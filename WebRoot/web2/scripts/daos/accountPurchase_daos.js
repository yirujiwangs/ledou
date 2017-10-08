(function () {
    'use strict';

    angular
        .module('center')
        .factory('accountPurchaseDao', accountPurchaseDao);
    
    accountPurchaseDao.$inject = ['baseHttp'];

    function accountPurchaseDao(baseHttp) {
        return{
            listTotal:function(param){
              return baseHttp({
                    method: 'POST',
                    url:'/supervisor/accountOrderList.do',
                    data: param
                });
            },
            search:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/supervisor/accountOrderSearch.do',
                    data: param
                });
            }
            // update:function(form){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/account/update.do',
            //         data: form
            //     });
            // },
            // adduser:function(form){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/account/adduser.do',
            //         data: form
            //     });
            // },
            // status:function(sta){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/account/status.do',
            //         data: sta
            //     });
            // },
            // del:function(obj){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/account/delete.do',
            //         data: obj
            //     });
            // },
            // // 账号采购
            // accountPurchase:function(param){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/proxyAccount/buyAccount.do',
            //         data: param
            //     });
            // },
            // // 账号库存数据指标
            // accountStockSta: function(){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/proxyAccount/proxyAccount.do',
            //     })
            // },
            // // 账号库存历史信息
            // accountStockHistory:function(form){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/proxyAccount/accountBuyRecord.do',
            //         data: form
            //     })
            // },
            // // 修改我的账号密码
            // editPassword: function(form){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/admin/cp.do',
            //         data: form
            //     });
            // }
        } 
    }
})();
