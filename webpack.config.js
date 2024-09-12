const path = require('path');

module.exports = {
    entry: {
        //post_page: './src/main/resources/static/js/react-dev/postPageEntryPoint.js'
    },
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static/js/react-prod'),
        filename: '[name].bundle.js'
    },
    devtool: 'source-map',
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react']
                    }
                },
            },
            {
                test: /\.(png|jpe?g|gif|svg)$/i,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: '[path][name].[ext]',
                            context: 'src/main/resources/static/src/public',
                            outputPath: 'images',
                            publicPath: '/src/public/',
                            useRelativePaths: true,
                        },
                    },
                ],
            }
        ]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    mode: 'development'
};