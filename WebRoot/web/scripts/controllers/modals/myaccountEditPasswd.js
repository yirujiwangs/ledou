(function(){
	'use strict';

    angular
        .module('center')
        .controller('MyaccountEditPasswdCtrl', MyaccountEditPasswdCtrl);

    MyaccountEditPasswdCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance','$modal','AlertService','accountService'];

    function MyaccountEditPasswdCtrl($scope,$rootScope,$route,$modalInstance,$modal,AlertService,accountService){
    	var vm = this;
        vm.ok = function(){
            // console.log($scope.form);
            accountService.editPassword($scope.form)
            .then(function(res){
                // console.log(res);
                if(res.data.errorcode == 1){
                    AlertService.alert({success:true,msg:"修改成功"});
                }
                else{
                    AlertService.alert({success:false,msg:"修改失败"});
                }
                $modalInstance.dismiss('cancel');
            });
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()