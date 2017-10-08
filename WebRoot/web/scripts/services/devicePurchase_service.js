/**
 * Created by admin on 2017/4/21.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('DevicePurchaseService', DevicePurchaseService);

    DevicePurchaseService.$inject = ['$q','DevicePurchaseDao'];

    function DevicePurchaseService($q,devicePurchaseDao) {
        return {
            listTotal: function (param) {
                return devicePurchaseDao.listTotal(param);
            },
            buyUrl: function(param){
                return devicePurchaseDao.buyUrl(param);
            },
            bindWxAccount: function(param){
                return devicePurchaseDao.bindWxAccount(param);
            },
            devicePrice:function(param){
                return devicePurchaseDao.devicePrice(param);
            }
        };
    }
})();
