(function(){
    'use strict';

    angular
        .module('center')
        .controller('RefundDetailCtrl', RefundDetailCtrl);

    RefundDetailCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','refundService','AlertService','RefundDetailData'];

    function RefundDetailCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,refundService,AlertService,RefundDetailData){
        var vm = this;
        refundService.refundDetail(RefundDetailData)
            .then(function(res){
                // console.log(res);
                vm.allData = res.data;
                // console.log(vm.allData.bank_name);
                if(vm.allData.bankName){
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