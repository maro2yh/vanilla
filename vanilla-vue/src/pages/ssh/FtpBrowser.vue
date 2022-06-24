<template>
  <div class="browser">
    <Layout
      :edit="state.edit"
      :resize="state.resize"
      :splits="state.splits"
    >
      <div class="nopane dir">
        <FtpBrowserLocal />
      </div>
      <div class="nopane dir">
        <FtpBrowserRemote />
      </div>
      <div class="nopane bottom">
        <b-tabs nav-wrapper-class="nav nav-tabs" content-class="p-15px bg-white mb-4">
          <b-tab active>
            <template slot="title">
							<span class="d-sm-none">Tab 1</span>
							<span class="d-sm-block d-none">Console</span>
						</template>
            <div class="bottom-body">

            </div>
          </b-tab>
        </b-tabs>
      </div>
    </Layout>
  </div>
</template>

<script>
import { Layout } from 'vue-split-layout'
import AppOptions from '@/config/AppOptions.vue'
import FtpBrowserLocal from './FtpBrowserLocal.vue'
import FtpBrowserRemote from './FtpBrowserRemote.vue'

const layouts = [
  {
    dir: 'vertical',
    first: {
      dir: 'horizontal',
      first: 0,
      second: 1,
      split: '50%'
    },
    second: 2,
    split: '70%'
  }
]

export default {
  components: {
    Layout,
    FtpBrowserLocal,
    FtpBrowserRemote
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
  created() {
    AppOptions.appHeaderNone = true
    AppOptions.appSidebarNone = true
  },
  mounted() {
  }
}
</script>

<style scoped>
.browser {
  width: 100%;
  height: calc(100vh - 60px);
}

.dir {
  height: 100%;
}

.bottom {
  height: 100%;
}

.bottom-body {
  height: calc(100vh - 10px);
}
</style>
