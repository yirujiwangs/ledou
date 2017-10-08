(function(){
    'use strict';

    angular
        .module('center')
        .filter('GetServiceType',GetServiceType);

    function GetServiceType() {
        return function(msg) {
            switch (msg){
                case 0:
                    return "订阅号";
                case 2:
                    return "服务号";
                case 1:
                    return "新版订阅号";
            }
        }
    }
})()
