<template>
	<div>
		<!-- BEGIN breadcrumb -->
		<ol class="breadcrumb float-xl-end">
			<li class="breadcrumb-item"><a href="javascript:;">Home</a></li>
			<li class="breadcrumb-item"><a href="javascript:;">Chart</a></li>
			<li class="breadcrumb-item active">Chart JS</li>
		</ol>
		<!-- END breadcrumb -->
		<!-- BEGIN page-header -->
		<h1 class="page-header">Chart JS <small>header small text goes here...</small></h1>
		<!-- END page-header -->
		<!-- BEGIN row -->
		<div class="row">
			<!-- BEGIN col-6 -->
			<div class="col-xl-6">
				<!-- BEGIN panel -->
				<panel title="Line Chart">
					<p>
						A line chart is a way of plotting data points on a line.
						Often, it is used to show trend data, and the comparison of two data sets.
					</p>
					<line-chart :data="lineChart.data" :options="lineChart.options" class="h-250px"></line-chart>
					<div class="hljs-wrapper" slot="outsideBody" v-highlight>
						<pre class="html"><code>&lt;line-chart :data="lineChart.data" :options="lineChart.options" class="h-250px"&gt;&lt;/line-chart&gt;
          
&lt;script&gt;
import AppOptions from '../config/AppOptions.vue'
import LineChart from '../components/vue-chartjs/LineChart'

export default {
  components: {
    LineChart
  },
  data () {
    var randomScalingFactor = function() { 
      return Math.round(Math.random()*100)
    };
    return {
      lineChart: {
        data: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
          datasets: [{
              label: 'Dataset 1',
              borderColor: AppOptions.color.blue,
              pointBackgroundColor: AppOptions.color.blue,
              pointRadius: 2,
              borderWidth: 2,
              backgroundColor: 'rgba('+ AppOptions.color.blueRgb + ', 0.3)',
              data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
          }, {
              label: 'Dataset 2',
              borderColor: AppOptions.color.componentColor,
              pointBackgroundColor: AppOptions.color.componentColor,
              pointRadius: 2,
              borderWidth: 2,
              backgroundColor: 'rgba('+ AppOptions.color.componentColorRgb + ', 0.3)',
              data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
          }]
        },
        options: {
          responsive: true, 
          maintainAspectRatio: false,
          hover: {
            mode: 'nearest',
            intersect: true
          },
          tooltips: {
            mode: 'index',
            intersect: false,
          },
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero:true,
                max: 100
              }
            }]
          }
        }
      }
    }  
  }
}
&lt;/script&gt;</code></pre>
					</div>
				</panel>
				<!-- END panel -->
			</div>
			<!-- END col-6 -->
			<!-- BEGIN col-6 -->
			<div class="col-xl-6">
				<!-- BEGIN panel -->
				<panel title="Bar Chart">
					<p>
						A bar chart is a way of showing data as bars.
						It is sometimes used to show trend data, and the comparison of multiple data sets side by side.
					</p>
					<bar-chart :data="barChart.data" :options="barChart.options" class="h-250px"></bar-chart>
					<div class="hljs-wrapper" slot="outsideBody" v-highlight>
						<pre class="html"><code>&lt;bar-chart :data="barChart.data" :options="barChart.options" class="h-250px"&gt;&lt;/bar-chart&gt;
          
&lt;script&gt;
import AppOptions from '../config/AppOptions.vue'
import BarChart from '../components/vue-chartjs/BarChart'

