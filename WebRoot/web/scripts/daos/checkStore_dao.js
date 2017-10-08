(function () {
    'use strict';

    angular
        .module('center')
        .factory('CheckStoreDao', CheckStoreDao);

    CheckStoreDao.$inject = ['baseHttp'];

    function CheckStoreDao(baseHttp) {
        return{
            getUploadToken: function () {
                return $.ajax({
                    type: "get", //jquery是不支持post方式跨域的
                    async: true,
                    url: "http://www.ledouya.com:3001/uptoken", //跨域请求的URL
                    dataType: "jsonp",
                    //传递给请求处理程序，用以获得jsonp回调函数名的参数名(默认为:callback)
                    jsonp: "jsoncallback",
                    //自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
                    jsonpCallback: "success_jsonpCallback"
                    //成功获取跨域服务器上的json数据后,会动态执行这个callback函数
                });
            },
            uploadInit: function (vm, data, selector, acceptCallback,errorCallback) {
                Qiniu.uploader({
                    runtimes: 'html5,flash,html4',
                    browse_button: selector,//上传选择的点选按钮，必需
                    max_file_size: '5mb',
                    chunk_size: '4mb',
                    uptoken: data.uptoken,
                    domain: data.domain,
                    get_new_uptoken: false,
                    auto_start: true,
                    init: {
                        'FileUploaded': acceptCallback,
                        'Error':errorCallback,
                        'BeforeUpload': function (up, file) {
                            // 每个文件上传时,处理相关的事情
                            // toastr.uploadFile();
                        }
                    }
                });
            },
            checkStoreInfo: function(param){
                return baseHttp({
                    method: 'POST',
                    dataType: "json",
                    url:'/manageMerchant/userCerCheck.do',
                    data: param
                });
            }
        }
    }
})();

