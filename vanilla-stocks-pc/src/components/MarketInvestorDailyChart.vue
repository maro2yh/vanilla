<template>
  <div>
    <q-card flat bordered>
      <q-card-section>
        <div class="text-h6">투자자별 일별 순매수</div>
        
        <q-card-section style="height: 350px;">
          <vue3-chart-js
            ref="chartRef"
            :id="chart.id"
            :type="chart.type"
            :data="chart.data"
            :options="chart.options"
          />
        </q-card-section>
      </q-card-section>
    </q-card>
  </div>
</template>
<script>
import { ref } from 'vue'
import Vue3ChartJs from '@j-t-mcc/vue3-chartjs'
import axios from  "@/assets/js/axios";

export default {
  components: {
    Vue3ChartJs,
  },
  setup() {
    const chartRef = ref(null)
    const moment = require('moment')
    const toDate = moment(new Date())
    const fromDate = moment(toDate).subtract('1', 'months')

    const chart = {
      type: 'bar',
      data: {
        labels: [],
        datasets: [],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false
      }
    }

    const update = async(market) => {
      const params = {
        searchFromDate: moment(fromDate).format('YYYYMMDD'),
        searchToDate: moment(toDate).format('YYYYMMDD')
      }

      const dailyData = await axios.request({
        url: market + '/investor/daily',
        method: 'get',
        params
      })

      console.log(dailyData)

      const labels = dailyData.data.map(data => data.date)
      const personal = dailyData.data.map(data => data.personal)
      const agency = dailyData.data.map(data => data.agency)
      const foreginer = dailyData.data.map(data => data.foreginer)

      chart.data.labels = labels
      chart.data.datasets = [
        {
          label: '개인',
          data: personal,
          fill: false,
          borderColor: '#C39BD3',
          backgroundColor: '#C39BD3'
        },
        {
          label: '기관',
          data: agency,
          fill: false,
          borderColor: '#5DADE2',
          backgroundColor: '#5DADE2'
        },
        {
          label: '외국인',
          data: foreginer,
          fill: false,
          borderColor: '#EB984E',
          backgroundColor: '#EB984E'
        }
      ]

      chartRef.value.update(250)
    }

    return {
      chartRef,
      chart,
      toDate,
      fromDate,
      update
    }
  },
  mounted() {
    this.$emit('onReady')
  }
}
</script>