<template>
  <div>
    <PageTitle />

    <div class="row">
      <div class="col-md-6">
        <div class="row">
          <div class="col-xs-12">
            <MarketSise ref="KospiSise"
              code="kospi"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-xs-12">
            <MarketDailyChart ref="KospiDailyChart"
              code="kospi"
            />
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <div class="row">
          <div class="col-xs-12">
            <MarketSise ref="KosdaqSise"
              code="kosdaq"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-xs-12">
            <MarketDailyChart ref="KosdaqDailyChart"
              code="kosdaq"
            />
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-12">
        <div class="row">
          <div class="col-md-3">
            <panel noHeader="true" noButton="true">
              <table class="table table-striped mb-0">
                <thead>
                  <tr>
                    <th colspan="3">업종 현황</th>
                  </tr>
                  <tr>
                    <th>업종명</th>
                    <th>전일대비</th>
                    <th>종목</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="upjong in upjongList" :key="upjong.no">
                    <td class="name">{{ upjong.name }}</td>
                    <td>{{ upjong.changeRate }}%</td>
                    <td>{{ upjong.upCount }}/{{ upjong.totalCount }}</td>
                  </tr>
                </tbody>
              </table>
            </panel>
          </div>
          <div class="col-md-3">
            <panel noHeader="true" noButton="true">
              <table class="table table-striped mb-0">
                <thead>
                  <tr>
                    <th colspan="3">테마 현황</th>
                  </tr>
                  <tr>
                    <th>테마명</th>
                    <th>전일대비</th>
                    <th>종목</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="theme in themeList" :key="theme.no">
                    <td class="name">{{ theme.name }}</td>
                    <td>{{ theme.changeRate }}%</td>
                    <td>{{ theme.upCount }}/{{ theme.totalCount }}</td>
                  </tr>
                </tbody>
              </table>
            </panel>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from '@/components/page-title/PageTitle.vue'
import MarketSise from './MarketSise.vue'
import MarketDailyChart from './MarketDailyChart.vue'

import { getUpjong, getTheme } from '@/assets/api/finance-api.js'

export default {
  components: {
    PageTitle,
    MarketSise,
    MarketDailyChart
  },
  data() {
    return {
      upjongList: [],
      themeList: []
    }
  },
  created() {
    this.getUpjongList(5)
    this.getThemeList(5)
  },
  methods: {
    async getUpjongList(limit) {
      const apiResult = await getUpjong(limit)
      this.upjongList = apiResult.data
    },
    async getThemeList(limit) {
      const apiResult = await getTheme(limit)
      this.themeList = apiResult.data
    }
  }
}
</script>

<style lang="less" rel="stylesheet/less" scoped>
td.name {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
</style>