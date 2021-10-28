<template>
  <div class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-blueGray-700">
    <div class="rounded-t mb-0 px-4 py-3 bg-transparent">
      <div class="flex flex-wrap items-center">
        <div class="relative w-full max-w-full flex-grow flex-1">
          <h6 class="uppercase text-blueGray-100 mb-1 text-xs font-semibold">
            {{ this.market }} 일일 차트
          </h6>
          <h2 class="text-white text-xl font-semibold">
          </h2>
        </div>
      </div>
    </div>
    <div class="p-4 flex-auto">
      <!-- Chart -->
      <div class="relative h-350-px">
        <canvas v-bind:id="market"></canvas>
      </div>
    </div>
  </div>
</template>
<script>
import Chart from "chart.js";

export default {
  props: {
    market: {
      type: String,
      default: "kospi"
    }
  },
  data() {
    return {
      searchFromDate: "",
      searchToDate: "",
      data: []
    }
  },
  mounted: function () {
    this.searchToDate = this.$moment().toDate();
    this.searchFromDate = this.$moment(this.searchToDate).subtract('52', 'weeks').toDate();

    this.getMarketDailyData();
  },
  methods: {
    async getMarketDailyData() {
      this.axios.get("/market/daily", { params: this.searchData }).then(response => {
        this.data = response.data;
        this.drawChart();
      }).catch((error) => {
        console.log(error);
      })
    },
    drawChart() {
      var config = {
        type: "line",
        data: {
          labels: this.xLabels,
          datasets: [
            {
              label: "지수",
              backgroundColor: "#4c51bf",
              borderColor: "#4c51bf",
              data: this.jisuData,
              fill: false,
            },
            /*
            {
              label: new Date().getFullYear() - 1,
              fill: false,
              backgroundColor: "#fff",
              borderColor: "#fff",
              data: [40, 68, 86, 74, 56, 60, 87],
            },
            */
          ],
        },
        options: {
          maintainAspectRatio: false,
          responsive: true,
          title: {
            display: false,
            text: this.market.toUpperCase() + "일일 차트",
            fontColor: "white",
          },
          legend: {
            labels: {
              fontColor: "white",
            },
            align: "end",
            position: "bottom",
          },
          tooltips: {
            mode: "index",
            intersect: false,
          },
          hover: {
            mode: "nearest",
            intersect: true,
          },
          scales: {
            xAxes: [
              {
                ticks: {
                  fontColor: "rgba(255,255,255,.7)",
                },
                display: true,
                scaleLabel: {
                  display: false,
                  labelString: "Month",
                  fontColor: "white",
                },
                gridLines: {
                  display: false,
                  borderDash: [2],
                  borderDashOffset: [2],
                  color: "rgba(33, 37, 41, 0.3)",
                  zeroLineColor: "rgba(0, 0, 0, 0)",
                  zeroLineBorderDash: [2],
                  zeroLineBorderDashOffset: [2],
                },
              },
            ],
            yAxes: [
              {
                ticks: {
                  fontColor: "rgba(255,255,255,.7)",
                },
                display: true,
                scaleLabel: {
                  display: false,
                  labelString: "Value",
                  fontColor: "white",
                },
                gridLines: {
                  borderDash: [3],
                  borderDashOffset: [3],
                  drawBorder: false,
                  color: "rgba(255, 255, 255, 0.15)",
                  zeroLineColor: "rgba(33, 37, 41, 0)",
                  zeroLineBorderDash: [2],
                  zeroLineBorderDashOffset: [2],
                },
              },
            ],
          },
        },
      };
      var ctx = document.getElementById(this.market).getContext("2d");
      window.myLine = new Chart(ctx, config);
    }
  },
  computed: {
    searchData() {
      return {
        market: this.market,
        searchFromDate: this.$moment(this.searchFromDate).format("YYYYMMDD"),
        searchToDate: this.$moment(this.searchToDate).format("YYYYMMDD")
      }
    },
    xLabels() {
      return this.data.map(data => data.date);
    },
    jisuData() {
      return this.data.map(data => data.jisu);
    }
  }
}
</script>
