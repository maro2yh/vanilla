const fs = require('fs')
const path = require('path')
const sessionFile = path.join('db/sessions.json')

var SessionFile = {
  createRootFolder() {
    const dirPath = path.join('db')

    if (!fs.existsSync(dirPath)) {
      fs.mkdir(dirPath, (err) => {
        if (err) {
          return console.error(err)
        }
      })
    }

    if (!fs.existsSync(sessionFile)) {
      let data = {
        sessions: []
      }
      fs.writeFileSync(sessionFile, JSON.stringify(data, null, 2))
    }
  },
  read() {
    SessionFile.createRootFolder()
    const fileData = fs.readFileSync(sessionFile, 'utf-8')
    return JSON.parse(fileData)
  },
  write(data) {
    SessionFile.createRootFolder()
    fs.writeFileSync(sessionFile, JSON.stringify(data, null, 2))
  }
}

export default SessionFile