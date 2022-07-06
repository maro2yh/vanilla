<template>
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
      <h3 class="mt-10px"><i class="fa fa-cog me-5px"></i>Session Manage</h3>

      <panel noButton="true" noHeader="true">
        <vue-custom-scrollbar class="form-box">
          <form>
            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">저장된 세션</label>
              <div class="col-md-7">
                <select class="form-select"
                  v-model="form.sessionId"
                  @change="onChangeSessions"
                  :disabled="isSaving"
                >
                  <option value="">새 연결</option>
                  <option v-for="s in sessions"
                    :key="s.id"
                    v-bind:value="s.id"
                  >
                    {{ s.sessionName }}
                  </option>
                </select>
                <!--<small class="fs-12px text-gray-500-darker">We'll never share your email with anyone else.</small>-->
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">세션 이름</label>
              <div class="col-md-7">
                <CustomInput
                  ref="SessionName"
                  type="text"
                  :nullable="false"
                  nullable-message="세션 이름을 입력하세요."
                  :data="form.sessionName"
                  :disabled="isSaving"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">Host</label>
              <div class="col-md-7">
                <CustomInput
                  ref="Host"
                  type="text"
                  :nullable="false"
                  nullable-message="Host를 입력하세요."
                  :data="form.host"
                  :disabled="isSaving"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">Post</label>
              <div class="col-md-7">
                <CustomInput
                  ref="Port"
                  type="text"
                  :nullable="false"
                  nullable-message="Port를 입력하세요."
                  :onlynumber="true"
                  onlynumber-message="Port가 유효하지 않습니다."
                  :data="form.port"
                  :disabled="isSaving"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">사용자 이름</label>
              <div class="col-md-7">
                <CustomInput
                  ref="UserName"
                  type="text"
                  :nullable="false"
                  nullable-message="사용자 이름을 입력하세요."
                  :data="form.userName"
                  :disabled="isSaving"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">비밀번호</label>
              <div class="col-md-7">
                <CustomInput
                  ref="Password"
                  type="password"
                  :nullable="true"
                  :data="form.password"
                  :disabled="isSaving"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">데이터 베이스</label>
              <div class="col-md-7">
                <CustomInput
                  ref="DataBase"
                  type="text"
                  :nullable="true"
                  :data="form.dbName"
                  :disabled="isSaving"
                />
                <small class="fs-12px text-gray-500-darker">2개 이상의 데이터 베이스를 입력하려면 ';'를 사용하세요.</small><br/>
                <small class="fs-12px text-gray-500-darker">모든 데이터 베이스를 보려면 비워 두세요.</small>
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end" for="maxIdle">세션 유휴 시간 (초)</label>
              <div class="col-md-7">
                <CustomInput
                  ref="MaxIdle"
                  type="text"
                  :nullable="true"
                  :onlynumber="true"
                  onlynumber-message="세션 유휴 시간이 유효하지 않습니다."
                  :data="form.maxIdle"
                  :disabled="isSaving"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-5 text-end">Keep-Alive Interval (초)</label>
              <div class="col-md-7">
                <CustomInput
                  ref="KeepAlive"
                  type="text"
                  :nullable="true"
                  :onlynumber="true"
                  onlynumber-message="Keep-Alive 시간이 유효하지 않습니다."
                  :data="form.keepAlive"
                  :disabled="isSaving"
                />
              </div>
            </div>
          </form>
        </vue-custom-scrollbar>

        <div slot="footer">
          <p class="text-end mb-0">
            <button class="btn btn-primary me-5px" :disabled="isSaving" v-if="form.sessionId !== ''" @click="onClickCancelBtn">연결</button>
            <button class="btn btn-info me-5px" :disabled="isSaving" v-if="form.sessionId !== ''" @click="onClickTestConnectBtn">테스트 연결</button>
            <button class="btn btn-success me-5px" :disabled="isSaving" @click="onClickSaveBtn">저장</button>
            <button class="btn btn-white" :disabled="isSaving" @click="onClickCancelBtn">취소</button>
          </p>
        </div>
      </panel>
    </div>
    <div class="col-md-3"></div>
  </div>
