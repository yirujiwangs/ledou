(function () {
    'use strict';

    angular
        .module('center')
        .factory('accountPurchaseService', accountPurchaseService);

    accountPurchaseService.$inject = ['$q','accountPurchaseDao'];

    function accountPurchaseService($q,accountPurchaseDao) {
        return {
            listTotal: function (page) {
                return accountPurchaseDao.listTotal(page);
            },
            search:function(page){
                return accountPurchaseDao.search(page);
            }
            // update: function (form) {
            //     return accountDao.update(form);
            // },
            // adduser: function (form) {
            //     return accountDao.adduser(form);
            // },
            // status: function (sta) {
            //     return accountDao.status(sta);
            // },
            // // 账号购买
            // accountPurchase: function(param){
            //     return accountDao.accountPurchase(param);
            // },
            // // 账号库存数据指标
            // accountStockSta: function(){
            //     return accountDao.accountStockSta();
            // },
            // // 账号库存历史记录
            // accountStockHistory: function(form){
            //     return accountDao.accountStockHistory(form);
            // },
            // editPassword: function(form){
            //     return accountDao.editPassword(form);
            // }
        };
    }
})();
