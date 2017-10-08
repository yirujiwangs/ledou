(function(){
    'use strict';

    angular
        .module('center')
        .filter('ShowAuthorizationStatus',ShowAuthorizationStatus);

    function ShowAuthorizationStatus() {
        return function(msg) {
            switch (msg){
                case 'A':
                    return "所有类型授权";
                case 'L':
                    return "登陆授权";
                case 'P':
                    return "吸粉红包授权";
            }
        }
    }
})()