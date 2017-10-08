(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountPurchaseCtrl', AccountPurchaseCtrl);

    AccountPurchaseCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','AlertService','accountPurchaseService','statusData'];
//界面上的核实功能 已取消
    function AccountPurchaseCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,AlertService,accountPurchaseService,statusData){
    	var vm = this;
        // if(statusData.status == 'true'){
        //     vm.btn = '禁用';
        //     statusData.status = 'false';
        // }
        // else if(statusData.status == 'false'){
        //     vm.btn = '解封';
        //     statusData.status = 'true';
        // }
    	vm.ok = function(){
            accountPurchaseService.status(statusData)
            .then(function(res){
                // console.log(res);
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"操作成功"});
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
            });
            $modalInstance.dismiss('cancel');
            $route.reload();
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()