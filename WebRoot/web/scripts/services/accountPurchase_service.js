/**
 * Created by admin on 2017/5/8.
 */
/**
 * Created by admin on 2017/4/21.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('AccountPurchaseService', AccountPurchaseService);

    AccountPurchaseService.$inject = ['$q','AccountPurchaseDao'];

    function AccountPurchaseService($q,AccountPurchaseDao) {
        return {
            buyUrl: function(param){
                return devicePurchaseDao.buyUrl(param);
            },

        };
    }
})();
