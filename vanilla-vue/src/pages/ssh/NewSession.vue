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
                    <input class="form-control" type="text" id="sessionName" placeholder="" v-model="form.sessionName" />
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-9">
                  <div class="mb-3">
                    <label class="form-label" for="hostname">IP or Hostname</label>
                    <input class="form-control" type="text" id="hostname" placeholder="" v-model="form.hostname" />
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="mb-3">
                    <label class="form-label" for="port">Port</label>
                    <input class="form-control" type="text" id="port" placeholder="" v-model="form.port" />
                  </div>
                </div>
              </div>

              <legend class="mb-3">CREDENTIALS</legend>

              <div class="row">
                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="credentials">Choose credentials</label>
                    <select class="form-select" v-model="form.credentials">
                      <option value="">- Create new credentials -</option>
                    </select>
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="username">Username</label>
                    <input class="form-control" type="text" id="username" placeholder="" v-model="form.username" />
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="password">Password</label>
                    <input class="form-control" type="password" id="password" placeholder="" v-model="form.password" />
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="privateKey">Private Key</label>
                    <input class="form-control" type="file" id="privateKey" placeholder="" ref="privateKey" />
                  </div>
                </div>

                <div class="col-md-12">
                  <div class="mb-3">
                    <label class="form-label" for="credentialsName">Credentials Name</label>
                    <input class="form-control" type="text" id="credentialsName" placeholder="" v-model="form.credentialsName" />
                  </div>
                </div>
              </div>
            </fieldset>
          </form>
        </vue-custom-scrollbar>
      </div>
      <div slot="footer" class="text-end">
        <button class="btn btn-primary btn-sm ms-5px" @click="onClickSaveBtn">Save</button>
      </div>
    </panel>
  </div>
</template>

<script>

export default {
  components: {
  },
  data() {
    return {
      formHeight: 200,
      form: {
        sessionName: '',
        hostname: '',
        port: '',
        credentials: '',
        username: '',
        password: '',
        privateKey: '',
        credentialsName: ''
      }
    }
  },
  created() {

  },
  mounted() {
    this.$emit('onReady')
  },
  methods: {
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
    setSessionData(data) {
      console.log(data)
    },
    onClickSaveBtn() {
      this.$emit('onSave', this.form)
    }
  }
}
</script>