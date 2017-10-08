(function () {
    'use strict';

    angular
        .module('center')
        .factory('AdService', AdService);

    AdService.$inject = ['$q','AdDao'];

    function AdService($q,AdDao) {
        return{
            listAd: function(page){
            	return AdDao.listAd(page);
            },
            startPageListAd: function(page){
                return AdDao.startPageListAd(page);
            },
            adDetail: function(param){
                return AdDao.adDetail(param);
            },
            advStrategyDetail: function(param){
                return AdDao.adDetail(param);
            },
            updateAdvStrategy: function(param){
                return AdDao.updateAdvStrategy(param);
            },
            getWithdrawls: function(param){
                return AdDao.getWithdrawls(param);
            },
            updateAdvStatus: function(param){
                return AdDao.updateAdvStatus(param);
            },
            payMoney:function(param){
                return AdDao.payMoney(param)
            },
            rePay: function(param){
                return AdDao.rePay(param)
            },
            bigRedList:function(param){
                return AdDao.bigRedList(param);
            },
            operateBigRed:function(param){
                return AdDao.operateBigRed(param);
            },
            bigRedData:function(param){
                return AdDao.bigRedData(param);
            },
            editBigRed:function(param){
                return AdDao.editBigRed(param);
            }
        }
    }
})();
