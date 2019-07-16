module.exports = {
    devtool: 'source-map',
    entry: {
        client: './src/client',
        server: './src/server'
    },
    module: {
        rules: [
            { test: /\.tsx?$/, loader: 'ts-loader' }
        ]
    },
    output: {
        path: __dirname,
        filename: '[name].js'
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.js']
    }
}
