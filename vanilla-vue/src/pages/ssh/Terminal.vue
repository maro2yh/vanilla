<template>
  <div id="terminal" class="terminal">
  </div>
</template>

<script>
import AppOptions from '@/config/AppOptions.vue'
import SessionFile from '@/pages/ssh/SessionFile.js'
import CredentialsFile from '@/pages/ssh/CredentialsFile.js'
import { Terminal } from 'xterm'
import { FitAddon } from 'xterm-addon-fit'
import { Client } from 'ssh2'

const fs = require('fs')

export default {
  data() {
    return {
      id: '',
      title: '',
      session: null,
      credential: null,
      term: null,
      command: '',
      sshClient: null,
      sshStream: null
    }
  },
  created() {
    AppOptions.appHeaderNone = true
    AppOptions.appSidebarNone = true
    this.id = this.$route.query.id

    const fileData = SessionFile.read()

    for (let data of fileData.sessions) {
      if (this.session !== null) {
        break
      }

      if (!data.isLeaf) {
        this.findSessionData(data)
      } else if (data.isLeaf && data.id === Number(this.id)) {
        this.session = data.session
        break
      }
    }

    this.title = this.session.sessionName
    this.findCredentialData(this.session.credentials)

    window.addEventListener('keypress', this.onKeypress)
  },
  mounted() {
    this.createTerminal()
    // this.connect()
  },
  methods: {
    findSessionData(parent) {
      for (let child of parent.children) {
        if (this.session !== null) {
          break
        }

        if (!child.isLeaf) {
          this.findSessionData(child)
        } else if (child.isLeaf && child.id === Number(this.id)) {
          this.session = child.session
          break
        }
      }
    },
    findCredentialData(id) {
      const fileData = CredentialsFile.read()

      for (let data of fileData.credentials) {
        if (data.id === id) {
          this.credential = data
          break
        }
      }
    },
    createTerminal() {
      this.term = new Terminal({
        rendererType: "canvas", //type of rendering
        rows: 35, // number of rows
        convertEol : true, // enabled, the cursor is set to the beginning of the next line
        scrollback : 10, // rollback amount of the terminal
        disableStdin : false, // whether to disable input
        cursorStyle : "underline", // Cursor Style
        cursorBlink : true, // cursor blinks
        theme : {
          foreground: "yellow", // font
          background : "#060101", // Background color
          cursor : "help" // Set the cursor
        }
      });

      const fitAddon = new FitAddon()
      this.term.loadAddon(fitAddon)

      this.term.open(document.getElementById('terminal'))
      fitAddon.fit()
      this.term.write('Hello from \x1B[1;3;31mxterm.js\x1B[0m $ ')

      this.term.onKey((key) => {
        console.log(key)

        if (key.domEvent.keyCode === 13) {
          this.term.write('\n')
          this.command += '\n'
          this.sshStream.write(this.command)
          this.command = ''
        } else {
          this.term.write(key.key)
          this.command += key.key
        }
      })
    },
    connect() {
      this.sshClient = new Client()
      this.sshClient.on('ready', () => {
        this.sshClient.shell((err, stream) => {
          if (err) throw err
          stream.on('close', () => {
            console.log('Stream :: close')
            this.sshClient.end()
          }).on('data', (data) => {
            this.term.write(data)
          })

          this.sshStream = stream
        })
      }).connect({
        host: this.session.hostname,
        port: this.session.port,
        username: this.credential.username,
        password: this.credential.password,
        privateKey: fs.readFileSync(this.credential.privateKey)
      })
    }
  }
}
</script>

<style scoped>
.terminal {
  width: calc(100vw - 30px);
  height: calc(100vh - 60px);
}
</style>