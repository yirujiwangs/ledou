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
            console.log('0000');
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

        var param={};
        vm.confirm=function(){
            var needmonyNormal = document.getElementById("normal").value;
            //获取二维码
            //给后台传递数据
            param.needmony = $scope.needmony;//所需金额
            console.log( param.needmony,"点击所需金额","zhifu")
            accountPurchaseService.buyUrl(param)
                .then(function (result) {
                    if(result){
                        if(result.data.errcode==1){
                            //请求成功
                            var url = result.data.object;
                            console.log(url);
                            var editSqlModalInstance = $modal.open({
                                templateUrl: 'views/modals/account_pay.html',
                                controller: 'AccountPayCtrl',
                                controllerAs: 'accountPayCtrl',
                                backdrop: 'static',
                                windowClass: 'overflow-y-auto chart-modal',
                            });
                        }
                    }
                });

        };
    }
})()