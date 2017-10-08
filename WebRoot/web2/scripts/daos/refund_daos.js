
(function () {
    'use strict';

    angular
    .module('center')
    .factory('RefundDao', RefundDao);
    
    RefundDao.$inject = ['baseHttp'];

    function RefundDao(baseHttp) {
        return{
          listRefund:function(page){
              return baseHttp({
                method: 'POST',
                url:'/supervisor/settlementCenter.do',
                data: page
            });
          },
          process:function(page){
              return baseHttp({
                method: 'POST',
                url:'/supervisor/financeOrderProcess.do',
                data: page
            });
          },
          refundDetail:function(page){
              return baseHttp({
                method: 'POST',
                url:'/supervisor/settlemnetOrderDetail.do',
                data: page
            });
          },
          search:function(id){
              return baseHttp({
                method: 'POST',
                url: '/supervisor/settlementSearch.do',
                data:id
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
          }
        } 
      }
  })();
