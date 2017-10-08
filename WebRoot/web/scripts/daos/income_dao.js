(function () {
    'use strict';

    angular
        .module('center')
        .factory('IncomeDao', IncomeDao);

    IncomeDao.$inject = ['baseHttp'];

    function IncomeDao(baseHttp) {
        return{
            //顶部关键指标的获取
            getBenefitSta:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/finance/statistics',
                    data:param
                });
            },
            //设备激活记录
            activerecord:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/deviceActivateRecord',
                    data: param
                })
            },
/*            monthDeviceList:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/monthDeviceList.do',
                    data: param
                })
            },*/
            //组合采购设备记录
            combinationDevice:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/dealer/device/record',
                    data: param
                })
            },
            //间接推荐设备采购记录
            indirectDevice:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/dealer/device/record',
                    data: param
                })
            },
            //自有设备品牌红包
            deviceadred:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/advBenefitList.do',
                    //url: '/finance/getMonthFinance.do',
                    data: param
                })
            },
            //直接推荐设备品牌红包
            directadred:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/advBenefitList.do',
                    data: param
                })
            },
            //商户广告金充值
            storeAdDepositMonthRecord:function(param){
                return baseHttp({
                    method: 'POST',
                    //url: '/finance/storeAdDepositMonthRecord.do',
                    url:'/finance/getMonthFinance.do',
                    data: param
                });
            },
            //加盟费记录
            initialFee:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/franchiseFeeRecordList.do',
                    data: param
                });
            },
            //商户营销分成奖励
            storeSharingBenefit:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/proxyBenefitRecord.do',
                    data: param
                });
            },
            //商户营销分成奖励详情
            incomeSharingDetail:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/proxyBenefitDetail.do',
                    data: param
                });
            },
            //设备激活记录里面的详情
            recordData:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/dateDeviceActivateRecord',
                    data: param
                })
            },
            //商户广告金充值详情
            storeAdDepositRecordDetail:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/storeAdDepositRecordDetail.do',
                    data: param
                })
            },
            //下级采购记录
            distBuyRecordList:function(param){
                return baseHttp({
                    method:'POST',
                    url:"/finance/distBuyRecordList.do",
                    data: param
                })
            },
            //下级采购详情
            distBuyRecordDetail:function(param){
                return baseHttp({
                    method:'POST',
                    url:"/finance/distBuyRecordDetail.do",
                    data: param
                })
            },
            dateDataIndex:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/dateDataIndex.do',
                    data: param
                })
            },
            dateDeviceList: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/dateDeviceList.do',
                    data: param
                })
            },
            dateRedDataIndex:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/dateRedDataIndex.do',
                    data: param
                })
            },
            advBenefitList:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/advBenefitList.do',
                    data: param
                })
            }
        }
    }
})();
