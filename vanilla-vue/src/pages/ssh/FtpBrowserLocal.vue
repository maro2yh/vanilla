<template>
  <div class="panel dir">
    <panel title="Local" noButton="true">
      <div class="row">
        <div class="col-md-12">
          <div class="mb-3">
            <input type="text" class="w-100" v-model="dirPath" />
          </div>
        </div>
      </div>
      <vue-tree-list
        :model="treeData"
        @click="onClickTreeNode"
        v-bind:default-expanded="true"
      >
        <template v-slot:leafNameDisplay="slotProps">
          <span>
            {{ slotProps.model.name }} <span class="muted">#{{ slotProps.model.id }}</span>
          </span>
        </template>
        <span class="icon me-1" slot="leafNodeIcon"><span class="text-black-100"><i class="fa fa-file" /></span></span>
        <span class="icon me-1" slot="treeNodeIcon"><span class="text-yellow"><i class="fa fa-folder" /></span></span>
      </vue-tree-list>
    </panel>
  </div>
</template>

<script>
import { VueTreeList, Tree, TreeNode } from 'vue-tree-list2'
const fs = require('fs')
const path = require('path')

export default {
  components: {
    VueTreeList
  },
  data() {
    return {
      dirPath: '',
      treeData: null,
      selectedNode: null
    }
  },
  created() {
    this.dirPath = path.resolve('./')
    this.setTreeData('./')
  },
  methods: {
    setTreeData(path) {
      this.treeData = new Tree([
        {
          name: '..',
          id: 1,
          pid: 0,
          dragDisabled: true,
          addTreeNodeDisabled: true,
          addLeafNodeDisabled: true,
          editNodeDisabled: true,
          delNodeDisabled: true
        }
      ])

      fs.readdir(path, { withFileTypes: true }, (err, files) => {
        if (err) throw err

        files.forEach((file, idx) => {
          this.treeData.addChildren(new TreeNode(
            {
              id: Date.now() + idx,
              name: file.name,
              isLeaf: !file.isDirectory(),
              children: [],
              dragDisabled: true,
              addTreeNodeDisabled: true,
              addLeafNodeDisabled: true,
              editNodeDisabled: true,
              delNodeDisabled: true
            }
          ))
        })
      })
    },
    onClickTreeNode(params) {
      if (this.selectedNode === null) {
        this.selectedNode = params
      } else if (this.selectedNode !== null && params.id === 1) {
        const splits = this.dirPath.split(path.sep)

        if (splits.length === 1) {
          this.dirPath = path.resolve('/')
          this.setTreeData('/')
        } else {
          this.dirPath = this.getParentPath()
          this.setTreeData(this.dirPath)
        }

        this.selectedNode = null
      } else if (this.selectedNode !== null && params.id !== 1 && this.selectedNode.id === params.id) {
        this.dirPath += path.sep + this.selectedNode.name
        this.setTreeData(this.dirPath)
        this.selectedNode = null
      }
    },
    getParentPath() {
      const splits = this.dirPath.split(path.sep)
      let parentPath = ''

      for (let i = 0; i < splits.length - 1; i++) {
        if (i > 0) {
          parentPath += path.sep
        }

        parentPath += splits[i]
      }

      return parentPath
    }
  }
}
</script>
