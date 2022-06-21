<template>
  <div>
    <pageTitle />

    <div class="row">
      <div class="col-md-6">
        <SessionList ref="SessionList"
          @onClickTreeNode="onClickTreeNode"
        />
      </div>
      <div class="col-md-6">
          <NewSession ref="NewSession"
            @onSaveSession="onSaveSession"
          />
      </div>
    </div>
  </div>
</template>

<script>
import pageTitle from '@/components/page-title/PageTitle.vue'
import SessionList from '@/pages/ssh/SessionList.vue'
import NewSession from '@/pages/ssh/NewSession.vue'
import SessionFile from '@/pages/ssh/SessionFile.js'
import CredentialsFile from '@/pages/ssh/CredentialsFile.js'

export default {
  components: {
    pageTitle,
    SessionList,
    NewSession
  },
  data() {
    return {
      selectedData: null
    }
  },
  created() {
    SessionFile.createRootFolder()
    CredentialsFile.createRootFolder()
  },
  methods: {
    onClickTreeNode(params) {
      this.selectedData = null

      if (params.id === 1) {
        this.$refs.NewSession.setNewSession()
        this.selectedData = params
      } else {
        const fileData = SessionFile.read()

        for (let i = 0; i < fileData.sessions.length; i++) {
          if (fileData.sessions[i].id === params.id) {
            this.$refs.NewSession.setSessionData(params.id, fileData.sessions[i])
            this.selectedData = params
            break
          } else if (!fileData.sessions[i].isLeaf) {
            this.clickTreeNode(fileData.sessions[i], params)
          }
        }
      }
    },
    onSaveSession(data) {
      this.selectedData = null   
      const sessionsData = SessionFile.read()

      for (let sessions of sessionsData.sessions) {
        if (sessions.isLeaf && sessions.id === data.id) {
          sessions.name = data.data.sessionName
          sessions.session = {
            sessionName: data.data.sessionName,
            hostname: data.data.hostname,
            port: data.data.port,
            credentials: data.data.credentials
          }
          break
        } else if (!sessions.isLeaf) {
          this.saveSession(sessions, data)
        }
      }

      SessionFile.write(sessionsData)

      this.$refs.SessionList.selectedNode.changeName(data.data.sessionName)
    },
    clickTreeNode(parent, params) {
      if (this.selectedData !== null) {
        return
      }

      for (let i = 0; i < parent.children.length; i++) {
        if (parent.children[i].id === params.id) {
          this.$refs.NewSession.setSessionData(params.id, parent.children[i])
          this.selectedData = params
          break
        } else if (!parent.children[i].isLeaf) {
          this.clickTreeNode(parent.children[i], params)
        }
      }
    },
    saveSession(parent, data) {
      if (this.selectedData !== null) {
        return
      }

      for (let sessions of parent.children) {
        if (sessions.isLeaf && sessions.id === data.id) {
          sessions.name = data.data.sessionName
          sessions.session = {
            sessionName: data.data.sessionName,
            hostname: data.data.hostname,
            port: data.data.port,
            credentials: data.data.credentials
          }
          break
        } else if (!sessions.isLeaf) {
          this.saveSession(sessions, data)
        }
      }
    }
  }
}
</script>