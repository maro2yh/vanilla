<template>
  <div>
    <div class="flex flex-wrap">
      <div class="w-full lg:w-6/12 xl:w-6/12 px-4">
        <div class="w-full lg:w-12/12 xl:w-6/12 px-4">
          <card-stats
            :statSubtitle="cardKospi.statSubtitle"
            :statTitle="cardKospi.statTitle"
            :statArrow="cardKospi.statArrow"
            :statPercent="cardKospi.statPercent"
            :statPercentColor="cardKospi.statPercentColor"
            :statDescripiron="cardKospi.statDescripiron"
            :statIconName="cardKospi.statIconName"
            :statIconColor="cardKospi.statIconColor"
          />
        </div>

        <div class="w-full lg:w-12/12 xl:w-12/12 px-4 mt-12">
          <market-daily-chart
            market="kospi"
          />
        </div>
      </div>

      <div class="w-full lg:w-6/12 xl:w-6/12 px-4">
        <div class="w-full lg:w-12/12 xl:w-6/12 px-4">
          <card-stats
            :statSubtitle="cardKosdaq.statSubtitle"
            :statTitle="cardKosdaq.statTitle"
            :statArrow="cardKosdaq.statArrow"
            :statPercent="cardKosdaq.statPercent"
            :statPercentColor="cardKosdaq.statPercentColor"
            :statDescripiron="cardKosdaq.statDescripiron"
            :statIconName="cardKosdaq.statIconName"
            :statIconColor="cardKosdaq.statIconColor"
          />
        </div>

        <div class="w-full lg:w-12/12 xl:w-12/12 px-4 mt-12">
          <market-daily-chart
            market="kosdaq"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import CardStats from "@/components/Cards/CardStats.vue";
import MarketDailyChart from "@/components/Chart/MarketDailyChart.vue";

export default {
  name: "marketstatus-page",
  components: {
    CardStats,
    MarketDailyChart
  },
  data() {
    return {
      kospiSise: {},
      kosdaqSise: {},
      cardKospi: {
        statSubtitle: "KOSPI",
        statTitle: "",
        statArrow: "",
        statPercent: "",
        statPercentColor: "text-mute-500",
        statDescripiron: "",
        statIconName: "far fa-chart-bar",
        statIconColor: "bg-emerald-500"
      },
      cardKosdaq: {
        statSubtitle: "KOSDAQ",
        statTitle: "",
        statArrow: "",
        statPercent: "",
        statPercentColor: "text-mute-500",
        statDescripiron: "",
        statIconName: "far fa-chart-bar",
        statIconColor: "bg-emerald-500"
      }
    }
  },
  created() {
    this.getMarketSise();
  },
  methods: {
    async getMarketSise() {
      this.axios.get("/market/sise", {}).then(response => {
        this.kospiSise = response.data.kospi;
        this.kosdaqSise = response.data.kosdaq;

        this.cardKospi.statTitle = "" + this.kospiSise.jisu;
        this.cardKospi.statPercent = "" + this.kospiSise.change + " " + this.kospiSise.changeRate;
        
        if (this.kospiSise.changeRate > 0) {
          this.cardKospi.statArrow = "fas fa-arrow-up";
          this.cardKospi.statPercentColor = "text-red-500";
        } else if (this.kospiSise.changeRate < 0) {
          this.cardKospi.statArrow = "fas fa-arrow-down";
          this.cardKospi.statPercentColor = "text-lightBlue-500";
        } else {
          this.cardKospi.statArrow = "fas fa-minus";
          this.cardKospi.statPercentColor = "text-mute-500";
        }

        this.cardKosdaq.statTitle = "" + this.kosdaqSise.jisu;
        this.cardKosdaq.statPercent = "" + this.kosdaqSise.change + " " + this.kosdaqSise.changeRate;
        
        if (this.kosdaqSise.changeRate > 0) {
          this.cardKosdaq.statArrow = "fas fa-arrow-up";
          this.cardKosdaq.statPercentColor = "text-red-500";
        } else if (this.kosdaqSise.changeRate < 0) {
          this.cardKosdaq.statArrow = "fas fa-arrow-down";
          this.cardKosdaq.statPercentColor = "text-lightBlue-500";
        } else {
          this.cardKosdaq.statArrow = "fas fa-minus";
          this.cardKosdaq.statPercentColor = "text-mute-500";
        }
      }).catch((error) => {
        console.log(error);
      })
    }
  }
}
</script>
