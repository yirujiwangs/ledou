(function () {
    'use strict';

    angular
        .module('center')
        .factory('HistoryService', HistoryService);

    HistoryService.$inject = ['$q','HistoryDao'];


    function HistoryService($q,HistoryDao) {
        return{
        	listHistorys:function(){
        		return HistoryDao.listHistorys();
        	},
            filterHistorys:function(form){
                return HistoryDao.filterHistorys(form);
            }
        }
    }
    
})();
