
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
                    url:'/admin/superLogin.do',
                    data:form
                });
            }
        } 
    }
})();
