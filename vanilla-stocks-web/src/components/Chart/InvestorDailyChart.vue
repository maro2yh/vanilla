<template>
  <div class="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-blueGray-700">
    <div class="rounded-t mb-0 px-4 py-3 bg-transparent">
      <div class="flex flex-wrap items-center">
        <div class="relative w-full max-w-full flex-grow flex-1">
          <h6 class="uppercase text-blueGray-100 mb-1 text-xs font-semibold">
            투자자별 {{ market }} 순매수 차트 (단위 : 억원)
          </h6>
        </div>
      </div>
    </div>
    <div class="p-4 flex-auto">
      <!-- Chart -->
      <div class="relative h-350-px">
        <canvas id="chart"></canvas>
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
      default: ""
    }
  },
  data() {
    return {
      ctx: null,
      chart: null,
      searchFromDate: "",
      searchToDate: "",
      data: []
    }
  },
  mounted: function () {
    this.searchToDate = this.$moment().toDate();
    this.searchFromDate = this.$moment(this.searchToDate).subtract('180', 'days').toDate();

    if (this.market) {
      this.getDailyData();
    }
  },
  methods: {
    async getDailyData() {
      this.axios.get("/investor/daily", { params: this.searchData }).then(response => {
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
              label: "개인",
              backgroundColor: "#4c51bf",
              borderColor: "#4c51bf",
              data: this.personalData,
              fill: false,
            },
            {
              label: "외국인",
              backgroundColor: "#CB4335",
              borderColor: "#CB4335",
              data: this.foreginerData,
              fill: false,
            },
            {
              label: "기관",
              backgroundColor: "#F1C40F",
              borderColor: "#F1C40F",
              data: this.agencyData,
              fill: false,
            },
          ],
        },
        options: {
          maintainAspectRatio: false,
          responsive: true,
          title: {
            display: false,
            text: "투자자별 일일 순매수 차트",
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

      if (!this.ctx) {
        this.ctx = document.getElementById("chart").getContext("2d");
      }

      if (!this.chart) {
        this.chart = new Chart(this.ctx, config);
      } else {
        this.chart.update();
      }
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
    personalData() {
      return this.data.map(data => data.personal);
    },
    foreginerData() {
      return this.data.map(data => data.foreginer);
    },
    agencyData() {
      return this.data.map(data => data.agency);
    }
  }
}
</script>
