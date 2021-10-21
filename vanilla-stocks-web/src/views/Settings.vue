<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
    </base-header>
    <b-container fluid class="mt--9">
      <b-row>
        <b-col xl="2" md="6">
          <b-card no-body>
            <b-card-header class="border-0">감시</b-card-header>
            <div class="card-body">
              <b-form-checkbox
                v-for="menu in monitorMenus"
                :key="menu.name"
                v-model="menu.checked"
                value="true"
                unchecked-value="false"
              >
              {{ menu.name }}
              </b-form-checkbox>
            </div>
          </b-card>
        </b-col>

        <b-col xl="2" md="6">
          <b-card no-body>
            <b-card-header class="border-0">관리</b-card-header>
            <div class="card-body">
              <b-form-checkbox
                v-for="menu in managementMenus"
                :key="menu.name"
                v-model="menu.checked"
                value="true"
                unchecked-value="false"
              >
              {{ menu.name }}
              </b-form-checkbox>
            </div>
          </b-card>
        </b-col>

        <b-col xl="2" md="6">
          <b-card no-body>
            <b-card-header class="border-0">성능</b-card-header>
            <div class="card-body">
              <b-form-checkbox
                v-for="menu in performanceMenus"
                :key="menu.name"
                v-model="menu.checked"
                value="true"
                unchecked-value="false"
              >
              {{ menu.name }}
              </b-form-checkbox>
            </div>
          </b-card>
        </b-col>

        <b-col xl="2" md="6">
          <b-card no-body>
            <b-card-header class="border-0">품질</b-card-header>
            <div class="card-body">
              <b-form-checkbox
                v-for="menu in qualityMenus"
                :key="menu.name"
                v-model="menu.checked"
                value="true"
                unchecked-value="false"
              >
              {{ menu.name }}
              </b-form-checkbox>
            </div>
          </b-card>
        </b-col>

        <b-col xl="2" md="6">
          <b-card no-body>
            <b-card-header class="border-0">기타</b-card-header>
            <div class="card-body">
              <b-form-checkbox
                v-for="menu in etcMenus"
                :key="menu.name"
                v-model="menu.checked"
                value="true"
                unchecked-value="false"
              >
              {{ menu.name }}
              </b-form-checkbox>
            </div>
          </b-card>
        </b-col>
      </b-row>
      <b-row>
        <b-col xl="10" style="text-align: center; margin-top: 10px;">
          <b-button variant="success" @click="onClickConfirmBtn">확인</b-button>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
  import EventBus from '@/plugins/eventBus';

  export default {
    data() {
      return {
        monitorMenus: [],
        managementMenus: [],
        performanceMenus: [],
        qualityMenus: [],
        etcMenus: []
      }
    },
    beforeMount() {
      const storageMenus = JSON.parse(window.localStorage.getItem('checkedMenus'));

      this.monitorMenus = storageMenus.monitorMenus;
      this.managementMenus = storageMenus.managementMenus;
      this.performanceMenus = storageMenus.performanceMenus;
      this.qualityMenus = storageMenus.qualityMenus;
      this.etcMenus = storageMenus.etcMenus;
    },
    methods: {
      onClickConfirmBtn() {
        const checkedMenus = {
          monitorMenus: this.monitorMenus,
          managementMenus: this.managementMenus,
          performanceMenus: this.performanceMenus,
          qualityMenus: this.qualityMenus,
          etcMenus: this.etcMenus
        }

        window.localStorage.setItem('checkedMenus', JSON.stringify(checkedMenus));
        
        EventBus.$emit('event-menuChanged', Date.now());
      }
    }
  }
</script>
<style>
  .card {
    height: 650px;
  }

  .card-header {
    background-color: #000;
    color: #FFF;
    font-weight: bold;
    text-align: center;
  }

  .card-body {
    padding: 10px;
  }
</style>