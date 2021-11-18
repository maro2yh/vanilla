const path = require('path');

module.exports = {
  pluginOptions: {
    quasar: {
      importStrategy: 'kebab',
      rtlSupport: false
    }
  },
  transpileDependencies: [
    'quasar'
  ],
  configureWebpack: {
    resolve: {
      alias: {
        '@': path.join(__dirname, 'src/')
      }
    }
  }
}
