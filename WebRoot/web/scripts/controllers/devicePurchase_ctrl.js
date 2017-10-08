/**
 * Created by admin on 2017/4/14.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DevicePurchaseCtrl', DevicePurchaseCtrl);

    DevicePurchaseCtrl.$inject = [ '$scope', '$rootScope','$route','dataService','base64Service','DevicePurchaseService','AlertService','$modal','$location','$timeout'];

    function DevicePurchaseCtrl($scope,$rootScope,$route,dataService,base64Service,devicePurchaseService,AlertService,$modal,$location,$timeout){
        var proxyType;
        if(sessionStorage.proxyType)proxyType = base64Service.base64decode(sessionStorage.proxyType);
        else if(localStorage.proxyType)proxyType = base64Service.base64decode(localStorage.proxyType);
        var vm=this;
        // 表单数据初始化
        $scope.form = {};
        $scope.amount=50;
        $scope.form.remark = '购买设备';
        $scope.buyPrice=0;
        $scope.policyReduct=0;

        //设备类型初始默认为N普通设备
        $scope.deviceType ='N';
        $scope.parseInt = function(num){
            return parseInt(num);
        }
        var param = {};

    /*
        devicePurchaseService.listTotal(param)  //前台给后天传参page
            .then(function(res){           //后台给前台传递过来的数据
                console.log(res);
                vm.generalNum=res;

            });
    */

        devicePurchaseService.devicePrice({proxyType:proxyType})  //前台给后天传参page
            .then(function(res){           //后台给前台传递过来的数据
                if(res.data.errcode==1){
                    $scope.buyPrice=res.data.object.buyPrice;
                    $scope.policyReduct=res.data.object.policyReduct;
                }
            });


        vm.add = function(){
            if($scope.amount){
                if(parseInt($scope.amount)<10000) {
                    //$scope.amount=number(document.getElementById("deviceNum").value);
                    $scope.amount = parseInt($scope.amount) + 1;
                }
            }
            else{
                $scope.amount = 50;
            }
        }
        vm.minus = function(){
            if(parseInt($scope.amount) > 50 ){
                //$scope.amount=number(document.getElementById("deviceNum").value);
                $scope.amount =parseInt( $scope.amount)- 1;
            }
            else{
                $scope.amount = 50;
            }
        };

        vm.confirm=function(){

            if (!$scope.proxyName) {
                AlertService.alert({success: false, msg: "请填写收货人"});
                return;
            }
            if (!$scope.contact) {
                AlertService.alert({success: false, msg: "请填写联系方式"});
                return;
            }
            if (!$scope.address) {
                AlertService.alert({success: false, msg: "请填写收件地址"});
                return;
            }
            if ($scope.amount <50 ||$scope.amount >10000) {
                AlertService.alert({success: false, msg: "采购数量必须在50——10000台之间"});
                return;
            }
            if(!$scope.reason){
                AlertService.alert({success: false, msg: "请填写设备用途"});
                return;
            }

            if($scope.proxyName !=""&& $scope.contact != "" && $scope.address != ""&&($scope.amount >50  || $scope.amount <1000)){
                //获取二维码
                //给后台传递数据
                param.name = $scope.proxyName;//收货信息
                param.contact = $scope.contact;
                param.address = $scope.address;
                param.amount = $scope.amount; //采购数量
                param.deviceType = $scope.deviceType;//设备类型N/S
                param.ruleId = 1;//设备类型
                param.buyPrice=$scope.buyPrice;
                param.policyReduct=$scope.policyReduct;
                param.totalPrice = ($scope.buyPrice-$scope.policyReduct)*$scope.amount;
                param.unitPrice =$scope.buyPrice-$scope.policyReduct;
                param.reason=$scope.reason;//申报原因
                devicePurchaseService.buyUrl(param)
                    .then(function (result) {
                    if(result){
                        if(result.data.errcode==1){
                            AlertService.alert({success: true, msg:result.data.errmsg});
                            $timeout(function(){
                                //模拟鼠标点击
                                var buttons=document.querySelectorAll("a");
                                var buttonsLen=buttons.length;
                                for(var i=0;i<buttonsLen;++i){
                                    if(buttons[i].innerText=='订单管理'){
                                        //ie
                                        if(document.all) {
                                            buttons[i].parentNode.click();
                                        }
                                        //其他浏览器
                                        else{
                                            var e = document.createEvent("MouseEvents");
                                            e.initEvent("click", true, true);
                                            buttons[i].parentNode.dispatchEvent(e);
                                        }
                                    }
                                }
                            },2000)
                        }
                        else if(result.data.errcode==-1)AlertService.alert({success: false, msg:result.data.errmsg});
                    }
                });
            }
        };
    }
})()