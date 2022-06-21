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
        sessions: []
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
  }
}

export default SessionFile