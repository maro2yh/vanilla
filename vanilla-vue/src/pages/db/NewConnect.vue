<template>
  <div class="row">
    <div class="col-xl-3"></div>
    <div class="col-xl-6">
      <h3 class="mt-10px"><i class="fa fa-cog"></i>New Connect</h3>
      <panel noButton="true" noHeader="true">
        <p class="mb-0">
          <button class="btn btn-primary me-5px">새 연결</button>
          <button class="btn btn-danger">삭제</button>
        </p>

        <vue-custom-scrollbar>
          <form class="with-header-with-footer">
            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-3">저장된 세션</label>
              <div class="col-md-9">
                <select class="form-select"
                  v-model="form.sessionId"
                  @change="onChangeSessions"
                >
                  <option value="">새 연결</option>
                </select>
                <!--<small class="fs-12px text-gray-500-darker">We'll never share your email with anyone else.</small>-->
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-3">세션 이름</label>
              <div class="col-md-9">
                <CustomInput
                  ref="SessionName"
                  type="text"
                  :nullable="false"
                  nullable-message="세션 이름을 입력하세요."
                  :data="form.sessionName"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-3">Host</label>
              <div class="col-md-9">
                <CustomInput
                  ref="Host"
                  type="text"
                  :nullable="false"
                  nullable-message="Host를 입력하세요."
                  :data="form.host"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-3">Post</label>
              <div class="col-md-9">
                <CustomInput
                  ref="Port"
                  type="text"
                  :nullable="false"
                  nullable-message="Port를 입력하세요."
                  :onlynumber="true"
                  onlynumber-message="Port가 유효하지 않습니다."
                  :data="form.port"
                />
              </div>
            </div>

            <div class="row mb-15px">
              <label class="form-label col-form-label col-md-3">데이터 베이스</label>
              <div class="col-md-9">
                <CustomInput
                  ref="DataBase"
                  type="text"
                  :nullable="true"
                  :data="form.dbName"
                />
                <small class="fs-12px text-gray-500-darker">2개 이상의 데이터 베이스를 입력하려면 ';'를 사용하세요.</small><br/>
                <small class="fs-12px text-gray-500-darker">모든 데이터 베이스를 보려면 비워 두세요.</small>
              </div>
            </div>
          </form>
        </vue-custom-scrollbar>

        <div slot="footer">
          <p class="text-end mb-0">
            <a href="javascript:;" class="btn btn-white me-5px">Default</a>
            <a href="javascript:;" class="btn btn-primary">Primary</a>
          </p>
        </div>
      </panel>
    </div>
    <div class="col-xl-3"></div>
  </div>
</template>

<script>
import CustomInput from '@/components/form/CustomInput.vue'

export default {
  components: {
    CustomInput
  },
  data() {
    return {
      form: {
        sessionId: '',
        sessionName: '',
        host: '',
        port: '',
        dbName: ''
      }
    }
  },
  methods: {
    onChangeSessions() {

    }
  }
}
</script>