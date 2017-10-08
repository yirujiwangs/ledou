
(function () {
    'use strict';

    angular
        .module('center')
        .factory('dirDao', dirDao);
    
    dirDao.$inject = ['baseHttp'];

    function dirDao(baseHttp) {
        return{
            list:function(url){
              return baseHttp({
                    method: 'GET',
                    url: 'http://localhost:8000/api/getDir?url='+url
                });
            }
        } 
    }
})();
