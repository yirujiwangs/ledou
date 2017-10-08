/**
 * Created by Administrator on 2017/8/3 0003.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .filter('ShowProxyType',ShowProxyType);

    function ShowProxyType() {
        return function(msg) {
            switch (msg){
                case 'P':
                    return "区县代理";
                case 'M':
                    return "市级代理";
            }
        }
    }
})()