</template>

<script>
import CustomInput from '@/components/form/CustomInput.vue'
import SessionFile from '@/pages/db/SessionFile.js'
import mariadb from 'mariadb'

export default {
  components: {
    CustomInput
  },
  data() {
    return {
      fileData: null,
      sessions: [],
      session: null,
      form: {
        sessionId: '',
        sessionName: '',
        host: '',
        port: '',
        userName: '',
        password: '',
        dbName: '',
        maxIdleRadio: 'default',
        maxIdle: '28800',
        keepAlive: ''
      },
      formRefs: ['SessionName', 'Host', 'Port', 'UserName', 'Password', 'DataBase', 'MaxIdle', 'KeepAlive'],
      isSaving: false
    }
  },
  created() {
    this.fileData = SessionFile.read()
    this.sessions = this.fileData.sessions
  },
  methods: {
    reset() {
      this.form = {
        sessionId: '',
        sessionName: '',
        host: '',
        port: '',
        userName: '',
        password: '',
        dbName: '',
        maxIdle: '28800',
        keepAlive: ''
      }
    },
    onChangeSessions() {
      if (this.form.sessionId === '') {
        this.reset()
      } else {
        this.session = this.sessions.find(e => e.id === this.form.sessionId)
        this.form = this.session
        this.form.sessionId = this.session.id
      }
    },
    onClickCancelBtn() {
      this.reset()
    },
    onClickSaveBtn() {
      try {
        this.isSaving = true
        let valid = true

        for (let ref of this.formRefs) {
          if (this.$refs[ref]) {
            valid = this.$refs[ref].valid()

            if (!valid) {
              break
            }
          }
        }

        if (!valid) {
          return
        }

        if (this.form.sessionId === '') {
          this.session = {
            id: '' + Date.now()
          }
        }

        this.session.sessionName = this.$refs['SessionName'].value
        this.session.host = this.$refs['Host'].value
        this.session.port = this.$refs['Port'].value
        this.session.userName = this.$refs['UserName'].value
        this.session.password = this.$refs['Password'].value
        this.session.dbName = this.$refs['DataBase'].value
        this.session.maxIdle = this.$refs['MaxIdle'].value
        this.session.keepAlive = this.$refs['KeepAlive'].value

        this.sessions.push(this.session)

        this.fileData.sessions = this.sessions
        SessionFile.write(this.fileData)

        this.form.sessionId = this.session.id
      } finally {
        this.isSaving = false
      }
    },
    async onClickTestConnectBtn() {
      const pool = mariadb.createPool({
        host: this.session.host,
        port: this.session.port,
        user: this.session.userName,
        password: this.session.password,
        connectionLimit: 1
      })

      let connection = null

      try {
        connection = await pool.getConnection()
        const rows = connection.query('SELECT VERSION()')
        console.log(rows)
      } catch (err) {
        console.log(err)
      } finally {
        if (connection) {
          connection.end()
        }
      }

      /*
      this.$swal({
        title: '테스트 연결',
        html: '접속 성공!<br/>DB 버전 : ',
        type: 'success',
        showCancelButton: false,
        buttonsStyling: false,
        confirmButtonText: '확인',
        cancelButtonText: 'Cancel',
        confirmButtonClass: 'btn me-5px btn-success',
        cancelButtonClass: 'btn btn-default',
        width: '300px'
      })
      */
    }
  }
}
</script>

<style lang="less" rel="stylesheet/less" scoped>
.panel-body {
  .form-box {
    height: calc(100vh - 300px);
    border: 1px solid rgba(var(--app-component-border-color-rgb), 0.75);
    padding: 10px;
  }
}

.panel-footer {
  border: 0px;
}
</style>