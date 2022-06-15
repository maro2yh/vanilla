const fs = require('fs')
const path = require('path')
const sessionFile = path.join('ssh/sessions.json')

var SessionFile = {
  createRootFolder() {
    const dirPath = path.join('ssh')

    if (!fs.existsSync(dirPath)) {
      fs.mkdir(dirPath, (err) => {
        if (err) {
          return console.error(err)
        }
      })
    }

    if (!fs.existsSync(sessionFile)) {
      let data = {
        sessions: this.fileData
      }
      fs.writeFileSync(sessionFile, JSON.stringify(data, null, 2))
    }
  },
  read() {
    const fileData = fs.readFileSync(sessionFile, 'utf-8')
    return JSON.parse(fileData)
  },
  write(data) {
    fs.writeFileSync(sessionFile, JSON.stringify(data, null, 2))
  },
  find(id) {
    const fileData = this.read()
    let data = null

    for (let i = 0; i < fileData.sessions.length; i++) {
      if (fileData.sessions[i].id && fileData.sessions[i].id === id) {
        data = fileData.sessions[i]
        break
      } else if (fileData.sessions[i].children && fileData.sessions[i].children.length > 0) {
        data = this.findChildrenId(fileData.sessions[i], id)

        if (data !== null) {
          break
        }
      }
    }

    return data
  },
  findChildrenId(parent, id) {
    let data = null

    for (let i = 0; i < parent.children.length; i++) {
      if (parent.children[i].id === id) {
        data = parent.children[i]
        break
      } else if (parent.children[i].id !== id && parent.children[i].children && parent.children[i].children.length > 0) {
        data = this.findChildrenId(parent.children[i], id)

        if (data !== null) {
          break
        }
      }
    }

    return data
  }
}

export default SessionFile