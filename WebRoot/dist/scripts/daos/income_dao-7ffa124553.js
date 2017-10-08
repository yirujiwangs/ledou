(function () {
    'use strict';

    angular
        .module('center')
        .factory('IncomeDao', IncomeDao);
    
    IncomeDao.$inject = ['baseHttp'];

    function IncomeDao(baseHttp) {
        return{
            getBenefitSta:function(){
                return baseHttp({
                    method: 'POST',
                    url:'/finance/getBenefitStatistics.do'
                });
            },
            depositDetails:function(time){
            	return baseHttp({
            		method: 'POST',
            		url: '/finance/depositDetails.do',
                    data: time
            	});
            }
        } 
    }
})();
