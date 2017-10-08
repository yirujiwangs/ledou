(function(){
    'use strict';

    angular
        .module('center')
        .filter('CheckStoreStatus',CheckStoreStatus);

    function CheckStoreStatus() {
        return function(msg) {
            switch (msg){
                case 0:
                    return "未审核(试用)";
                case 1:
                    return "提交审核中";
                case 2:
                    return "审核驳回";
                case 3:
                    return "审核通过";
                case 4:
                    return "异常";
            }
        }
    }
})()