export default {
  components: {
    BarChart
  },
  data () {
    var randomScalingFactor = function() { 
      return Math.round(Math.random()*100)
    };
    return {
      barChart: {
        data: {
          labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
          datasets: [{
            label: 'Dataset 1',
            borderWidth: 2,
            borderColor: AppOptions.color.purple,
            backgroundColor: 'rgba('+ AppOptions.color.purpleRgb + ', 0.3)',
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
          }, {
            label: 'Dataset 2',
            borderWidth: 2,
            borderColor: AppOptions.color.componentColor,
            backgroundColor: 'rgba('+ AppOptions.color.componentColorRgb + ', 0.3)',
            data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
          }]
        },
        options: {
          responsive: true, 
          maintainAspectRatio: false
        }
      }
    }  
  }
}
&lt;/script&gt;</code></pre>
					</div>
				</panel>
				<!-- END panel -->
			</div>
			<!-- END col-6 -->
		</div>
		<!-- END row -->
		<!-- BEGIN row -->
		<div class="row">
			<!-- BEGIN col-6 -->
			<div class="col-xl-6">
				<!-- BEGIN panel -->
				<panel title="Radar Chart">
					<p>
						A radar chart is a way of showing multiple data points and the variation between them.
						They are often useful for comparing the points of two or more different data sets.
					</p>
					<radar-chart :data="radarChart.data" :options="radarChart.options" class="h-250px"></radar-chart>
					<div class="hljs-wrapper" slot="outsideBody" v-highlight>
						<pre class="html"><code>&lt;radar-chart :data="radarChart.data" :options="radarChart.options" class="h-250px"&gt;&lt;/radar-chart&gt;
          
&lt;script&gt;
import AppOptions from '../config/AppOptions.vue'
import RadarChart from '../components/vue-chartjs/RadarChart'

export default {
  components: {
    RadarChart
  },
  data () {
    return {
      radarChart: {
        data: {
          labels: ['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'],
          datasets: [{
            label: 'Dataset 1',
            borderWidth: 2,
            borderColor: AppOptions.color.red,
            pointBackgroundColor: AppOptions.color.red,
            pointRadius: 2,
            backgroundColor: 'rgba('+ AppOptions.color.redRgb + ', 0.2)',
            data: [65,59,90,81,56,55,40]
          }, {
            label: 'Dataset 2',
            borderWidth: 2,
            borderColor: AppOptions.color.componentColor,
            pointBackgroundColor: AppOptions.color.componentColor,
            pointRadius: 2,
            backgroundColor: 'rgba('+ AppOptions.color.componentColorRgb + ', 0.2)',
            data: [28,48,40,19,96,27,100]
          }]
        },
        options: {
          responsive: true, 
          maintainAspectRatio: false
        }
      }
    }  
  }
}
&lt;/script&gt;</code></pre>
					</div>
				</panel>
				<!-- END panel -->
			</div>
			<!-- END col-6 -->
			<!-- BEGIN col-6 -->
			<div class="col-xl-6">
				<!-- BEGIN panel -->
				<panel title="Polar Area Chart">
					<p>
						Polar area charts are similar to pie charts, but each segment has the same angle - the radius of the segment 
						differs depending on the value.
					</p>
					<polar-area-chart :data="polarAreaChart.data" :options="polarAreaChart.options" class="h-250px"></polar-area-chart>
					<div class="hljs-wrapper" slot="outsideBody" v-highlight>
						<pre class="html"><code>&lt;polar-area-chart :data="polarAreaChart.data" :options="polarAreaChart.options" class="h-250px"&gt;&lt;/polar-area-chart&gt;

&lt;script&gt;
import AppOptions from '../config/AppOptions.vue'
import PolarAreaChart from '../components/vue-chartjs/PolarAreaChart'

