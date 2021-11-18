<template>
  <div class="q-pa-md row items-start q-gutter-md">
    <q-card
      class="text-white"
      :class="classes"
      style="width: 100%;"
    >
      <q-card-section>
        <div class="text-h6">
          {{ sise.jisu }}
        </div>
        <div class="text-subtitle2">
          <q-icon :name="icon" />
          {{ sise.change }} {{ sise.changeRate }}%
        </div>
      </q-card-section>
    </q-card>
  </div>
</template>
<script>
export default {
  name: 'MarketSiseCard',
  props: {
    market: {
      type: String
    }
  },
  data() {
    return {
      sise: {
        jisu: '0.0'
      },
      icon: '',
      classes: ''
    }
  },
  mounted() {
    this.$emit('onReady');
  },
  methods: {
    async update() {
      const params = {}
      this.$api.get(this.market + '/sise', params).then((response) => {
        this.sise = response.data

        if (this.sise.changeRate < 0) {
          this.icon = 'south'
          this.classes = 'bg-down'
        } else if (this.sise.changeRate > 0) {
          this.icon = 'north'
          this.classes = 'bg-up'
        } else {
          this.icon = 'remove'
        }
      })
    }
  }
}
</script>
<style lang="sass" scoped>
.bg-down
  background: radial-gradient(circle, #35a2ff 0%, #014a88 100%)

.bg-up
  background: radial-gradient(circle, #dca5aa 0%, #f32213 100%)
</style>