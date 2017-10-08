(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountEditCtrl', AccountEditCtrl);

    AccountEditCtrl.$inject = [ '$scope', '$rootScope','$route','dataService','$location','$modalInstance','shopData','utilService','$modal','AlertService','accountService'];

    function AccountEditCtrl($scope,$rootScope,$route,dataService,$location,$modalInstance,shopData,utilService,$modal,AlertService,accountService){
    	var vm=this;

        // 设定三级联动容器左浮动
        $scope.align = true;

    	$scope.form=shopData;
    	vm.update=function(){
            $scope.form.areaCode = $scope.areaCode;
            $scope.form.policy=parseInt($scope.form.policy);
			accountService.update($scope.form)
			.then(function(res){
                console.log(res);
				if(res.data.flag){
                    AlertService.alert({success:true,msg:"操作成功"});
                    $route.reload();
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请稍后再试"})
                }
			})
			$modalInstance.dismiss('cancel');
    	}
        vm.close=function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()