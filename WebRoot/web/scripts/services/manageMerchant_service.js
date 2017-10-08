(function () {
    'use strict';

    angular
        .module('center')
        .factory('ManageMerchantService',ManageMerchantService);

    ManageMerchantService.$inject = ['$q','ManageMerchantDao'];

    function ManageMerchantService($q,ManageMerchantDao) {
        return {
            statistics:function(){
                return ManageMerchantDao.statistics(); //关键指标里面的数字
            },
            manageMerchantList:function(data){
                return ManageMerchantDao.manageMerchantList(data);
            }
        }
    }
})();