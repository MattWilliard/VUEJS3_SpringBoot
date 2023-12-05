const EncodingPlugin = require("encoding-plugin");

module.exports = {
    configureWebpack: {
        devtool: "inline-source-map",
        plugins: [
          new EncodingPlugin({
            encoding: "UTF-8"
          })
        ],
      },
    devServer:{
        proxy: {
            '^/json/db': {
                target: 'http://localhost:5000' ,
                changeOrigin: true,
                logLevel: 'debug',
                pathRewrite: {
                    '^/json/db': '/'
                },
            },
            '^/java': {
                target: 'http://localhost:8081',
                changeOrigin: true,
                pathRewrite: {
                    '^/java': '/'
                },
            }
        },
    },
    publicPath:
        process.env.NODE_ENV === "production"
          ? "/TaskTracker/"
          : "/Local_TaskTracker/"
}