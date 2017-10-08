(function(){
    'use strict';

    angular
        .module('center')
        .filter('sex',sex);

    function sex() {
        return function(msg) {
            switch (msg){
                case 0:
                    return "保密";
                case 1:
                    return "男";
                case 2:
                    return "女";
            }
        }
    }

    angular
        .module('center')
        .filter('logStatus',logStatus);

    function logStatus() {
        return function(msg) {
            switch (msg){
                case 'N':
                    return "正常";
                case 'ERR':
                    return "失效";
                case 'WG':
                    return "待支付";
                case 'WR':
                    return "未点击";
            }
        }
    }

    angular
        .module('center')
        .filter('userStatus',userStatus);

    function userStatus() {
        return function(msg) {
            switch (msg){
                case 'R':
                    return "取消关注";
                case 'N':
                    return "成功关注";
                case 'WN':
                    return "关注时间在24小时内";
                case 'ERR':
                    return "24小时内取消关注";
                default:
                    return "关注中"
            }
        }
    }

})()
