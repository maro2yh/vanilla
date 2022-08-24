<template>
  <div>
    <panel noHeader="true" noButton="true">
      <form class="form-horizontal">
        <div class="form-group row">
          <div class="col-md-8">
            <div class="row gx-3">
              <div class="col-3" style="position: relative">
                <date-picker v-model="form.startDate" :config="configs.start" ref="startDate" v-on:dp-change="onStartChange" placeholder="Start Date"></date-picker>
              </div>
              <div class="col-1" style="width: 20px; margin-top: 5px;">~</div>
              <div class="col-3" style="position: relative">
                <date-picker v-model="form.endDate" :config="configs.end" ref="endDate" v-on:dp-change="onEndChange" placeholder="End Date"></date-picker>
              </div>
            </div>
          </div>
        </div>

        <div>
          <apexchart type="line" :options="mixedChart.options" :series="mixedChart.series"></apexchart>
        </div>
      </form>
    </panel>
  </div>
</template>

<script>
import AppOptions from '@/config/AppOptions.vue'

export default {
  data() {
    return {
      form: {
        startDate: null,
        endDate: null
      },
      configs: {
        start: {
          format: 'YYYY-MM-DD',
          useCurrent: false,
          showClear: true,
          showClose: true,
          minDate: false,
          maxDate: new Date()
        },
        end: {
          format: 'YYYY-MM-DD',
          useCurrent: false,
          showClear: true,
          showClose: true,
          minDate: false,
          maxDate: new Date()
        }
      },
      mixedChart: {
        options: {
          chart: {
            height: 350,
            type: 'line',
            stacked: false
          },
          dataLabels: {
            enabled: false
          },
          stroke: {
            width: [0, 0, 3]
          },
          colors: [AppOptions.color.orange, AppOptions.color.teal, AppOptions.color.orange],
          title: {
            text: 'XYZ - Stock Analysis (2012 - 2020)',
            align: 'left',
            offsetX: 110,
            style: {
              color: AppOptions.color.componentColor
            }
          },
          xaxis: {
            categories: [2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020],
            axisBorder: {
              show: true,
              color: 'rgba('+ AppOptions.color.componentColorRgb + ', .25)',
              height: 1,
              width: '100%',
              offsetX: 0,
              offsetY: -1
            },
            axisTicks: {
              show: true,
              borderType: 'solid',
              color: 'rgba('+ AppOptions.color.componentColorRgb + ', .25)',
              height: 6,
              offsetX: 0,
              offsetY: 0
            },
            labels: {
              style: {
                colors: AppOptions.color.componentColor,
                fontSize: AppOptions.font.size,
                fontFamily: AppOptions.font.family,
                fontWeight: 400,
                cssClass: 'apexcharts-xaxis-label',
              }
            }
          },
          yaxis: [{
            axisTicks: {
              show: true,
            },
            axisBorder: {
              show: true,
              color: AppOptions.color.orange
            },
            labels: {
              style: {
                color: AppOptions.color.orange
              }
            },
            title: {
              text: "Income (thousand crores)",
              style: {
                color: AppOptions.color.orange
              }
            },
            tooltip: {
              enabled: true
            }
          },
          {
            seriesName: 'Income',
            opposite: true,
            axisTicks: {
              show: true,
            },
            axisBorder: {
              show: true,
              color: AppOptions.color.teal
            },
            labels: {
              style: {
                color: AppOptions.color.teal
              }
            },
            title: {
              text: "Operating Cashflow (thousand crores)",
              style: {
                color: AppOptions.color.teal
              }
            },
          },
          {
            seriesName: 'Revenue',
            opposite: true,
            axisTicks: {
              show: true,
            },
            axisBorder: {
              show: true,
              color: AppOptions.color.orange
            },
            labels: {
              style: {
                color: AppOptions.color.orange
              },
            },
            title: {
              text: "Revenue (thousand crores)",
              style: {
                color: AppOptions.color.orange
              }
            }
          }],
          grid: {
            borderColor: 'rgba('+ AppOptions.color.componentColorRgb + ', .15)'
          },
          tooltip: {
            fixed: {
              enabled: true,
              position: 'topLeft', // topRight, topLeft, bottomRight, bottomLeft
              offsetY: 30,
              offsetX: 60
            },
          },
          legend: {
            horizontalAlign: 'left',
            offsetX: 40,
            labels: {
              colors: AppOptions.color.componentColor
            }
          }
        },
        series: [{
          name: 'Income',
          type: 'column',
          data: [1.4, 2, 2.5, 1.5, 2.5, 2.8, 3.8, 4.6]
        },
        {
          name: 'Cashflow',
          type: 'column',
          data: [1.1, 3, 3.1, 4, 4.1, 4.9, 6.5, 8.5]
        },
        {
          name: 'Revenue',
          type: 'line',
          data: [20, 29, 37, 36, 44, 45, 50, 58]
        }]
      }
    }
  },
  created() {
    this.form.endDate = this.$moment().toDate()
    this.form.startDate = this.$moment(this.form.endDate).subtract('1', 'years').toDate()
  },
  methods: {
    onStartChange(e) {
      console.log(e)
      // this.$set(this.configs.end, 'minDate', e.date || null);
    },
    onEndChange(e) {
      console.log(e)
      // this.$set(this.configs.start, 'maxDate', e.date || null);
    }
  }
}
</script>
