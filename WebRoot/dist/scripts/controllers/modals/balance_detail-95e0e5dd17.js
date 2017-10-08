(function(){
	'use strict';

    angular
        .module('center')
        .controller('BalanceDetailCtrl', BalanceDetailCtrl);

    BalanceDetailCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','BalanceService','AlertService','balanceData'];

    function BalanceDetailCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,BalanceService,AlertService,balanceData){
    	var vm = this;
        BalanceService.refundDetail(balanceData)
        .then(function(res){
            vm.allData = res.data.proxyFinanceRecord;
            // console.log(vm.allData.bank_name);
            if(vm.allData.bank_name){
                vm.withdrawSelect = '对公账户方式';
            }
            else{
                vm.withdrawSelect = '支付宝方式';
            }
            // console.log(vm.withdrawSelect);
        })
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()