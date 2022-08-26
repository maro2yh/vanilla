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
          <apexchart ref="jisuChart" type="line" :options="chartOptions" :series="chartSeries"></apexchart>
        </div>
      </form>
    </panel>
  </div>
</template>

<script>
// import AppOptions from '@/config/AppOptions.vue'
import { getMarketDaily } from '@/assets/api/finance-api.js'

export default {
  props: ['code'],
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
          maxDate: false
        }
      },
      categories: [],
      data: [],
      chartOptions: {
        xaxis: {
          categories: []
        }
      },
      chartSeries: [{
        name: '지수',
        data: []
      }]
    }
  },
  created() {
    this.form.endDate = this.$moment().toDate()
    this.form.startDate = this.$moment(this.form.endDate).subtract('1', 'years').toDate()
    this.getDaily()
  },
  methods: {
    async getDaily() {
      this.categories = []
      this.data = []

      const apiResult = await getMarketDaily(this.code,
        this.$moment(this.form.startDate).format('YYYYMMDD'),
        this.$moment(this.form.endDate).format('YYYYMMDD')
      )

      if (apiResult.data) {
        apiResult.data.forEach((data) => {
          this.categories.push(data.date)
          this.data.push(data.jisu)
        })
      }

      this.$refs.jisuChart.updateOptions({
        xaxis: {
          categories: this.categories,
          tickAmount: 6
        }
      })

      this.chartSeries = [{
        data: this.data
      }]
    },
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