export default {
  components: {
    PolarAreaChart
  },
  data () {
    return {
      polarAreaChart: {
        data: {
          labels: ['Dataset 1', 'Dataset 2', 'Dataset 3', 'Dataset 4', 'Dataset 5'],
          datasets: [{
            data: [300, 160, 100, 200, 120],
            backgroundColor: ['rgba('+ AppOptions.color.purpleRgb + ', 0.7)', 'rgba('+ AppOptions.color.blueRgb + ', 0.7)', 'rgba('+ AppOptions.color.tealRgb + ', 0.7)', 'rgba('+ AppOptions.color.gray500 + ', 0.7)', 'rgba('+ AppOptions.color.componentColorRgb + ', 0.7)'],
            borderColor: [AppOptions.color.purple, AppOptions.color.blue, AppOptions.color.teal, AppOptions.color.gray500, AppOptions.color.componentColor],
            borderWidth: 2,
            label: 'My dataset'
          }]
        },
        options: {
          responsive: true, 
          maintainAspectRatio: false
        }
      }
    }  
  }
}
&lt;/script&gt;</code></pre>
					</div>
				</panel>
				<!-- END panel -->
			</div>
			<!-- END col-6 -->
		</div>
		<!-- END row -->
		<!-- BEGIN row -->
		<div class="row">
			<!-- BEGIN col-6 -->
			<div class="col-md-6">
				<!-- BEGIN panel -->
				<panel title="Pie Chart">
					<p>
						Pie and doughnut charts are probably the most commonly used chart there are. They are divided into segments, the arc of each segment shows the proportional value of each piece of data.
					</p>
					<pie-chart :data="pieChart.data" :options="pieChart.options" class="h-250px"></pie-chart>
					<div class="hljs-wrapper" slot="outsideBody" v-highlight>
						<pre class="html"><code>&lt;pie-chart :data="pieChart.data" :options="pieChart.options" class="h-250px"&gt;&lt;/pie-chart&gt;

&lt;script&gt;
import AppOptions from '../config/AppOptions.vue'
import PieChart from '../components/vue-chartjs/PieChart'

export default {
  components: {
    PieChart,
  },
  data () {
    return {
      pieChart: {
        data: {
          labels: ['Dataset 1', 'Dataset 2', 'Dataset 3', 'Dataset 4', 'Dataset 5'],
          datasets: [{
            data: [300, 50, 100, 40, 120],
            backgroundColor: ['rgba('+ AppOptions.color.redRgb + ', 0.7)', 'rgba('+ AppOptions.color.orange + ', 0.7)', 'rgba('+ AppOptions.color.gray200 + ', 0.7)', 'rgba('+ AppOptions.color.gray500 + ', 0.7)', 'rgba('+ AppOptions.color.componentColorRgb + ', 0.7)'],
            borderColor: [AppOptions.color.red, AppOptions.color.orange, AppOptions.color.gray200, AppOptions.color.gray500, AppOptions.color.componentColor],
            borderWidth: 2,
            label: 'My dataset'
          }]
        },
        options: {
          responsive: true, 
          maintainAspectRatio: false
        }
      }
    }  
  }
}
&lt;/script&gt;</code></pre>
					</div>
				</panel>
				<!-- END panel -->
			</div>
			<!-- END col-6 -->
			<!-- BEGIN col-6 -->
			<div class="col-md-6">
				<!-- BEGIN panel -->
				<panel title="Doughnut Chart">
					<p>
						Pie and doughnut charts are registered under two aliases in the Chart core. Other than their different default value, and different alias, they are exactly the same.
					</p>
					<doughnut-chart :data="doughnutChart.data" :options="doughnutChart.options" class="h-250px"></doughnut-chart>
					<div class="hljs-wrapper" slot="outsideBody" v-highlight>
						<pre class="html"><code>&lt;doughnut-chart :data="doughnutChart.data" :options="doughnutChart.options" class="h-250px"&gt;&lt;/doughnut-chart&gt;
&lt;script&gt;
import AppOptions from '../config/AppOptions.vue'
import DoughnutChart from '../components/vue-chartjs/DoughnutChart'

