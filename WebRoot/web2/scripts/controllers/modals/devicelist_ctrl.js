/**
 * Created by admin on 2017/5/11.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DevicelistCtrl', DevicelistCtrl);

    DevicelistCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','deviceStockService'];

    function DevicelistCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,deviceStockService){
        var vm = this;

        // 设定三级联动容器左浮动
        $scope.align = true;
        // 设定三级联动必选
        $scope.require = true;

        // 表单数据初始化
        $scope.form = {};
        vm.delivery = function () {

            var accountVal = document.getElementById("phone").value;
            var datatimeVal = document.getElementById("time").value;
            var numberVal = document.getElementById("amount").value;
            var nameVal = document.getElementById("name").value;
            var accountNumVal = document.getElementById("contact").value;
            var addressVal = document.getElementById("address").value;
            var checkName = document.getElementsByName("deviceType");
            var reasonVal=document.getElementById("reason").value;

            for (var i = 0; i < checkName.length; i++) {
                if (checkName[i].checked) {
                    $scope.form.deviceType = checkName[i].value;
                }
            }

            if (accountVal == "") {
                AlertService.alert({success: false, msg: "请输入运营商"});
                return;
            }
            if (datatimeVal == "") {
                AlertService.alert({success: false, msg: "请输入时间"});
                return;
            }
            if (numberVal == "") {
                AlertService.alert({success: false, msg: "请输入采购数量"});
                return;
            }
            if (nameVal == "") {
                AlertService.alert({success: false, msg: "请输入收货人姓名"});
                return;
            }
            if (accountNumVal == "") {
                AlertService.alert({success: false, msg: "请输入您的联系方式"});
                return;
            }
            if (addressVal == "") {
                AlertService.alert({success: false, msg: "请填写收件地址"});
                return;
            }
            if(reasonVal == ""){
                AlertService.alert({success: false, msg: "请填写申请理由"});
                return;
            }


            $scope.form.unitPrice = 100 * $scope.form.unitPrice;
            deviceStockService.createDeviceOrder($scope.form)
                    .then(function (res) {
                        if (res.data.errcode === 1) {
                            AlertService.alert({success: true, msg: "创建设备订单成功"});
                            $route.reload();
                        }
                        else {
                            AlertService.alert({success: false, msg: "创建失败，请稍候再试"});
                            $route.reload();
                        }
                    });
                $modalInstance.dismiss('cancel');

        };

        vm.close = function () {
            $modalInstance.dismiss('cancel');
        }
    }
})()