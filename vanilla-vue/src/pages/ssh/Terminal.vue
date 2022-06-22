<template>
  <div id="terminal">
  </div>
</template>

<script>
import AppOptions from '@/config/AppOptions.vue'
import SessionFile from '@/pages/ssh/SessionFile.js'
import CredentialsFile from '@/pages/ssh/CredentialsFile.js'
import { Terminal } from 'xterm'
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
      socket: null
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
  },
  mounted() {
    this.connect()
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
      console.log(fileData)

      for (let data of fileData.credentials) {
        if (data.id === id) {
          this.credential = data
          break
        }
      }
    },
    connect() {
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
      this.term.open(document.getElementById('terminal'))
      this.term.write('Hello from \x1B[1;3;31mxterm.js\x1B[0m $ ')

      this.client = new Client()
      this.client.on('ready', () => {
        console.log('Client :: ready')
        this.client.shell((err, stream) => {
          if (err) throw err
          stream.on('close', () => {
            console.log('Stream :: close')
            this.client.end()
          }).on('data', (data) => {
            console.log('OUTPUT: ' + data)
            this.term.write(data)
          })
          stream.end('ls -l\nexit\n')
        })
      }).connect({
        host: this.session.hostname,
        port: this.session.port,
        username: this.credential.username,
        password: this.credential.password,
        privateKey: fs.readFileSync('public/ppk/gcp_rsa_vue')
      })
    }
  }
}
</script>

<style scoped>
.panel-body-bg {
  background-color: black;
  background-image: radial-gradient(
    rgba(0, 150, 0, 0.75), black 120%
  );
  height: calc(100vh - 60px);
  color: white;
  font: 1.3rem Inconsolata, monospace;
  text-shadow: 0 0 5px #C8C8C8;
}

::selection {
  background: #0080FF;
  text-shadow: none;
}


</style>