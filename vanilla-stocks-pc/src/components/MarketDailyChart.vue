<template>
  <div>
    <q-card flat bordered>
      <q-card-section>
        <div class="text-h6">일별 시세</div>
        
        <q-card-section style="height: 350px;">
          <vue3-chart-js
            ref="chartRef"
            :id="lineChart.id"
            :type="lineChart.type"
            :data="lineChart.data"
            :options="lineChart.options"
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

    const lineChart = {
      id: 'line-chart',
      type: "line",
      data: {
        labels: [],
        datasets: [{
          label: '지수',
          data: []
        }],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false
      },
    };

    const update = async(market, fromDate, toDate) => {
      const params = {
        searchFromDate: fromDate,
        searchToDate: toDate
      };

      const jisuData = await axios.request({
        url: market + '/daily',
        method: 'get',
        params
      })

      const labels = jisuData.data.map(data => data.date)
      const datas = jisuData.data.map(data => data.jisu)

      lineChart.data.labels = labels;
      lineChart.data.datasets = [
        {
          label: '지수',
          data: datas,
          fill: false,
          borderColor: '#41B883',
          backgroundColor: 'black',
          borderWidth: 1,
        }
      ];

      chartRef.value.update(250);
    }

    return {
      lineChart,
      chartRef,
      update
    };
  },
  mounted() {
    this.$emit('onReady')
  }
}
</script>