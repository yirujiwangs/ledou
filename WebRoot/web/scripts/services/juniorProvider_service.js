(function () {
    'use strict';

    angular
        .module('center')
        .factory('JuniorProviderService',JuniorProviderService);

    JuniorProviderService.$inject = ['$q','JuniorProviderDao'];

    function JuniorProviderService($q,JuniorProviderDao) {
        return {
            statistics:function(){
                return JuniorProviderDao.statistics(); //关键指标里面的数字
            },
            juniorProvidertList:function(data){
                return JuniorProviderDao.juniorProvidertList(data);
            },
            juniorProvidertDetailList:function(data){
                return JuniorProviderDao.juniorProvidertDetailList(data);
            }
        }
    }
})();