export default {
  components: {
    DoughnutChart
  },
  data () {
    return {
      doughnutChart: {
        data: {
          labels: ['Dataset 1', 'Dataset 2', 'Dataset 3', 'Dataset 4', 'Dataset 5'],
          datasets: [{
            data: [300, 50, 100, 40, 120],
            backgroundColor: ['rgba('+ AppOptions.color.purpleRgb + ', 0.7)', 'rgba('+ AppOptions.color.blueRgb + ', 0.7)', 'rgba('+ AppOptions.color.tealRgb + ', 0.7)', 'rgba('+ AppOptions.color.gray500 + ', 0.7)', 'rgba('+ AppOptions.color.componentColorRgb + ', 0.7)'],
            borderColor: [AppOptions.color.purple, AppOptions.color.blue, AppOptions.color.teal, AppOptions.color.gray500, AppOptions.color.componentColor],
            borderWidth: 2,
            label: 'My dataset'
          }]
        },
        options: {
          responsive: true, 
          maintainAspectRatio: false
        }
      }
    }  
  }
}
&lt;/script&gt;</code></pre>
					</div>
				</panel>
				<!-- END panel -->
			</div>
			<!-- END col-6 -->
		</div>
		<!-- END row -->
	</div>
</template>

<script>
import AppOptions from '../config/AppOptions.vue'
import LineChart from '../components/vue-chartjs/LineChart'
import BarChart from '../components/vue-chartjs/BarChart'
import RadarChart from '../components/vue-chartjs/RadarChart'
import PolarAreaChart from '../components/vue-chartjs/PolarAreaChart'
import PieChart from '../components/vue-chartjs/PieChart'
import DoughnutChart from '../components/vue-chartjs/DoughnutChart'
import { Chart } from 'chart.js'

