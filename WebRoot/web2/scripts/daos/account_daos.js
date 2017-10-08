(function () {
    'use strict';

    angular
    .module('center')
    .factory('accountDao', accountDao);
    
    accountDao.$inject = ['baseHttp'];

    function accountDao(baseHttp) {
        return{
            operatorAccounts:function(params){
                return baseHttp({
                    method: 'POST',
                    url:'/supervisor/accountManage.do',
                    data:params
                });
            },
            update:function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/supervisor/updateRemark.do',
                    data: form
                });
            },
            subUpdate:function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/account/update.do',
                    data: form
                })
            },
            adduser:function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/supervisor/insertAccount.do',
                    data: form
                });
            },
            addSubUser:function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/account/adduser.do',
                    data: form
                });
            },
            status:function(sta){
                return baseHttp({
                    method: 'POST',
                    url: '/proxyAccount/update/status',
                    data: sta
                });
            },
            subStatus:function(sta){
                return baseHttp({
                    method: 'POST',
                    url: '/account/status.do',
                    data: sta
                });
            },
            search:function(sta){
                return baseHttp({
                    method: 'POST',
                    url: '/supervisor/accountManageSearch.do',
                    data: sta
                });
            },
            stateList:function(sta){
                return baseHttp({
                    method: 'POST',
                    url: '/supervisor/accountListByStatus.do',
                    data: sta
                });
            },
            getAccountDetail:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: param
                })
            }
            // 存session用
            // detail:function(id){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/supervisor/corporationInfoDetail.do',
            //         data: id
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
