(function () {
    'use strict';

    angular
        .module('center')
        .factory('accountService', accountService);

    accountService.$inject = ['$q','accountDao'];

    function accountService($q,accountDao) {
        return {
            listTotal: function (page) {
                return accountDao.listTotal(page);
            },
            update: function (form) {
                return accountDao.update(form);
            },
            adduser: function (form) {
                return accountDao.adduser(form);
            },
            status: function (sta) {
                return accountDao.status(sta);
            },
            // 账号购买
            accountPurchase: function(param){
                return accountDao.accountPurchase(param);
            },
            // 账号库存数据指标
            accountStockSta: function(){
                return accountDao.accountStockSta();
            },
            // 账号库存历史记录
            accountStockHistory: function(form){
                return accountDao.accountStockHistory(form);
            },
            editPassword: function(form){
                return accountDao.editPassword(form);
            },
            bindWxAccount: function(account){
                return accountDao.bindWxAccount(account);
            }
        };
    }
})();
