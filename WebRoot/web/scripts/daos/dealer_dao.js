(function () {
    'use strict';

    angular
        .module('center')
        .factory('dealerDao', dealerDao);

    dealerDao.$inject = ['baseHttp'];

    function dealerDao(baseHttp) {
        return{
            listTotal:function(page){
              return baseHttp({
                    method: 'POST',
                    url:'/account/index.do',
                    data: page
                });
            },
            listDevice:function(){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                });
            },
            // 添加经销商
            addDealer:function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data:form
                    });
            },
            // 禁用
            status:function(sta){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: sta
                });
            },
            distributeDevice:function(obj){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: obj
                });
            },
            // 编辑
           updateDealer:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: param
                });
            },
            distributeMerchant:function(obj){dealerDetail
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: obj
                });
            },
            // 获取某个经销商信息
            dealerDetail:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: param
                });
            }

        }
    }
})();
