(function(){
    'use strict';

    angular
        .module('center')
        .filter('GetVerifyType',GetVerifyType);
   //注意格式是integer而不是string
    function GetVerifyType() {
        return function(msg) {
            switch (msg){
                case -1:
                    return "订阅号";
                case 0:
                    return "微信认证";
                case 1:
                    return "新浪认证";
                case 2:
                    return "腾讯认证";
                default :
                    return "等待认证";
            }
        }
    }
})()
