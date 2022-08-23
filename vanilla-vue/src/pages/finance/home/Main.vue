<template>
  <div>
    <PageTitle />

    <div class="row">
      <div class="col-md-6">
        <div class="row">
          <div class="col-xs-6">
            <MarketSise ref="MarketSise"
              code="kospi"
            />
          </div>
        </div>
      </div>
      <div class="col-md-6">
      </div>
    </div>
  </div>
</template>

<script>
import PageTitle from '@/components/page-title/PageTitle.vue'
import MarketSise from './MarketSise.vue'

export default {
  components: {
    PageTitle,
    MarketSise
  },
  created() {
    
  },
  methods: {
    
  }
}
</script>