(function(){
	'use strict';

    angular
        .module('center')
        .controller('BalanceWithdrawCtrl', BalanceWithdrawCtrl);

    BalanceWithdrawCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','personData','BalanceService','AlertService','dataService'];

    function BalanceWithdrawCtrl($scope,$rootScope,$modalInstance,personData,BalanceService,AlertService,dataService){
        var vm=this;
        // console.log(personData);
        $scope.form = {};
        for(var key in personData.data.accountInfo){
            $scope.form[key] = personData.data.accountInfo[key];
        }
        if($scope.form.company_name){
            $scope.form.name = $scope.form.company_name;
        }
        else{
            $scope.form.name = $scope.form.nickname;
        }
        // console.log($scope.form);
        vm.ok = function(){
            // console.log($scope.form);
            BalanceService.refund($scope.form)
            .then(function(res){
                // console.log(res);
                if(res.data.errorcode === '0'){
                    AlertService.alert({success:true,msg:res.data.errmsg});
                }
                else{
                    AlertService.alert({success:false,msg:res.data.errmsg});
                }
                $modalInstance.dismiss('cancel');
            })
        }
        vm.close=function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()