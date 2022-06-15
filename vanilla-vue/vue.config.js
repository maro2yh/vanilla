const path = require('path')

module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        'root': path.resolve(__dirname),
        'public': path.resolve(__dirname, 'public'),
        '@': path.resolve(__dirname, 'src')
      }
    }
  },
  pluginOptions: {
    electronBuilder: {
      nodeIntegration: true
    }
  }
}