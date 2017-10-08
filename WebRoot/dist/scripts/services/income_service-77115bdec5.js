(function () {
    'use strict';

    angular
        .module('center')
        .factory('IncomeService', IncomeService);

    IncomeService.$inject = ['$q','IncomeDao'];

    function IncomeService($q,IncomeDao) {
        return{
        	getBenefitSta: function(){
                return IncomeDao.getBenefitSta();
            },
            depositDetails: function(time){
                return IncomeDao.depositDetails(time);
            }
        }
    }
})();
