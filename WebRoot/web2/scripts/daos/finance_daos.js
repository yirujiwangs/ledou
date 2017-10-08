
(function () {
    'use strict';

    angular
        .module('center')
        .factory('FinanceDao', FinanceDao);
    
    FinanceDao.$inject = ['baseHttp'];

    function FinanceDao(baseHttp) {
        return{
            ///清算代理商上月收�?
            clearIncome:function(page){
                return baseHttp({
                    method: 'POST',
                    url:'/supervisor/settleProxyIncome.do',
                    data: page
                });
            },
            listFinance:function(page){
              return baseHttp({
                    method: 'POST',
                    url:'/supervisor/financeManage.do',
                    data: page
                });
            },
            listFinanceSearch:function(param){
              return baseHttp({
                    method: 'POST',
                    url:'/supervisor/financeManageSearch.do',
                    data: param
                });
            },
            getStoreDepositDetail:function(param){
                return baseHttp({
                      method: 'POST',
                      url: '/supervisor/getStoreDepositDetail.do',
                      data: param
                });
            },
            storeFinanceStatistics:function(){
                return baseHttp({
                    method: 'POST',
                    url: '/supervisor/storeFinanceStatistics.do'
                })
            },
            getRechargeTips:function(){
              return baseHttp({
                    method: 'GET',
                    url: '/supervisor/deposit.do'
                });
            },
            getWithdrawnItems:function(){
              return baseHttp({
                    method: 'GET',
                    url:'/supervisor/refund.do'
                });
            },
            search:function(id){
              return baseHttp({
                    method: 'POST',
                    url: '/supervisor/searchByTradeNo.do',
                    data:id
                });
            },
            getFlowingDetail:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/supervisor/detail.do',
                    data:form
                });
            },
            rechargeConfirm:function(form){
                console.log(form)
              return baseHttp({
                    method: 'POST',
                    url: '/supervisor/rechargeConfirm.do',
                    data:form
                });
            },
            withdrawnConfirm:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/supervisor/withdrawnConfirm.do',
                    data:form
                });
            },
            detailSearch:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/supervisor/detailSearch.do',
                    data:form
                });
            },
            getFinanceDetail: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/storeDeposits.do',
                    data: param
                });
            },
            getFinanceDetailSta: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/storeFinanceStatistics.do',
                    data: param
                })
            },
            getFinanceStoreDetail: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/getStoreDepositDetail.do',
                    data: param
                })
            },
            getFinanceIncomeDetail: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/depositDetails.do',
                    data: param
                })
            },
            getFinanceIncomeDetailSta: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/finance/getBenefitStatistics.do',
                    data: param
                })
            }
        } 
    }
})();
