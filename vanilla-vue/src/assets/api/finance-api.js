import axios from './axios.js'

export function getMarketSise(code) {
  return axios({
    url: '/market/sise',
    method: 'get',
    params: {
      code
    }
  })
}