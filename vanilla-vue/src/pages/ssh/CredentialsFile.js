const fs = require('fs')
const path = require('path')
const credentialsFile = path.join('ssh/credentials.json')

var CredentialsFile = {
  createRootFolder() {
    const dirPath = path.join('ssh')

    if (!fs.existsSync(dirPath)) {
      fs.mkdir(dirPath, (err) => {
        if (err) {
          return console.error(err)
        }
      })
    }

    if (!fs.existsSync(credentialsFile)) {
      let data = {
        credentials: []
      }
      fs.writeFileSync(credentialsFile, JSON.stringify(data, null, 2))
    }
  },
  read() {
    const fileData = fs.readFileSync(credentialsFile, 'utf-8')
    return JSON.parse(fileData)
  },
  write(data) {
    fs.writeFileSync(credentialsFile, JSON.stringify(data, null, 2))
  },
  find(id) {
    const fileData = CredentialsFile.read()
    let credential = null

    for (let data of fileData.credentials) {
      if (data.id === id) {
        credential = data
        break
      }
    }

    return credential
  }
}

export default CredentialsFile