/**
 * Created by admin on 2017/5/27.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceSettingsCtrl', DeviceSettingsCtrl);

    DeviceSettingsCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','DeviceService','deviceData','base64Service','$timeout'];

    function DeviceSettingsCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,deviceService,deviceData,base64Service,$timeout){
        if(sessionStorage.proxyType) {
            $scope.proxyType = base64Service.base64decode(sessionStorage.proxyType);
            $scope.policy=sessionStorage.policy;
        }
        else if(localStorage.proxyType){
            $scope.proxyType = base64Service.base64decode(localStorage.proxyType);
            $scope.policy=localStorage.policy;
        }
        var vm = this;
        // 表单数据初始化
        $scope.deviceId = deviceData.iBeaconID;

        //如果已经激活，为false，未激活为true
        $scope.deviceState = deviceData.status;
        $scope.form = {};
        $scope.form.comment = '';
        $scope.clickable = false;
        init($scope.deviceId);
        $scope.redPercent=0;

        //设置默认值
        $scope.price=0;
        $scope.form.ownPrice=100;

        if($scope.policy==1)$scope.form.ownPercent=20+"%";
        else if($scope.policy==2)$scope.form.ownPercent=15+"%";
        $scope.$watch("price",function (n) {
            $scope.form.ownPrice = (100-n);
        });
        $scope.$watch("redPercent",function (n) {
            if($scope.policy==1){
                !isNaN(20-n)?$scope.form.ownPercent = (20-n)+"%":$scope.form.ownPercent="超出范围";
            }
            else if($scope.policy==2){
                !isNaN(15-n)?$scope.form.ownPercent = (15-n)+"%":$scope.form.ownPercent="超出范围";
            }
        });
        var param = {};
        vm.submit = function() {
            var activePercent = $scope.price;
            var redPercent =$scope.redPercent;
            var comment= $scope.form.comment;

            if (activePercent > 100) {
                AlertService.alert({ msg: "服务商激活补贴不能大于100"});
                return;
            }

            if(!$scope.redPercent){
                if($scope.policy==1)AlertService.alert({ msg: "请输入0-20内激活返利百分比"});
                else if($scope.policy==2)AlertService.alert({ msg: "请输入0-15内激活返利百分比"});
                return;
            }
            param.aliveCodes=deviceData.iBeaconID.split();
            param.activePercent=parseInt(activePercent);
            param.redPercent=parseInt(redPercent);
            param.comment=comment;
            param=angular.fromJson(param);
            deviceService.settingSubmit(param)
                .then(function (res) {
                    if (res && res.data) {
                        if (res.data.errcode == 1) {
                            AlertService.alert({success: true, msg: res.data.errmsg});
                            $timeout(function(){
                                $route.reload();
                            },1500);
                            vm.close();

                        }else{
                            AlertService.alert({success: false, msg: res.data.errmsg});
                        }
                    }
                });
        };

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        };


        /**
         *
         * 初始化设备的返利参数
         *
         */
        function init(deviceId){
            if(deviceId==null || deviceId.length!=12){
                return;
            }

            var param = {};
            param.aliveCode = deviceId;

            deviceService.settingInfo(param)
                .then(function (res) {
                    if (res && res.data) {
                        if (res.data.errcode == 1) {
                            console.log(res)
                            if (res.data.object) {
                                var data = angular.fromJson(res.data.object);
                                param.comment = data.comment;
                                param.activePercent=parseInt(data.active_percent/100);
                                param.redPercent=parseInt(data.red_percent);
                                $scope.clickable = false;
                            }else{
                                if($scope.deviceState=='A') {
                                    $scope.clickable = true;
                                }
                            }
                        }else{
                            AlertService.alert({success: false, msg: res.data.errmsg});
                        }
                    }
                });
        };
    }
})()