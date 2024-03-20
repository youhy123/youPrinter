var exec = require('cordova/exec');

exports.created = function (arg0, success, error) {
    exec(success, error, 'youPrinter', 'created', [arg0]);
};
exports.connectNet = function (arg0, success, error) {
    exec(success, error, 'youPrinter', 'connectNet', [arg0]);
};
exports.disConnect = function (arg0, success, error) {
    exec(success, error, 'youPrinter', 'disConnect', [arg0]);
};
exports.printQddSample = function (arg0, success, error) {
    exec(success, error, 'youPrinter', 'printQddSample', [arg0]);
};
