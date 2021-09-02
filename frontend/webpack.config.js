const path = require('path');
const { VueLoaderPlugin } = require('vue-loader');

module.exports = {
    resolve: {
        alias: {
            jquery: "jquery/src/jquery"
        }
    },
    entry: {
        main: './src/main/app/main.js',
    },
    output: {
        path: path.resolve(__dirname, './build/resources/main/public/dist'),
        filename: '[name].bundle.js',
    },
    mode: 'development',
    module: {
        rules: [
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
            },
            {
                test: /\.(gif|png|jpe?g|svg)$/i,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            esModule: false
                        }
                    }
                ]
            },
            {
                test: /\.js$/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [['@babel/preset-env', {
                            targets: {
                                esmodules: true
                            }
                        }]]
                    }
                }
            }
        ]
    },
    plugins: [
        // убедитесь что подключили плагин!
        new VueLoaderPlugin()
    ]
};