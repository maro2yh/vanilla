<template>
  <div>
    <PageTitle />

    <div class="row">
      <div class="col-md-6">
        <div class="row">
          <div class="col-xs-12">
            <MarketSise ref="MarketSise"
              code="kospi"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-xs-12">
            <MarketDailyChart ref="MarketDailyChart" />
          </div>
        </div>
      </div>
      <div class="col-md-6">
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from '@/components/page-title/PageTitle.vue'
import MarketSise from './MarketSise.vue'
import MarketDailyChart from './MarketDailyChart.vue'

export default {
  components: {
    PageTitle,
    MarketSise,
    MarketDailyChart
  },
  created() {

  },
  methods: {

  }
}
</script>