
(function () {
    'use strict';

    angular
        .module('center')
        .factory('baseHttp', baseHttp);
    
    baseHttp.$inject = ['$http', '$rootScope', '$timeout','AlertService','$location'];

    function baseHttp($http, $rootScope, $timeout,AlertService,$location) {
        return function (config) {
            //模拟数据要用到外网所以这里要正则验证下
            var reg=/^\//;
            if(reg.test(config.url)){
                config.url =/*"http://192.168.1.219:8080"+*/"/ledou" + config.url;
            }

            var httpPromise = $http(config);

            httpPromise.error(function (e, status) {
                if(status==500){
                    AlertService.alert({success:false,msg:"服务器错误"})
                }
                else if(status==401){
                    $location.path('/login')
                }
                else if(status==404){
                    $location.path('/404')
                }
                else{
                    AlertService.alert({success:false,msg:e})
                }
            });

            return httpPromise;
        }
    }
})();
