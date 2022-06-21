<template>
  <div>
    <panel title="Create new session" noButton="true" id="panel">
      <div>
        <vue-custom-scrollbar>
          <form class="with-header-with-footer">
            <fieldset>
              <div class="row">
                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="sessionName">Session Name</label>
                    <CustomInput
                      ref="SessionName"
                      type="text"
                      :disabled="formDisabled"
                      :nullable="false"
                      nullable-message="Please enter the Session Name."
                      :data="form.sessionName"
                    />
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-9">
                  <div class="mb-3">
                    <label class="form-label" for="hostname">IP or Hostname</label>
                    <CustomInput
                      ref="Hostname"
                      type="text"
                      :disabled="formDisabled"
                      :nullable="false"
                      nullable-message="Please enter the Hostname."
                      :ip="true"
                      ip-message="Please enter ip type."
                      :data="form.hostname"
                    />
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="mb-3">
                    <label class="form-label" for="port">Port</label>
                    <CustomInput
                      ref="Port"
                      type="text"
                      :disabled="formDisabled"
                      :nullable="false"
                      nullable-message="Please enter the Port."
                      :onlynumber="true"
                      onlynumber-message="Please enter only numbers."
                      :data="form.port"
                    />
                  </div>
                </div>
              </div>

              <legend class="mb-3">CREDENTIALS</legend>

              <div class="row">
                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="credentials">Choose credentials</label>
                    <select class="form-select"
                      v-model="form.credentials"
                      :disabled="formDisabled"
                    >
                      <option value="">- Create new credentials -</option>
                      <option v-for="credential in credentialsList"
                        :key="credential.id"
                        v-bind:value="credential.id"
                        @change="onChangeCredenrials"
                      >
                        {{ credential.name }}
                      </option>
                    </select>
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="username">Username</label>
                    <CustomInput
                      ref="Username"
                      type="text"
                      :disabled="formDisabled"
                      :nullable="false"
                      nullable-message="Please enter the Username."
                      :data="form.username"
                    />
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="password">Password</label>
                    <CustomInput
                      ref="Password"
                      type="password"
                      :disabled="formDisabled"
                      :nullable="true"
                      :data="form.password"
                    />
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="privateKey">
                      Private Key
                      <span v-if="isEdit && form.privateKey !== ''"
                        class="me5"
                      >
                        ({{ form.privateKey }})
                      </span>
                    </label>
                    <input class="form-control"
                      ref="PrivateKey"
                      type="file"
                      id="privateKey"
                      :disabled="formDisabled"
                      v-if="isEdit"
                    />
                    <input class="form-control"
                      type="text"
                      :disabled="formDisabled"
                      v-if="!isEdit"
                      v-model="form.privateKey"
                    />
                  </div>
                </div>

                <div class="col-md-12" v-if="form.credentials === ''">
                  <div class="mb-3">
                    <label class="form-label" for="credentialsName">Credentials Name</label>
                    <CustomInput
                      ref="CredentialsName"
                      type="credentialsName"
                      :disabled="formDisabled"
                      :nullable="true"
                      :data="form.credentialsName"
                    />
                  </div>
                </div>
              </div>
            </fieldset>
          </form>
        </vue-custom-scrollbar>
      </div>
      <div slot="footer">
        <div class="row">
          <div class="col-md-6">
            <div class="text-start">
              <button class="btn btn-primary btn-sm ms-5px"
                v-if="connectBtnEnabled"
                @click="onClickConnectBtn"
              >
                Connect
              </button>
            </div>
          </div>
          <div class="col-md-6">
            <div class="text-end">
              <button class="btn btn-primary btn-sm ms-5px text-end"
                v-if="saveBtnEnabled"
                @click="onClickSaveBtn"
              >
                Save
              </button>
              <button class="btn btn-default btn-sm ms-5px"
                v-if="saveBtnEnabled"
                @click="onClickCancelBtn"
              >
                Cancel
              </button>
              <button class="btn btn-primary btn-sm ms-5px"
                v-if="editBtnEnabled"
                @click="onClickEditBtn"
              >
                Edit
              </button>
            </div>
          </div>
        </div>
      </div>
    </panel>
  </div>
</template>

<script>
import CustomInput from '@/components/form/CustomInput.vue'
import CredentialsFile from '@/pages/ssh/CredentialsFile.js'

