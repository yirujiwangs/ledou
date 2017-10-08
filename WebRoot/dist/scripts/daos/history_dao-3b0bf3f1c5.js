(function () {
    'use strict';

    angular
        .module('center')
        .factory('HistoryDao', HistoryDao);
    
    HistoryDao.$inject = ['baseHttp'];

    function HistoryDao(baseHttp) {
        return{
            listHistorys:function(){
              return baseHttp({
                    method: 'GET',
                    url:'/history/index.do'
                });
            },
            filterHistorys:function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/history/search.do',
                    data: form
                });
            }
        } 
    }    
})();

