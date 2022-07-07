<template>
  <div class="browser">
    <!-- <pageTitle /> -->

    <Layout
      :edit="state.edit"
      :resize="state.resize"
      :splits="state.splits"
    >
      <div class="nopane dir">
        <Navigator />
      </div>
      <div class="nopane dir">
        
      </div>
    </Layout>
  </div>
</template>

<script>
// import pageTitle from '@/components/page-title/PageTitle.vue'
// import NewSession from '@/pages/db/NewSession.vue'
import { Layout } from 'vue-split-layout'
import Navigator from '@/pages/db/Navigator.vue'

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
    // NewSession,
    Layout,
    Navigator
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
  },
  methods: {
    tabActivated(newTabIndex, oldTabIndex, event) {
      console.log(newTabIndex, oldTabIndex)
      event.preventDefault()
    },
    onClickNewConnection() {
      this.$refs.NewSession.reset()
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
  border: 1px solid;
  padding: 5px;
}
</style>