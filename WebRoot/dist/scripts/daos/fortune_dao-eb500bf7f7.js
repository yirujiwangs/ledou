
(function () {
    'use strict';

    angular
        .module('center')
        .factory('FortuneDao', FortuneDao);
    
    FortuneDao.$inject = ['baseHttp'];

    function FortuneDao(baseHttp) {
        return{
            listFortune:function(page){
              return baseHttp({
                    method: 'POST',
                    url:'/finance/storeDeposits.do',
                    data: page
                });
            },
            getStoreDepositDetail:function(param){
                return baseHttp({
                      method: 'POST',
                      url: '/finance/getStoreDepositDetail.do',
                      data: param
                });
            },
            storeFinanceStatistics:function(){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/storeFinanceStatistics.do'
                })
            },
            getRechargeTips:function(){
              return baseHttp({
                    method: 'GET',
                    url: '/finance/deposit.do'
                });
            },
            getWithdrawnItems:function(){
              return baseHttp({
                    method: 'GET',
                    url:'/finance/refund.do'
                });
            },
            search:function(id){
              return baseHttp({
                    method: 'POST',
                    url: '/finance/searchByTradeNo.do',
                    data:id
                });
            },
            getFlowingDetail:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/finance/detail.do',
                    data:form
                });
            },
            rechargeConfirm:function(form){
                console.log(form)
              return baseHttp({
                    method: 'POST',
                    url: '/finance/rechargeConfirm.do',
                    data:form
                });
            },
            withdrawnConfirm:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/finance/withdrawnConfirm.do',
                    data:form
                });
            },
            detailSearch:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/finance/detailSearch.do',
                    data:form
                });
            }
        } 
    }
})();
