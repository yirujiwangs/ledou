(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountStatusCtrl', AccountStatusCtrl);

    AccountStatusCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','AlertService','accountService','statusData'];

    function AccountStatusCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,AlertService,accountService,statusData){
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
            accountService.status(statusData)
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