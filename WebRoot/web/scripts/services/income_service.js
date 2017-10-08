(function () {
    'use strict';

    angular
        .module('center')
        .factory('IncomeService', IncomeService);

    IncomeService.$inject = ['$q','IncomeDao'];

    function IncomeService($q,IncomeDao) {
        return{
            //顶部关键指标的获取
            getBenefitSta: function(param){
                return IncomeDao.getBenefitSta(param);
            },
            //设备激活记录
           activerecord: function(param){
                return IncomeDao.activerecord(param);
            },
/*            monthDeviceList:function(param){
              return IncomeDao.monthDeviceList(param);
            },*/
            //组合采购设备记录
            combinationDevice: function(param){
                return IncomeDao.combinationDevice(param);
            },
            //间接推荐设备采购记录
            indirectDevice: function(param){
                return IncomeDao.indirectDevice(param);
            },
            //自有设备品牌红包
            deviceadred: function(param){
                return IncomeDao.deviceadred(param);
            },
            //直接推荐设备品牌红包
            directadred: function(param){
                return IncomeDao.directadred(param);
            },
            //商户广告金充值
            storeAdDepositMonthRecord:function(param){
                return  IncomeDao.storeAdDepositMonthRecord(param);
            },
            //加盟费记录
            initialFee:function(param){
                return  IncomeDao.initialFee(param);
            },
            //商户营销分成奖励
            storeSharingBenefit:function(param){
                return  IncomeDao.storeSharingBenefit(param);
            },
            //商户营销分成奖励详情
            incomeSharingDetail:function(param){
                return  IncomeDao.incomeSharingDetail(param);
            },
            //设备激活记录里面的详情
            recordData: function(param){
                return IncomeDao.recordData(param);
            },
            //商户广告金充值详情
            storeAdDepositRecordDetail:function(param){
                return IncomeDao.storeAdDepositRecordDetail(param);
            },
            //下级采购记录
            distBuyRecordList: function(param){
                return IncomeDao.distBuyRecordList(param);
            },
            //下级采购详情
            distBuyRecordDetail: function(param){
                return IncomeDao.distBuyRecordDetail(param);
            },
            dateDataIndex: function(param){
                return IncomeDao.dateDataIndex(param);
            },
            dateDeviceList: function(param){
                return IncomeDao.dateDeviceList(param);
            },
            dateRedDataIndex:function(param){
                return IncomeDao.dateRedDataIndex(param);
            },
            advBenefitList:function(param){
                return  IncomeDao.advBenefitList(param);
            }
        }
    }
})();
