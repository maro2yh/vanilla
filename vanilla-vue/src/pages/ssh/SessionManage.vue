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
            @onSave="onSaveNewSession"
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

export default {
  components: {
    pageTitle,
    SessionList,
    NewSession
  },
  created() {
    SessionFile.createRootFolder()
  },
  methods: {
    onClickTreeNode(node) {
      if (node.id === 1) {
        this.$refs.NewSession.clearFields()
      } else if (node.isLeaf) {
        const sessionData = SessionFile.find(node.id)
        console.log(sessionData)
      }
    },
    onSaveNewSession(data) {
      this.$refs.SessionList.createNewSession(data)
    },
    findSessionToFile(data, id, session) {
      if (session !== null) {
        return session
      }

      if (data.id !== undefined && data.id === id) {
        session = data
        return session
      }

      if (data.children !== undefined && data.children.length > 0) {
        data.children.forEach((d) => {
          this.findSessionToFile(d, id, session)
        })
      }
    }
  }
}
</script>