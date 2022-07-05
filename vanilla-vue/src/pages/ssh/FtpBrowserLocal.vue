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
      <div class="row">
        <div class="col-xs-12">
          <vue-custom-scrollbar>
            <div class="table-area">
              <table class="table table-hover mb-0" v-columns-resizable>
                <thead v-columns-resizable>
                  <tr>
                    <th width="2%" class="border-top"></th>
                    <th width="68%" class="border-top">파일명 <span class="fa fa-sort"></span></th>
                    <th width="10%" class="text-center border-top border-left">크기 <span class="fa fa-sort"></span></th>
                    <th width="20%" class="text-center border-top border-left">최종 수정 <span class="fa fa-sort"></span></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="data in dataSet" :key="data.id"
                    @dblclick="onDbClickRow(data)"
                  >
                    <td>
                      <span v-if="!data.isFile" class="text-yellow"><i class="fa fa-folder" /></span>
                      <span v-if="data.isFile" class="text-black-100"><i class="fa fa-file" /></span>
                    </td>
                    <td class="text-ellipsis">
                        <span>{{ data.name }}</span>
                      </td>
                    <td class="text-ellipsis"><span v-if="data.isFile">{{ data.size }}</span></td>
                    <td class="text-ellipsis">{{ data.mtime }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </vue-custom-scrollbar>
        </div>
      </div>
    </panel>
  </div>
</template>

<script>
const fs = require('fs')
const path = require('path')

export default {
  components: {
  },
  data() {
    return {
      dirPath: '',
      dataSet: [],
      selectedData: null
    }
  },
  created() {
    this.dirPath = path.resolve('./')
    this.readDir()
  },
  methods: {
    readDir() {
      this.dataSet = []
      this.dataSet.push({
        id: 0,
        name: '..',
        isFile: false,
        mtime: '',
        size: 0
      })

      fs.readdir(this.dirPath, { withFileTypes: true }, (err, files) => {
        if (err) throw err

        files.forEach((file) => {
          fs.stat(this.dirPath + path.sep + file.name, (err, stats) => {
            if (err) throw err

            this.dataSet.push({
              id: Math.random() * (99999 - 1) + 1,
              name: file.name,
              isFile: stats.isFile() ? true : false,
              mtime: this.$moment(stats.mtime).format('YYYY-MM-DD HH:mm:ss'),
              size: stats.size
            })
            // isFile(), isDirectory(), size, atimeNs(last accessed), mtimeNs(last modified), ctimeNs(last changed), birthtimeMs(created)
          })
        })
      })
    },
    onDbClickRow(data) {
      if (data.id === 0) {
        this.moveToParentDir()
      } else if (!data.isFile) {
        this.moveToChildDir(data.name)
      }
    },
    moveToParentDir() {
      const splits = this.dirPath.split(path.sep)
      let parentPath = ''

      for (let i = 0; i < splits.length - 1; i++) {
        if (i > 0) {
          parentPath += path.sep
        }

        parentPath += splits[i]
      }

      this.dirPath = parentPath
      this.readDir()
    },
    moveToChildDir(name) {
      this.dirPath += path.sep + name
      this.readDir()
    }
  }
}
</script>

<style lang="less" rel="stylesheet/less" scoped>
.table-area {
  height: calc(100vh - 150px);
}

.table:hover {
  cursor: default;
}

.tr-hover:hover {
  cursor: pointer;
}

.border-top {
  border-top: 1px solid #000000
}

.border-left {
  border-left: 1px solid #000000
}
</style>