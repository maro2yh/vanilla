<template>
  <div>
    <panel title="Sessions" noHeader="true" noButton="true" id="panel-sessions">
      <vue-custom-scrollbar class="without-header-with-footer">
        <vue-tree-list
          @click="onClickTreeNode"
          @change-name="onChangeName"
          @delete-node="onDel"
          @add-node="onAddNode"
          :model="treeData"
          default-tree-node-name="new node"
          default-leaf-node-name="new leaf"
          v-bind:default-expanded="false"
        >
          <template v-slot:leafNameDisplay="slotProps">
            <span>
              {{ slotProps.model.name }} <span class="muted">#{{ slotProps.model.id }}</span>
            </span>
          </template>
          <span class="icon" slot="addTreeNodeIcon"><span><i class="fa fa-folder-plus" /></span></span>
          <span class="icon ms-1" slot="addLeafNodeIcon"><span><i class="fa fa-plus" /></span></span>
          <span class="icon ms-1" slot="editNodeIcon"><span><i class="fa fa-edit" /></span></span>
          <span class="icon ms-1" slot="delNodeIcon"><span><i class="fa fa-remove" /></span></span>
          <span class="icon me-1" slot="leafNodeIcon"><span><i class="fa fa-file" /></span></span>
          <span class="icon me-1" slot="treeNodeIcon"><span><i class="fa fa-folder" /></span></span>
        </vue-tree-list>
      </vue-custom-scrollbar>
      <div slot="footer" class="text-end">
        <button class="btn btn-primary btn-sm ms-5px">New Folder</button>
        <button class="btn btn-primary btn-sm ms-5px">New Site</button>
      </div>
    </panel>
  </div>
</template>

<script>
import SessionFile from '@/pages/ssh/SessionFile.js'
import { VueTreeList, Tree, TreeNode } from 'vue-tree-list2'
// import { Client } from 'ssh2'

export default {
  components: {
    VueTreeList
  },
  data() {
    return {
      client: null,
      ssh: null,
      treeHeight: 200,
      treeData: new Tree([
        {
          name: 'New Site',
          id: 1,
          pid: 0,
          dragDisabled: true,
          addTreeNodeDisabled: true,
          addLeafNodeDisabled: true,
          editNodeDisabled: true,
          delNodeDisabled: true
        }
      ]),
      fileData: [],
      selectedNode: {}
    }
  },
  created() {
  },
  mounted() {
    this.$emit('onReady')
    this.initTreeData()
  },
  methods: {
    initTreeData() {
      this.fileData = SessionFile.read()
      this.fileData.sessions.forEach((session) => {
        const node = new TreeNode(session)
        this.treeData.addChildren(node)
      })
    },
    onReadyNewSession() {

    },
    onDel(node) {
      console.log(node)
      node.remove()
    },
    onChangeName(params) {
      let data = null

      for (let i = 0; i < this.fileData.sessions.length; i++) {
        if (this.fileData.sessions[i]. id === params.id) {
          data = this.fileData.sessions[i]
          break
        }
      }

      data.name = params.newName
      SessionFile.write(this.fileData)
    },
    onAddNode(params) {
      console.log(params)
    },
    onClickTreeNode(params) {
      if (this.selectedNode && this.selectedNode.id && this.selectedNode.id === params.id) {
        return
      }

      if (this.selectedNode && this.selectedNode.id && this.selectedNode.id !== params.id) {
        document.getElementById(this.selectedNode.id).classList.remove('vtl-selected')
      }

      document.getElementById(params.id).classList.add('vtl-selected')
      this.selectedNode = params

      this.$emit('onClickTreeNode', params)
    },
    onClickAddNewFolder() {
      var node = new TreeNode({ name: 'new folder', isLeaf: false })
      if (!this.treeData.children) this.treeData.children = []
      this.treeData.addChildren(node)

      this.fileData.sessions.push({
        id: node.id,
        name: node.name,
        isLeaf: false,
        children: []
      })

      SessionFile.write(this.fileData)
    },
    createNewSession(data) {
      var node = new TreeNode({ name: data.sessionName, isLeaf: true })

      if (!this.selectedNode || !this.selectedNode.id || (this.selectedNode && this.selectedNode.id === 1)) {
        this.treeData.addChildren(node)
        this.fileData.sessions.push({
          id: node.id,
          name: node.name,
          isLeaf: true
        })
      }

      SessionFile.write(this.fileData)
    },
    goNewSessionPage() {
      this.$route.path('/ssh/new-session')
    },
    /*
    connect() {
      this.client = new Client()
      this.client.on('ready', () => {
        console.log('Client :: ready')
        this.client.shell((err, stream) => {
          if (err) throw err
          stream.on('close', () => {
            console.log('Stream :: close')
            this.client.end()
          }).on('data', (data) => {
            console.log('OUTPUT: ' + data)
          })
          stream.end('ls -l\nexit\n')
        })
      }).connect({
        host: '34.64.111.146',
        port: 22,
        username: 'yonghyun01',
        privateKey: fs.readFileSync('public/ppk/gcp_rsa_vue')
      })
    }
    */
  }
}
</script>

<style lang="less" rel="stylesheet/less">
  .vtl {
    .vtl-drag-disabled {
      /* background-color: #d0cfcf; */
      &:hover {
        background-color: #d0cfcf;
      }
    }
    .vtl-disabled {
      background-color: #d0cfcf;
    }
    .vtl-selected {
      background-color: #f3f1f1c7;
    }
  }
</style>

<style lang="less" rel="stylesheet/less" scoped>
  .icon {
    &:hover {
      cursor: pointer;
    }
  }
</style>
