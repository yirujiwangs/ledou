(function () {
    'use strict';

    angular
        .module('center')
        .factory('CheckStoreService', CheckStoreService);

    CheckStoreService.$inject = ['$q','CheckStoreDao'];

    function CheckStoreService($q,CheckStoreDao) {
        return{
            checkStoreInfo:function(param){
                CheckStoreDao.checkStoreInfo(param);
            }
        }
    }
})();
