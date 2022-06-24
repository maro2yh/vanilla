<template>
  <div>
    <panel title="Sessions" noHeader="true" noButton="true" id="panel-sessions">
      <vue-custom-scrollbar>
        <div class="without-header-with-footer">
          <vue-tree-list
            @click="onClickTreeNode"
            @changed-name="onChangeName"
            @delete-node="onClickDelete"
            @add-node="onClickAddNode"
            :model="treeData"
            default-tree-node-name="new folder"
            default-leaf-node-name="new site"
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
            <span class="icon me-1" slot="leafNodeIcon"><span class="text-black-100"><i class="fa fa-file" /></span></span>
            <span class="icon me-1" slot="treeNodeIcon"><span class="text-yellow"><i class="fa fa-folder" /></span></span>
          </vue-tree-list>
        </div>
      </vue-custom-scrollbar>
      <div slot="footer" class="text-end">
        <button class="btn btn-primary btn-sm ms-5px" @click="onClickNewFolder">New Folder</button>
        <button class="btn btn-primary btn-sm ms-5px" @click="onClickNewSite">New Site</button>
      </div>
    </panel>
  </div>
</template>

<script>
import SessionFile from '@/pages/ssh/SessionFile.js'
import { VueTreeList, Tree, TreeNode } from 'vue-tree-list2'

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
          isLeaf: true,
          dragDisabled: true,
          addTreeNodeDisabled: true,
          addLeafNodeDisabled: true,
          editNodeDisabled: true,
          delNodeDisabled: true
        }
      ]),
      fileData: [],
      selectedNode: null
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

        if (!session.isLeaf && session.children.length > 0) {
          this.initChildren(node, session)
        }

        this.treeData.addChildren(node)
      })
    },
    initChildren(parent, params) {
      params.children.forEach((session) => {
        const node = new TreeNode(session)

         if (!session.isLeaf && session.children.length > 0) {
          this.initChildren(node, session)
        }

        parent.addChildren(node)
      })
    },
    onClickDelete(params) {
      let delIdx = -1

      for (let i = 0; i < this.fileData.sessions.length; i++) {
        if (this.fileData.sessions[i]. id === params.id) {
          delIdx = i
          break
        } else if (!this.fileData.sessions[i].isLeaf) {
          this.deleteNode(this.fileData.sessions[i], params)
        }
      }

      if (delIdx >= 0) {
        this.fileData.sessions.splice(delIdx, 1)
      }

      params.remove()
      SessionFile.write(this.fileData)
    },
    onChangeName(params) {
      for (let i = 0; i < this.fileData.sessions.length; i++) {
        if (this.fileData.sessions[i]. id === params.id) {
          this.fileData.sessions[i].name = params.newName

          if (this.fileData.sessions[i].isLeaf) {
            this.fileData.sessions[i].session.sessionName = params.newName
          }

          break
        }  else if (!this.fileData.sessions[i].isLeaf) {
          this.changeName(this.fileData.sessions[i], params)
        }
      }

      SessionFile.write(this.fileData)
    },
    onClickAddNode(params) {
      for (let i = 0; i < this.fileData.sessions.length; i++) {
        if (this.fileData.sessions[i].id === params.parent.id) {
          const data = {
            id: params.id,
            name: params.name,
            isLeaf: params.isLeaf
          }

          if (!params.isLeaf) {
            data.children = []
          } else {
            data.session = {
              sessionName: '',
              hostname: '',
              port: '',
              credentials: ''
            }
          }

          this.fileData.sessions[i].children.push(data)
          break
        } else if (!this.fileData.sessions[i].isLeaf) {
          this.addNewNode(this.fileData.sessions[i], params)
        }
      }

      SessionFile.write(this.fileData)

      this.$nextTick(function() {
        this.onClickTreeNode(params)
      })
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
    addNewNode(parent, params) {
      for (let i = 0; i < parent.children.length; i++) {
        if (parent.children[i].id === params.parent.id) {
          const data = {
            id: params.id,
            name: params.name,
            isLeaf: params.isLeaf
          }

          if (!params.isLeaf) {
            data.children = []
          } else {
            data.session = {
              sessionName: '',
              hostname: '',
              port: '',
              credentials: ''
            }
          }

          parent.children[i].children.push(data)
          break
        } else if (!parent.children[i].isLeaf) {
          this.addNewNode(parent.children[i], params)
        }
      }
    },
    deleteNode(parent, params) {
      let delIdx = -1

      for (let i = 0; i < parent.children.length; i++) {
        if (parent.children[i].id === params.id) {
          delIdx = i
          break
        } else if (!parent.children[i].isLeaf) {
          this.deleteNode(parent.children[i], params)
        }
      }

      if (delIdx >= 0) {
        parent.children.splice(delIdx, 1)
      }
    },
    changeName(parent, params) {
      for (let i = 0; i < parent.children.length; i++) {
        if (parent.children[i].id === params.id) {
          parent.children[i].name = params.newName

          if (parent.children[i].isLeaf) {
            parent.children[i].session.sessionName = params.newName
          }

          break
        } else if (!parent.children[i].isLeaf) {
          this.changeName(parent.children[i], params)
        }
      }
    },
    onClickNewFolder() {
      const node = new TreeNode({
        name: 'new folder',
        isLeaf: false,
        children: []
      })

      const params = {
        id: node.id,
        name: node.name,
        isLeaf: false,
        children: []
      }

      if (!this.selectedNode || this.selectedNode.id === 1) {
        this.treeData.addChildren(node)
        params.parent = {
          id: 1
        }
      } else if (this.selectedNode && !this.selectedNode.isLeaf) {
        this.selectedNode.addChildren(node)
        params.parent = {
          id: this.selectedNode.id
        }
      } else {
        this.selectedNode.parent.addChildren(node)
        params.parent = {
          id: this.selectedNode.parent.id
        }
      }

      this.onClickAddNode(params)
    },
    onClickNewSite() {
      const node = new TreeNode({
        name: 'new site',
        isLeaf: true
      })

      const params = {
        id: node.id,
        name: node.name,
        isLeaf: true
      }

      if (!this.selectedNode || this.selectedNode.id === 1) {
        this.treeData.addChildren(node)
        params.parent = {
          id: 1
        }
      } else if (this.selectedNode && !this.selectedNode.isLeaf) {
        this.selectedNode.addChildren(node)
        params.parent = {
          id: this.selectedNode.id
        }
      } else {
        this.selectedNode.parent.addChildren(node)
        params.parent = {
          id: this.selectedNode.parent.id
        }
      }

      this.onClickAddNode(params)
    }
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
