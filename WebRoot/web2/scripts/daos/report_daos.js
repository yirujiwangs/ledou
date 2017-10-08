/**
 * Created by admin on 2017/4/27.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('reportDao', reportDao);

    reportDao.$inject = ['baseHttp'];

    function reportDao(baseHttp) {
        return{
            reportList:function(params){
                return baseHttp({
                    method: 'POST',
                    url:'/supervisor/proxyReports',
                    data:params
                });
            },
            signState:function(newState){
                return baseHttp({
                    method: 'POST',
                    url:'/supervisor/updateReportStatus',
                    data:newState
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
            // ��session��
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
            // // �˺Ųɹ�
            // accountPurchase:function(param){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/proxyAccount/buyAccount.do',
            //         data: param
            //     });
            // },
            // // �˺ſ������ָ��
            // accountStockSta: function(){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/proxyAccount/proxyAccount.do',
            //     })
            // },
            // // �˺ſ����ʷ��Ϣ
            // accountStockHistory:function(form){
            //     return baseHttp({
            //         method: 'POST',
            //         url: '/proxyAccount/accountBuyRecord.do',
            //         data: form
            //     })
            // },
            // // �޸��ҵ��˺�����
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
