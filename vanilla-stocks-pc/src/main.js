import { createApp } from 'vue'
import App from './App.vue'
import { Quasar } from 'quasar'
import { router } from '@/router/index'
import quasarUserOptions from './quasar-user-options'
import axios from  '@/assets/js/axios'
import moment from 'moment'

const app = createApp(App)
app.config.globalProperties.$api = axios;
app.config.globalProperties.$moment = moment;
app.use(Quasar, quasarUserOptions)
app.use(router)
app.mount('#app')
