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
          <vue-custom-scrollbar>
            <div class="table-area">
              <table class="table mb-0" v-columns-resizable>
                <thead>
                  <tr>
                    <th width="2%"></th>
                    <th width="30%">파일명</th>
                    <th width="8%">크기</th>
                    <th width="20%">최종 수정</th>
                    <th width="20%">권한</th>
                    <th width="20%">소유자/그룹</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="data in dataSet" :key="data.id"
                    @dblclick="onDbClickRow(data)"
                  >
                    <td>
                      <span v-if="!data.isFile" class="text-yellow"><i class="fa fa-folder" /></span>
                      <span v-if="data.isFile" class="text-black-100"><i class="fa fa-file" /></span>
                    </td>
                    <td class="text-ellipsis">
                        <span>{{ data.name }}</span>
                      </td>
                    <td class="text-ellipsis"><span v-if="data.isFile">{{ data.size }}</span></td>
                    <td class="text-ellipsis">{{ data.mtime }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </vue-custom-scrollbar>
        </div>
      </div>
    </panel>
  </div>
</template>

<script>
import CredentialsFile from './CredentialsFile.js'
import { Client } from 'ssh2'

const fs = require('fs')
// const path = require('path')

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
      dirPath: '',
      dataSet: [],
      selectedData: null
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
          this.readDir()
        })
      }).connect(this.config)
    },
    readDir() {
      this.sftp.readdir(this.dirPath, (err, files) => {
        if (err) throw err
        // List the directory in the console
        console.dir(files)
        // Do not forget to close the connection, otherwise you'll get troubles
        // this.client.end()
      })
    },
    setTreeData(files) {
      if (files && files.length > 0) {
        files.forEach((file, idx) => {
          // if (data.longname.substring(0, 1) === 'd') {
          console.log(idx, file)
        })
      }
    }
  }
}
</script>

<style lang="less" rel="stylesheet/less" scoped>
.table-area {
  height: calc(100vh - 150px);
}

.table:hover {
  cursor: default;
}

.tr-hover:hover {
  cursor: pointer;
}

.border-top {
  border-top: 1px solid #000000
}

.border-left {
  border-left: 1px solid #000000
}
</style>