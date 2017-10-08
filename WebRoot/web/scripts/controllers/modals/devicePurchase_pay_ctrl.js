/**
 * Created by admin on 2017/4/21.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DevicePurchasePayCtrl', DevicePurchasePayCtrl);

    DevicePurchasePayCtrl.$inject = [ '$scope','DevicePurchaseService', '$rootScope','$location','$route','$modalInstance','dataService','$modal','param'];

    function DevicePurchasePayCtrl($scope,devicePurchaseService,$rootScope,$location,$route,$modalInstance,dataService,$modal,param){
        var vm=this;
        $scope.url = param.url;


        var id =  window.setInterval(function(){
            devicePurchaseService.bindWxAccount(param)
                .then(function(data){
                    console.log("ajax成功")
                    if(data) {
                        if (data.data) {
                            if (data.data.errcode == 1) {//请求成功
                                if(data.data.errmsg=='OK') {

                                    vm.close();//关闭（二维码支付）模态框
                                    //$location.path('/deviceStock');//页面跳转到订单管理
                                    $location.path('/index.html#/deviceStock');//页面跳转到订单管理
                                }
                            }
                        }
                    }
                })
        },5000);

        vm.close = function(){
            clearInterval(id);
            $modalInstance.dismiss('cancel');
        }
        vm.success = function(){
            clearInterval(id);
            $modalInstance.dismiss('cancel');
            $route.reload();
        }
        vm.cancel = function(){
           vm.close();
        }

    }
})()