
(function () {
    'use strict';

    angular
        .module('center')
        .factory('AdDao', AdDao);
    
    AdDao.$inject = ['baseHttp'];

    function AdDao(baseHttp) {
        return {
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
            uploadInit: function (vm, data, selector, beforeUploadCallback, acceptCallback, errorCallback, maxSize) {
                //这里写是为了双重保险
                if (!maxSize)maxSize = 1;
                Qiniu.uploader({
                    runtimes: 'html5,flash,html4',
                    browse_button: selector,//上传选择的点选按钮，必需
                    max_file_size: maxSize + 'mb',
                    chunk_size: '4mb',
                    uptoken: data.uptoken,
                    domain: data.domain,
                    get_new_uptoken: false,
                    auto_start: true,
                    init: {
                        'BeforeUpload': beforeUploadCallback,
                        'FileUploaded': acceptCallback,
                        'Error': errorCallback
                    }
                });
            },
            listAd: function (page) {
                return baseHttp({
                    method: "POST",
                    dataType: "json",
                    url: "/advertise/advlist.do",
                    data: page
                })
            },
            startPageListAd: function (page) {
                return baseHttp({
                    method: "POST",
                    dataType: "json",
                    url: "/advertise/advlist.do",
                    data: page
                })
            },
            adDetail: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/advStrategyDetail.do",
                    data: param
                })
            },
            advStrategyDetail: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/advStrategyDetail.do",
                    data: param
                })
            },
            updateAdvStrategy: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/updateAdvStrategy.do",
                    data: param
                })
            },
            getWithdrawls: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/getWithdrawls.do",
                    data: param
                })
            },
            updateAdvStatus: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/updateAdvStatus.do",
                    data: param
                })
            },
            payMoney: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/leAdvPost.do",
                    data: param
                })
            },
            rePay: function (param) {
                //var newwindow=window.open("about:blank");
                //window.focus();
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/leAdvRePay.do",
                    data: param
                })
            },
            bigRedList: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: "/red/bigRedList.do",
                    data: param
                })
            },
            operateBigRed:function(param){
                return baseHttp({
                    method: 'POST',
                    url: "/red/operateBigRed.do",
                    data: param
                })
            },
            bigRedData:function(param){
                return baseHttp({
                    method: 'POST',
                    url: "/advertise/leSurpriseRedData.do",
                    data: param
                })
            },
            editBigRed:function(param){
                return baseHttp({
                    method: 'POST',
                    url: "/red/editBigRed.do",
                    data: param
                })
            }
        }
    }
})();
