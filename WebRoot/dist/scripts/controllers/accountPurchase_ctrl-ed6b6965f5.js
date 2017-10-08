(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountPurchaseCtrl', AccountPurchaseCtrl);

    AccountPurchaseCtrl.$inject = [ '$scope', '$rootScope','$route','dataService','accountService','$modal'];

    function AccountPurchaseCtrl($scope,$rootScope,$route,dataService,accountService,$modal){
        var vm=this;
        // 表单数据初始化
        $scope.form = {};
        $scope.form.result = '10';
        $scope.form.accountType = 'normal';
        $scope.form.remark = '购买账号库存';
        $scope.parseInt = function(num){
            return parseInt(num);
        }
        vm.add = function(){
            if($scope.form.result){
                $scope.form.result = parseInt($scope.form.result) + 1;
            }
            else{
                $scope.form.result = 1;
            }
        }
        vm.minus = function(){
            if(parseInt($scope.form.result) > 0){
                $scope.form.result = parseInt($scope.form.result) - 1;
            }
            else{
                $scope.form.result = 0;
            }
            
        }
        vm.confirm=function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_pay.html',
                controller: 'AccountPayCtrl',
                controllerAs: 'accountPayCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
            });
        };
    }
})()