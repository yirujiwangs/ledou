(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountDetailStatusCtrl', AccountDetailStatusCtrl);

    AccountDetailStatusCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','AlertService','accountService','statusData'];

    function AccountDetailStatusCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,AlertService,accountService,statusData){
    	var vm = this;
        if(statusData.status == 'true'){
            vm.btn = '禁用';
            statusData.status = 'false';
        }
        else if(statusData.status == 'false'){
            vm.btn = '解封';
            statusData.status = 'true';
        }
    	vm.ok = function(){
            // console.log(statusData);
            accountService.subStatus(statusData)
            .then(function(res){
                // console.log(res);
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"操作成功"});
                    $route.reload();
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
            });
            $modalInstance.dismiss('cancel');
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()