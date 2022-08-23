<template>
  <div>
    <panel noHeader="true" noButton="true" id="panel-sessions">
      <div class="table-responsive" v-if="sise">
        <table class="table table-striped mb-0">
          <thead>
            <tr>
              <th colspan="4">
                <span class="market-name" :class="jisuUpDown">{{ sise.market }}</span>
                <span class="ms-10px jisu" :class="jisuUpDown">{{ Number(sise.jisu).toLocaleString() }}</span>
                <span class="ms-10px" :class="jisuUpDown"><i :class="changeValueIcon"></i>{{ sise.changeValue }}</span>
                <span class="ms-10px" :class="jisuUpDown">{{ sise.changeRate }}%</span>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>거래량(천주)</th>
              <td>{{ Number(sise.tradeVolume).toLocaleString() }}</td>
              <th>거래대금(백만)</th>
              <td>{{ Number(sise.tradePrice).toLocaleString() }}</td>
            </tr>
            <tr>
              <th>장중최고</th>
              <td>{{ Number(sise.high).toLocaleString() }}</td>
              <th>장중최저</th>
              <td>{{ Number(sise.low).toLocaleString() }}</td>
            </tr>
            <tr>
              <th>52주 최고</th>
              <td>{{ Number(sise.week52High).toLocaleString() }}</td>
              <th>52주 최저</th>
              <td>{{ Number(sise.week52Low).toLocaleString() }}</td>
            </tr>
            <tr>
              <th>등락 종목</th>
              <td colspan="3">
                <span class="item-count-up">상한가 {{ sise.upLimitCount }}</span>
                <span class="ms-15px item-count-up">상승 {{ sise.upCount }}</span>
                <span class="ms-15px">보합 {{ sise.sameCount }}</span>
                <span class="ms-15px item-count-down">하한가 {{ sise.downLimitCount}}</span>
                <span class="ms-15px item-count-down">하락 {{ sise.downCount }}</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </panel>
  </div>
</template>

<script>
import { getMarketSise } from '@/assets/api/finance-api.js'

export default {
  props: ['code'],
  data() {
    return {
      sise: null,
      jisuUpDown: '',
      changeValueIcon: ''
    }
  },
  created() {
    console.log('MarketSise created >> ', this.code)
    this.getSise()
  },
  methods: {
    async getSise() {
      const apiResult = await getMarketSise(this.code)
      this.sise = apiResult.data
      console.log(this.sise)

      if (Number(this.sise.changeRate) < 0) {
        this.jisuUpDown = 'down'
        this.changeValueIcon = 'fa fa-caret-down'
      } else if (Number(this.sise.changeRate) > 0) {
        this.jisuUpDown = 'up'
        this.changeValueIcon = 'fa fa-caret-up'
      } else {
        this.jisuUpDown = ''
      }
    }
  }
}
</script>

<style lang="less" rel="stylesheet/less" scoped>
span.market-name {
  text-transform: uppercase;
  font-size: 20px;
}

span.jisu {
  font-size: 15px;
}

table > thead > tr > th > span.down {
  color: blue;
}

table > thead > tr > th > span.up {
  color: red;
}

.item-count-up {
  color: red;
}

.item-count-down {
  color: blue;
}
</style>