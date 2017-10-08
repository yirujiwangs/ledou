(function () {
    'use strict';

    angular
        .module('center')
        .factory('OldIncomeDao', OldIncomeDao);

    OldIncomeDao.$inject = ['baseHttp'];

    function OldIncomeDao(baseHttp) {
        return{
            //顶部关键指标的获取
            getBenefitSta:function(){
                return baseHttp({
                    method: 'POST',
                    url:'/finance/statistics',
                    data: {}
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
            //income第1个选项卡
            combinationDevice:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/dealer/device/record',
                    data: param
                })
            },
            //第2个选项卡
            indirectDevice:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/dealer/device/record',
                    data: param
                })
            },
            //income第3个选项卡
            deviceadred:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/advBenefitList.do',
                    data: param
                })
            },
            //income第4个选项卡
            directadred:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/advBenefitList.do',
                    data: param
                })
            },
            //income第五个选项卡
            storeAdDeposit:function(time){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/storeAdDeposit',
                    data: time
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
            //第三个选项卡的里面的详情
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
            }
        }
    }
})();
