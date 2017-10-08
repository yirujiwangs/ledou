///**
// * Created by admin on 2017/4/26.
// */
(function(){
    'use strict';
    angular
        .module('center')
        .controller('DeviceStockDetailCtrl', DeviceStockDetailCtrl);

    DeviceStockDetailCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','dataService','$modalInstance','$modal','refundService','AlertService','deviceStockService','statusData'];

    function DeviceStockDetailCtrl($scope,$rootScope,$location,$route,dataService,$modalInstance,$modal,refundService,AlertService,deviceStockService,statusData){
        var vm = this;
        // 表单数据初始化
        $scope.form = {};
        $scope.form.tradeNo=statusData.tradeNo;
        $scope.form.logisticNo = statusData.logisticNo;

       // 备货状态
        vm.storeTypes = [{name:'拒绝'},{name:'备货中'},{name:'已发货'}];

        //执行获取已存在的物流信息
        getLogistic();

        vm.add = function(){
            var radionum = document.getElementsByName("status");
            for(var i=0;i<radionum.length;i++) {
                if (radionum[i].checked) {
                    $scope.form.status = radionum[i].value;
                }
            };
            deviceStockService.updateLogistic($scope.form)
                .then(function(res){
                    if(res.data.errcode){
                        AlertService.alert({success:true,msg:"修改成功"});
                    }
                    else{
                        AlertService.alert({success:false,msg:"修改失败"});
                    }
                })
                .catch(function(err){
                    AlertService.alert({success:false,msg:err});
                });
            $modalInstance.dismiss('cancel');
        };

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        };

        function getLogistic(){
            var statusData = {};
            statusData.dNo = $scope.form.logisticNo;
            deviceStockService.logistics(statusData)
                .then(function(res){
                    if(res) {
                        if (res.data.errcode == 1) {
                            console.log(res.data,"弹出信息");
                            var logistics = res.data.object;
                            $scope.form.status = logistics.status;
                            $scope.form.logisticCompany = logistics.logistic_company;
                        }
                    }
                });
        }
    }
})()
