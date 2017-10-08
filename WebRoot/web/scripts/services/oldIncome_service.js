(function () {
    'use strict';

    angular
        .module('center')
        .factory('OldIncomeService', OldIncomeService);

    OldIncomeService.$inject = ['$q','OldIncomeDao'];

    function OldIncomeService($q,OldIncomeDao) {
        return{
            //顶部关键指标的获取
            getBenefitSta: function(){
                return OldIncomeDao.getBenefitSta();
            },
            //设备激活记录
            activerecord: function(param){
                return OldIncomeDao.activerecord(param);
            },
            //第1个选项卡
            combinationDevice: function(param){
                return OldIncomeDao.combinationDevice(param);
            },
            //第2个选项卡
            indirectDevice: function(param){
                return OldIncomeDao.indirectDevice(param);
            },
            //第3个选项卡
            deviceadred: function(param){
                return OldIncomeDao.deviceadred(param);
            },
            //第4个选项卡
            directadred: function(param){
                return OldIncomeDao.directadred(param);
            },
            //第5个选项卡
            storeAdDeposit: function(time){
                return OldIncomeDao.storeAdDeposit(time);
            },
            dateDataIndex: function(param){
                return OldIncomeDao.dateDataIndex(param);
            },
            //设备激活记录里面的详情
            recordData: function(param){
                return OldIncomeDao.recordData(param);
            },
            dateDeviceList: function(param){
                return OldIncomeDao.dateDeviceList(param);
            }
        }
    }
})();
