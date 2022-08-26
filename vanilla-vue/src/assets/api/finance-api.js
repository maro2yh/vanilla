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

export function getMarketDaily(code, searchFromDate, searchToDate) {
  return axios({
    url: '/market/daily',
    method: 'get',
    params: {
      code,
      searchFromDate,
      searchToDate
    }
  })
}

export function getUpjong(limit) {
  return axios({
    url: '/upjong',
    method: 'get',
    params: {
      limit
    }
  })
}

export function getTheme(limit) {
  return axios({
    url: '/theme',
    method: 'get',
    params: {
      limit
    }
  })
}