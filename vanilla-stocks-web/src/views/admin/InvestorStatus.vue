<template>
  <div class="flex flex-wrap mt-4">
    <div class="w-full lg:w-12/12 xl:w-12/12 px-4">
      <div
        class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded-lg bg-blueGray-100 border-0"
      >
        <div class="rounded-t bg-white mb-0 px-6 py-6">
          <div class="text-center flex">
            <button
              class="active:bg-emerald-600 font-bold uppercase text-xs px-4 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none mr-1 ease-linear transition-all duration-150"
              type="button"
              :class="[
                market === 'KOSPI' ? 'bg-emerald-500 text-white' : 'bg-white text-black'
              ]"
              @click="onClickChangeMarket('KOSPI')"
            >
              KOSPI
            </button>

            <button
              class="active:bg-emerald-600 font-bold uppercase text-xs px-4 py-2 rounded shadow hover:shadow-md outline-none focus:outline-none mr-1 ease-linear transition-all duration-150"
              type="button"
              :class="[
                market === 'KOSDAQ' ? 'bg-emerald-500 text-white' : 'bg-white text-black'
              ]"
              @click="onClickChangeMarket('KOSDAQ')"
            >
              KOSDAQ
            </button>
          </div>

          <div class="mt-1">
            <investor-daily-chart
              ref="InvestorDailyChart"
              :market="market"
            />
          </div>
        </div>
      </div>
    </div>

    <div class="w-full lg:w-12/12 xl:w-12/12 px-4">
      
    </div>
  </div>
</template>
<script>
import InvestorDailyChart from "@/components/Chart/InvestorDailyChart.vue";

export default {
  name: "investor-status-page",
  components: {
    InvestorDailyChart
  },
  data() {
    return {
      market: "KOSPI"
    }
  },
  methods: {
    onClickChangeMarket(market) {
      this.market = market;
      this.$refs.InvestorDailyChart.getDailyData();
    }
  }
}
</script>