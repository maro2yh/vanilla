<template>
  <div class="q-pa-md">
    <div class="row">
      <div class="col-4">
        <market-sise-card
          ref="MarketSiseCard"
          market="kospi"
          @onReady="onReadyMarketSiseCard"
        />
      </div>
    </div>

    <div class="row">
      <div class="col-12">
        <market-daily-chart
          ref="MarketDailyChart"
          market="kospi"
          @onReady="onReadyMarketDailyChart"
        />
      </div>
    </div>

    <div class="row q-mt-sm">
      <div class="col-12">
        <market-investor-daily-chart
          ref="MarketInvestorDailyChart"
          market="kospi"
          @onReady="onReadyMarketInvestorChart"
        />
      </div>
    </div>
  </div>
</template>
<script>
import MarketSiseCard from '@/components/MarketSiseCard.vue'
import MarketDailyChart from '@/components/MarketDailyChart.vue'
import MarketInvestorDailyChart from '@/components/MarketInvestorDailyChart.vue'

export default {
  name: 'MarketStatus',
  components: {
    MarketSiseCard,
    MarketDailyChart,
    MarketInvestorDailyChart
  },
  data() {
    return {
      toDate: null,
      fromDate: null
    }
  },
  beforeMount() {
    this.toDate = this.$moment(new Date())
    this.fromDate = this.$moment(this.toDate).subtract('52', 'weeks')
  },
  methods: {
    onReadyMarketSiseCard() {
      this.$refs.MarketSiseCard.update()
    },
    onReadyMarketDailyChart() {
      // this.$refs.MarketDailyChart.update('kospi', this.$moment(this.fromDate).format('YYYYMMDD'), this.$moment(this.toDate).format('YYYYMMDD'))
    },
    onReadyMarketInvestorChart() {
      this.$refs.MarketInvestorDailyChart.update('kospi')
    }
  }
}
</script>
<style lang="sass" scoped>
</style>