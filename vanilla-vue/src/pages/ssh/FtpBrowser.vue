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
        <FtpBrowserRemote
          ref="FtpBrowserRemote"
          :id="id"
          :title="sessionName"
          :session="session"
          @onReady="onReadyRemote"
        />
      </div>
    </Layout>
  </div>
</template>

<script>
import { Layout } from 'vue-split-layout'
import AppOptions from '@/config/AppOptions.vue'
import FtpBrowserLocal from './FtpBrowserLocal.vue'
import FtpBrowserRemote from './FtpBrowserRemote.vue'
import SessionFile from './SessionFile.js'

const layouts = [
  {
    dir: 'horizontal',
    first: 0,
    second: 1,
    split: '50%'
    // second: 2,
    // split: '70%'
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
      id: '',
      sessionName: '',
      session: null,
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
    this.id = Number(this.$route.query.id)
    this.getSession()
  },
  methods: {
    getSession() {
      const fileData = SessionFile.read()

      for (let session of fileData.sessions) {
        if (this.session !== null) {
          break
        }

        if (session.isLeaf && session.id === this.id) {
          this.session = session
          break
        } else if (!session.isLeaf) {
          this.findSession(session)
        }
      }

      this.sessionName = this.session.name
      this.session = this.session.session
    },
    findSession(parent) {
      if (this.session !== null) {
        return
      }

      for (let child of parent.children) {
        if (this.session !== null) {
          break
        }

        if (child.isLeaf && child.id === this.id) {
          this.session = child
          break
        } else if (!child.isLeaf) {
          this.findSession(child)
        }
      }
    },
    onReadyRemote() {
      // this.$refs.FtpBrowserRemote.connect()
    }
  }
}
</script>

<style scoped>
.browser {
  width: 100%;
  height: calc(100vh - 40px);
}

.dir {
  height: calc(100vh - 1px);
}
</style>
