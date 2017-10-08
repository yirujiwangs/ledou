
(function () {
    'use strict';

    angular
        .module('center')
        .factory('loginDao', loginDao);
    
    loginDao.$inject = ['baseHttp'];

    function loginDao(baseHttp) {       
        return{
            check:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/admin/login.do',
                    data:form
                });
            },
            qrcode: function(){
                return baseHttp({
                    method: 'POST',
                    url: '/wx/qrCode.do'
                })
            },
            qrcodeLogin: function(param){
                var Data = angular.toJson({"state":param});
                //console.log(Data)
                return baseHttp({
                    method: 'POST',
                    url: '/wx/requestLogin.do',
                    data:Data
                })
            }
        } 
    }
})();
