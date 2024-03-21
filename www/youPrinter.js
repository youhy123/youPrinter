var exec = require('cordova/exec');

exports.created = function (arg0, success, error) {
    exec(success, error, 'youprinter', 'created', [arg0]);
};
exports.connectNet = function (arg0, success, error) {
    exec(success, error, 'youprinter', 'connectNet', [arg0]);
};
exports.disConnect = function (arg0, success, error) {
    exec(success, error, 'youprinter', 'disConnect', [arg0]);
};
exports.printQddSample = function (arg0, success, error) {
    exec(success, error, 'youprinter', 'printQddSample', [arg0]);
};
