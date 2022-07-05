<template>
  <div class="browser">
    <!-- <pageTitle /> -->

    <div class="row">
      <div class="col-xl-12">
        <b-tabs nav-wrapper-class="nav nav-tabs"
          content-class="panel mb-0 p-3 rounded-0"
          v-on:activate-tab="tabActivated"
        >
          <b-tab active>
            <template slot="title">
              <button type="button"
                class="btn btn-link btn-xs"
                @click="onClickNewConnection"
              >
                <span class="d-sm-block d-none"><i class="fa fa-plus"></i></span>
              </button>
            </template>

            <NewConnect ref="NewConnect" />
          </b-tab>
        </b-tabs>
      </div>
    </div>
  </div>
</template>

<script>
// import pageTitle from '@/components/page-title/PageTitle.vue'
import NewConnect from '@/pages/db/NewConnect.vue'
// import { Layout } from 'vue-split-layout'

const layouts = [
  {
    dir: 'horizontal',
    first: 0,
    second: 1,
    split: '30%'
    // second: 2,
    // split: '70%'
  }
]

export default {
  components: {
    // pageTitle,
    NewConnect
    // Layout
  },
  data() {
    return {
      state: {
        extraStyle: false,
        edit: false,
        resize: true,
        splits: layouts[0],
        layoutN: 0
      }
    }
  },
  methods: {
    tabActivated(newTabIndex, oldTabIndex, event) {
      event.preventDefault()
    },
    onClickNewConnection() {
      console.log('onClickNewConnection')
    }
  }
}
</script>

<style scoped>
.browser {
  width: 100%;
  height: calc(100vh - 80px);
}

.dir {
  height: calc(100vh - 1px);
}
</style>