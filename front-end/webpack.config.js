var path    = require('path');
var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var node_modules_dir = path.join(__dirname, 'node_modules');

var config = {
  entry: {
    app: ['slick-carousel', path.resolve(__dirname, 'app/js/main.jsx')],
    //vendors: ['react', 'react-router', 'underscore', 'classnames']
  },
  output: {
    path: process.env.NODE_ENV === 'production' ? path.resolve(__dirname, 'dist') : path.resolve(__dirname, 'build/assets'),
    filename: 'app.js'
  },
  resolveLoader: {
    modulesDirectories: ['node_modules']
  },
  resolve: {
    alias: {},
    extensions: ["", ".webpack.js", ".web.js", ".js", ".jsx", ".coffee", ".less"]
  },
  context: path.join(__dirname, 'build'),
  plugins: [
    new CopyWebpackPlugin([
      { from: 'index.html' },
      { from: 'assets/images', to: 'assets/images' },
    ]),
    new webpack.EnvironmentPlugin([
      "NODE_ENV"
    ]),
    //new webpack.optimize.CommonsChunkPlugin('vendors', 'vendors.js')
  ],
  module: {
    noParse: [],
    loaders: [
      { test: /\.css$/, loaders: ['style-loader', 'css-loader']},
      { test: /\.less$/, loaders: ['style-loader', 'css-loader', 'less-loader'], exclude: /(node_modules)/},
      { test: /\.(js|jsx)$/, loader: 'babel', exclude: /(node_modules)/ },

      // **IMPORTANT** This is needed so that each bootstrap js file required by bootstrap-webpack has access to the jQuery object
      { test: /bootstrap\/js\//, loader: 'imports?jQuery=jquery' },

      // Needed for the css-loader when [bootstrap-webpack](https://github.com/bline/bootstrap-webpack) loads bootstrap's css.
      { test: /\.(woff|woff2)(\?v=\d+\.\d+\.\d+)?$/,   loader: "url?limit=10000&minetype=application/font-woff" },
      { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/,    loader: "url?limit=10000&minetype=application/octet-stream" },
      { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/,    loader: "file" },
      { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/,    loader: "url?limit=10000&minetype=image/svg+xml" },

      { test: /\.png$/, loader: "url?limit=100000" },
      { test: /\.jpg$/, loader: "file" }
    ]
  }
}

module.exports = config;
