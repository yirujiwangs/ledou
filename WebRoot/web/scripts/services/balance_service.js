(function () {
    'use strict';

    angular
        .module('center')
        .factory('BalanceService', BalanceService);

    BalanceService.$inject = ['$q','BalanceDao'];

    function BalanceService($q,BalanceDao) {
        return{
        	finance: function(time){
                return BalanceDao.finance(time);
            },
            getFinanceDetail: function(param){
                return BalanceDao.getFinanceDetail(param);
            },
            refund: function(form){
                return BalanceDao.refund(form);
            },
            refundDetail: function(id){
                return BalanceDao.refundDetail(id);
            }
        }
    }
})();
