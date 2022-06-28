<template>
  <div class="panel dir">
    <panel :title="panelTitle" noButton="true">
      <div class="row">
        <div class="col-md-12">
          <div class="mb-3">
            <input type="text" class="w-100" v-model="dirPath" />
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="table-responsive">
            <table class="table mb-0">
              <colgroup>
                <col width="40%" />
                <col width="10%" />
                <col width="10%" />
                <col width="20%" />
                <col width="10%" />
                <col width="10%" />
              </colgroup>
              <thead>
                <tr>
                  <th>파일명</th>
                  <th>크기</th>
                  <th>파일 유형</th>
                  <th>최종 수정</th>
                  <th>권한</th>
                  <th>소유자/그룹</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </panel>
  </div>
</template>

<script>
import CredentialsFile from './CredentialsFile.js'
import { Client } from 'ssh2'

const fs = require('fs')

export default {
  props: {
    id: {
      type: Number,
      required: true
    },
    title: {
      type: String
    },
    session: {
      type: Object
    }
  },
  data() {
    return {
      panelTitle: '',
      credential: null,
      config: null,
      client: null,
      sftp: null,
      dirPath: ''
    }
  },
  created() {
    this.panelTitle = 'Remote - ' + this.title
    this.credential = CredentialsFile.find(this.session.credentials)
    
    this.config = {
      host: this.session.hostname,
      port: this.session.port,
      username: this.credential.username,
      password: this.credential.password,
      privateKey: this.credential.privateKey !== '' ? fs.readFileSync(this.credential.privateKey) : ''
    }
    
    this.dirPath = '/home/' + this.config.username
  },
  mounted() {
    this.$emit('onReady')
  },
  methods: {
    connect() {
      this.client = new Client()
      this.client.on('ready', () => {
        this.client.sftp((err, sftp) => {
          if (err) throw err
          this.sftp = sftp

          sftp.readdir(this.dirPath, (err, list) => {
            if (err) throw err
            // List the directory in the console
            console.dir(list)
            this.setTreeData(list)
            // Do not forget to close the connection, otherwise you'll get troubles
            // this.client.end()
         })
        })
      }).connect(this.config)
    },
    setTreeData(list) {
      if (list && list.length > 0) {
        list.forEach((data, idx) => {
          // if (data.longname.substring(0, 1) === 'd') {
          console.log(data, idx)
        })
      }
    }
  }
}
</script>