export default {
  components: {
    CustomInput
  },
  data() {
    return {
      formHeight: 200,
      isFolder: true,
      isEdit: false,
      id: '',
      form: {
        sessionName: '',
        hostname: '',
        port: '',
        credentials: '',
        username: '',
        password: '',
        privateKey: '',
        credentialsName: ''
      },
      formRefs: ['SessionName', 'Hostname', 'Port', 'Username', 'Password', 'CredentialsName'],
      credentialsFileData: null,
      credentialsList: [],
      selectedCredential: null
    }
  },
  created() {
    this.credentialsFileData = CredentialsFile.read()
    this.credentialsList = this.credentialsFileData.credentials
  },
  mounted() {
    this.$emit('onReady')
  },
  computed: {
    formDisabled() {
      return this.isFolder || !this.isEdit
    },
    saveBtnEnabled() {
      return this.isEdit
    },
    editBtnEnabled() {
      return !this.isFolder && this.id !== '' && !this.isEdit
    },
    connectBtnEnabled() {
      return !this.isFolder && this.id !== ''
    }
  },
  methods: {
    setNewSession() {
      this.id = ''
      this.isFolder = false
      this.isEdit = true
      this.form = {
        sessionName: '',
        hostname: '',
        port: '',
        credentials: '',
        username: '',
        password: '',
        privateKey: '',
        credentialsName: ''
      }
    },
    setSessionData(id, data) {
      this.id = id
      this.isFolder = !data.isLeaf || data.id === 1

      if (data.isLeaf) {
        this.form = data.session
        this.form.credentialsName = ''

        if (!this.form.sessionName) {
          this.form.sessionName = data.name
        }

        this.onChangeCredenrials()
        
        if (this.selectedCredential) {
          this.form.username = this.selectedCredential.username
          this.form.password = this.selectedCredential.password
          this.form.privateKey = this.selectedCredential.privateKey
          this.form.credentialsName =  this.selectedCredential.name
        }
      } else {
        this.clearFields()
        this.isEdit = false
      }
    },
    onClickSaveBtn() {
      let valid = true

      for (let ref of this.formRefs) {
        valid = this.$refs[ref].valid()

        if (!valid) {
          break
        }
      }

      if (!valid) {
        return
      }

      let credential = null

      if (this.form.credentials) {
        credential = {
          id: '' + Date.now(),
          name: this.form.credentialsName,
          username: this.$refs['Username'].value,
          password:  this.$refs['Password'].value,
          privateKey: (document.getElementById('privateKey').files !== null && document.getElementById('privateKey').files.length > 0) ? document.getElementById('privateKey').files[0].path : ''
        }

        this.credentialsFileData.credentials.push(credential)
        CredentialsFile.write(this.credentialsFileData)
      } else {
        for (let credentials of this.credentialsFileData.credentials) {
          if (credentials.id === this.form.credentials) {
            credential = credentials
            break
          }
        }
      }

      this.form.sessionName = this.$refs['SessionName'].value
      this.form.hostname = this.$refs['Hostname'].value
      this.form.port = this.$refs['Port'].value
      // this.form.username = this.$refs['Username'].value
      // this.form.password = this.$refs['Password'].value
      // this.form.privateKey = (document.getElementById('privateKey').files !== null && document.getElementById('privateKey').files.length > 0) ? document.getElementById('privateKey').files[0].path : ''
      // this.form.credentialsName = (this.$refs['CredentialsName'].value && this.$refs['CredentialsName'].value.trim()) ? this.$refs['CredentialsName'].value : this.form.sessionName
            
      this.$emit('onSaveSession', {id: this.id, data: this.form})
    },
    clearFields() {
      this.form = {
        sessionName: '',
        hostname: '',
        port: '',
        credentials: '',
        username: '',
        password: '',
        privateKey: '',
        credentialsName: ''
      }
    },
    onChangeCredenrials() {
      this.selectedCredential = null

      for (let credential of this.credentialsList) {
        if (credential.id === this.form.credentials) {
          this.selectedCredential = credential
          break
        }
      }
    },
    onClickCancelBtn() {

    },
    onClickEditBtn() {
      this.isEdit = true
    },
    onClickConnectBtn() {

    }
  }
}
</script>