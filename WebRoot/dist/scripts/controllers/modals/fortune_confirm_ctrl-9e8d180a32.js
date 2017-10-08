(function(){
	'use strict';

    angular
        .module('center')
        .controller('FortuneConfirmCtrl', FortuneConfirmCtrl);

    FortuneConfirmCtrl.$inject = [ '$scope', '$rootScope','dataService','FortuneService','AlertService','$modal','$modalInstance','confirm'];

    function FortuneConfirmCtrl($scope,$rootScope,dataService,FortuneService,AlertService,$modal,$modalInstance,confirm){    	
        //支付宝接口实现后去掉if部分,并且去掉$scope.show
        if(confirm=='fortuneWithdrawnConfirm'){
            $scope.show=1;
            var vm=this
            vm.ok=function(){
                $rootScope.$broadcast(confirm,$scope.form);
                $modalInstance.dismiss()
            }
            vm.close=function(){
                $modalInstance.dismiss()
            }
        }
        else{
            $scope.show=0;
            $scope.form={};
            var vm=this
            vm.ok=function(){
                if($scope.form.username!=undefined&&$scope.form.password!=undefined){
                    $rootScope.$broadcast(confirm,$scope.form);
                    $modalInstance.dismiss()
                }
                else{
                    AlertService.alert({success:false,msg:"请填写用户名和密码"})
                }
                
            }
            vm.close=function(){
                $modalInstance.dismiss()
            }
        }
        
    }
})()