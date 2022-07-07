<template>
  <div class="row">
    <div class="col-xl-12">
      <div class="navigator-box">
        <b-tabs>
          <b-tab title="Navigator" active>
            <vue-custom-scrollbar class="navigator-body">
              <NavigatorToolbar />
            </vue-custom-scrollbar>
          </b-tab>
          <b-tab title="Second"><p>I'm the second tab</p></b-tab>
        </b-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import NavigatorToolbar from '@/pages/db/NavigatorToolbar.vue'

export default {
  components: {
    NavigatorToolbar
  }
}
</script>

<style scoped>
.navigator-body {
  height: 100%;
  width: 100%;
  background-color: #fff;
}
</style>