/**
 * Created by admin on 2017/5/27.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceActiveReturnCtrl', DeviceActiveReturnCtrl);

    DeviceActiveReturnCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','DeviceService','base64Service','$timeout'];

    function DeviceActiveReturnCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,deviceService,base64Service,$timeout){
        if(sessionStorage.proxyType) {
            $scope.proxyType = base64Service.base64decode(sessionStorage.proxyType);
            $scope.policy=sessionStorage.policy;
        }
        else if(localStorage.proxyType){
            $scope.proxyType = base64Service.base64decode(localStorage.proxyType);
            $scope.policy=localStorage.policy;
        }
        var vm = this;
        var param = {};
        //激活补贴    设备分成
        $scope.form={};

        $scope.form.comment='';

        $scope.startDeviceNo='';
        $scope.endDeviceNo='';

        $scope.deviceNum =0 ;
        $scope.redPercent=0;

        $scope.pattern=/^\d{12}$/;

        //设置默认值
        $scope.price=0;
        $scope.form.ownPrice=100;

        //ngif中使用ngmodel无效
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

                !isNaN(15-n)?$scope.form.ownPercent = (15-n)+"%":$scope.form.ownPercent="超出范围";
            }
        });

        $scope.$watch("startDeviceNo",function () {
            if ($scope.endDeviceNo != "") {
                if ($scope.endDeviceNo >= $scope.startDeviceNo) {
                    $scope.deviceNum = $scope.endDeviceNo - $scope.startDeviceNo + 1;
                }
            }
        });

        $scope.$watch("endDeviceNo",function () {
            if ($scope.endDeviceNo != "") {
                if ($scope.endDeviceNo >= $scope.startDeviceNo) {
                    $scope.deviceNum = $scope.endDeviceNo - $scope.startDeviceNo + 1;
                }
            }
        });



        vm.submit = function() {

            var activePercent = $scope.price;;
            var redPercent = $scope.redPercent;
            var comment=  $scope.form.comment;
            var deviceArray = [];

            if (activePercent > 100) {
                AlertService.alert({ msg: "服务商激活补贴不能大于100"});
                return;
            }

            if(!$scope.redPercent){
                if($scope.policy==1)AlertService.alert({ msg: "请输入0-20内激活返利百分比"});
                else if($scope.policy==2)AlertService.alert({ msg: "请输入0-15内激活返利百分比"});
                return;
            }
            if($scope.startDeviceNo=='' || $scope.endDeviceNo=='' || $scope.startDeviceNo > $scope.endDeviceNo){
                AlertService.alert({ msg: "请输入合法的序列号"});
                return;
            }


            for(var i =$scope.startDeviceNo; i <= $scope.endDeviceNo; i ++){
                deviceArray.push( parseInt(i)+'');
            }

                param.aliveCodes=deviceArray;
                param.activePercent = parseInt(activePercent);
                param.redPercent = parseInt(redPercent);
                param.comment = comment;

                param = angular.fromJson(param);

                deviceService.settingSubmit(param)
                    .then(function (res) {
                        console.log(param);
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
        }
    }
})()