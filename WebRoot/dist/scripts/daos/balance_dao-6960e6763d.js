(function () {
    'use strict';

    angular
        .module('center')
        .factory('BalanceDao', BalanceDao);
    
    BalanceDao.$inject = ['baseHttp'];

    function BalanceDao(baseHttp) {
        return{
            finance:function(time){
                return baseHttp({
                    method: 'POST',
                    url: '/proxyFinance/finance.do',
                    data: time
                });
            },
            getFinanceDetail:function(param){
            	return baseHttp({
            		method: 'POST',
            		url: '/proxyFinance/getFinanceDetail.do',
            		data: param
            	});
            },
            refund:function(form){
            	return baseHttp({
            		method: 'POST',
            		url: '/proxyFinance/refund.do',
            		data: form
            	});
            },
            refundDetail:function(id){
                return baseHttp({
                    method: 'POST',
                    url: '/proxyFinance/refundDetail.do',
                    data: id
                });
            }
        } 
    }
})();

