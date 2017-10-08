(function(){
	'use strict';

    angular
        .module('center')
        .controller('BalanceWithdrawCtrl', BalanceWithdrawCtrl);

    BalanceWithdrawCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','personData','BalanceService','AlertService','$route','dataService'];

    function BalanceWithdrawCtrl($scope,$rootScope,$modalInstance,personData,BalanceService,AlertService,$route,dataService){
        var vm=this;
         console.log(personData ,"申请结算的默认信息");
        $scope.form = {};
        $scope.account_name = personData.data.account.username;
        for(var key in personData.data.accountInfo){
            $scope.form[key] = personData.data.accountInfo[key];
        }
        if($scope.form.company_name){
            $scope.form.name = $scope.form.company_name;
        }
        else{
            $scope.form.name = $scope.account_name;
        }
        // console.log($scope.form);
        vm.ok = function(){
            // console.log($scope.form);
            BalanceService.refund($scope.form)
            .then(function(res){
                  if (res.data.errorcode == 1) {
                      AlertService.alert({success: false, msg: res.data.errmsg});
                  }
                  else {
                      AlertService.alert({success: true, msg: res.data.errmsg});
                  }
                  $modalInstance.dismiss('cancel');

            })
        }
        vm.close=function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()