export default {
	components: {
		LineChart,
		BarChart,
		RadarChart,
		PolarAreaChart,
		PieChart,
		DoughnutChart
	},
	data () {
		var randomScalingFactor = function() { 
			return Math.round(Math.random()*100)
		};
		
		Chart.defaults.global.defaultColor = 'rgba('+ AppOptions.color.componentColorRgb + ', .65)';
		Chart.defaults.global.defaultFontFamily = AppOptions.font.family;
		Chart.defaults.global.defaultFontStyle = 600;
		Chart.defaults.scale.gridLines.color = 'rgba('+ AppOptions.color.componentColorRgb + ', .15)';
		Chart.defaults.scale.gridLines.zeroLineColor = 'rgba('+ AppOptions.color.componentColorRgb + ', .15)';
		
		return {
			appOptions: AppOptions,
			lineChart: {
				data: {
					labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
					datasets: [{
							label: 'Dataset 1',
							borderColor: AppOptions.color.blue,
							pointBackgroundColor: AppOptions.color.blue,
							pointRadius: 2,
							borderWidth: 2,
							backgroundColor: 'rgba('+ AppOptions.color.blueRgb +', 0.3)',
							data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
					}, {
							label: 'Dataset 2',
							borderColor: AppOptions.color.componentColor,
							pointBackgroundColor: AppOptions.color.componentColor,
							pointRadius: 2,
							borderWidth: 2,
							backgroundColor: 'rgba('+ AppOptions.color.componentColorRgb +', 0.3)',
							data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
					}]
				},
				options: {
					responsive: true, 
					maintainAspectRatio: false,
					hover: {
						mode: 'nearest',
						intersect: true
					},
					tooltips: {
						mode: 'index',
						intersect: false,
					},
					scales: {
						yAxes: [{
							ticks: {
								beginAtZero:true,
								max: 100
							}
						}]
					}
				}
			},
			barChart: {
				data: {
					labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
					datasets: [{
						label: 'Dataset 1',
						borderWidth: 2,
						borderColor: AppOptions.color.purple,
						backgroundColor: 'rgba('+ AppOptions.color.purpleRgb +', 0.3)',
						data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
					}, {
						label: 'Dataset 2',
						borderWidth: 2,
						borderColor: AppOptions.color.componentColor,
						backgroundColor: 'rgba('+ AppOptions.color.componentColorRgb +', 0.3)',
						data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
					}]
				},
				options: {
					responsive: true, 
					maintainAspectRatio: false
				}
			},
			radarChart: {
				data: {
					labels: ['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'],
					datasets: [{
						label: 'Dataset 1',
						borderWidth: 2,
						borderColor: AppOptions.color.red,
						pointBackgroundColor: AppOptions.color.red,
						pointRadius: 2,
						backgroundColor: 'rgba('+ AppOptions.color.redRgb +', 0.2)',
						data: [65,59,90,81,56,55,40]
					}, {
						label: 'Dataset 2',
						borderWidth: 2,
						borderColor: AppOptions.color.componentColor,
						pointBackgroundColor: AppOptions.color.componentColor,
						pointRadius: 2,
						backgroundColor: 'rgba('+ AppOptions.color.componentColorRgb +', 0.2)',
						data: [28,48,40,19,96,27,100]
					}]
				},
				options: {
					responsive: true, 
					maintainAspectRatio: false
				}
			},
			polarAreaChart: {
				data: {
					labels: ['Dataset 1', 'Dataset 2', 'Dataset 3', 'Dataset 4', 'Dataset 5'],
					datasets: [{
						data: [300, 160, 100, 200, 120],
						backgroundColor: ['rgba('+ AppOptions.color.purpleRgb + ', 0.7)', 'rgba('+ AppOptions.color.blueRgb + ', 0.7)', 'rgba('+ AppOptions.color.tealRgb + ', 0.7)', 'rgba('+ AppOptions.color.gray500 + ', 0.7)', 'rgba('+ AppOptions.color.componentColorRgb + ', 0.7)'],
						borderColor: [AppOptions.color.purple, AppOptions.color.blue, AppOptions.color.teal, AppOptions.color.gray500, AppOptions.color.componentColor],
						borderWidth: 2,
						label: 'My dataset'
					}]
				},
				options: {
					responsive: true, 
					maintainAspectRatio: false
				}
			},
			pieChart: {
				data: {
					labels: ['Dataset 1', 'Dataset 2', 'Dataset 3', 'Dataset 4', 'Dataset 5'],
					datasets: [{
						data: [300, 50, 100, 40, 120],
						backgroundColor: ['rgba('+ AppOptions.color.redRgb + ', 0.7)', 'rgba('+ AppOptions.color.orange + ', 0.7)', 'rgba('+ AppOptions.color.gray200 + ', 0.7)', 'rgba('+ AppOptions.color.gray500 + ', 0.7)', 'rgba('+ AppOptions.color.componentColorRgb + ', 0.7)'],
						borderColor: [AppOptions.color.red, AppOptions.color.orange, AppOptions.color.gray200, AppOptions.color.gray500, AppOptions.color.componentColor],
						borderWidth: 2,
						label: 'My dataset'
					}]
				},
				options: {
					responsive: true, 
					maintainAspectRatio: false
				}
			},
			doughnutChart: {
				data: {
					labels: ['Dataset 1', 'Dataset 2', 'Dataset 3', 'Dataset 4', 'Dataset 5'],
					datasets: [{
						data: [300, 50, 100, 40, 120],
						backgroundColor: ['rgba('+ AppOptions.color.purpleRgb + ', 0.7)', 'rgba('+ AppOptions.color.blueRgb + ', 0.7)', 'rgba('+ AppOptions.color.tealRgb + ', 0.7)', 'rgba('+ AppOptions.color.gray500 + ', 0.7)', 'rgba('+ AppOptions.color.componentColorRgb + ', 0.7)'],
						borderColor: [AppOptions.color.purple, AppOptions.color.blue, AppOptions.color.teal, AppOptions.color.gray500, AppOptions.color.componentColor],
						borderWidth: 2,
						label: 'My dataset'
					}]
				},
				options: {
					responsive: true, 
					maintainAspectRatio: false
				}
			}
		}	
	}
}
</script>