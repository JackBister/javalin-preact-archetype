module.exports = function(config) {
    config.set({
        autoWatch: true,
        frameworks: ["jasmine", "karma-typescript"],
        files: [
            { pattern: "src/**/*.ts" },
            { pattern: "test/**/*.ts" },
            { pattern: "src/**/*.tsx" },
            { pattern: "test/**/*.tsx" }
        ],
        karmaTypescriptConfig: {
            bundlerOptions: {
                transforms: [require("karma-typescript-es6-transform")()]
            }
        },
        preprocessors: {
            "**/*.ts": ["karma-typescript"],
            "**/*.tsx": ["karma-typescript"]
        },
        reporters: ["progress", "karma-typescript"],
        browsers: ["Chrome"]
    });
};
