(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountAddCtrl', AccountAddCtrl);

    AccountAddCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','accountService'];

    function AccountAddCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,accountService){
        var vm = this;

        // 设定三级联动容器左浮动
        $scope.align = true;
        // 设定三级联动必选
        $scope.require = true;
        //默认选择新代理
        $scope.policy=2;
        //  默认选择签约方式为绿豆芽公司
        $scope.signway=1;
        //默认选择区县代理
        $scope.proxyType='P';
        // 表单数据初始化
        $scope.form = {};
        $scope.form.status = true;
        // 定义商家类型
        vm.storeTypes = [{name:'普通类型'},{name:'平台类型'}];
        // storetype默认选择普通商家
        $scope.form.storeType=vm.storeTypes[0].name;
    	vm.add = function(){
            $scope.form.policy=parseInt($scope.policy);
            $scope.form.areaCode = $scope.areaCode;
            $scope.form.signway=parseInt($scope.signway);
            $scope.form.franchiseFee=parseInt($scope.franchiseFee*100);
            $scope.form.proxyType= $scope.proxyType;
            accountService.adduser($scope.form)
            .then(function(res){
                console.log($scope.form,"传递给后台");
                if(res.data.errcode === 1){
                    AlertService.alert({success:true,msg:"创建代理区域成功"});
                    $route.reload();
                }
                else{
                    AlertService.alert({success:false,msg:"创建失败，请稍候再试"});
                    $route.reload();
                }
            })
            $modalInstance.dismiss('cancel');
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()