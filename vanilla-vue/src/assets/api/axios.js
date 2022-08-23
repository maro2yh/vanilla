import axios from 'axios'

axios.defaults.baseURL = process.env.VUE_APP_API_BASE_URL + process.env.VUE_APP_API_VERSION

axios.interceptors.request.use(function (config) {
  for (const key in config.params) {
    config.params[key] = encodeURIComponent(config.params[key])
  }

  return config
}, function (error) {
  console.log('axios request error')
  console.log(error)
  return error
})

axios.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    return error.response
  }
)

export default axios