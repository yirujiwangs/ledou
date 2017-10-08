/**
 * Created by admin on 2017/4/20.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceStockStatusCtrl', DeviceStockStatusCtrl);

    DeviceStockStatusCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','utilService','$modal','AlertService','DeviceStockService','statusData'];

    function DeviceStockStatusCtrl($scope,$rootScope,$location,$route,$modalInstance,utilService,$modal,AlertService,deviceStockService,statusData){
        var vm = this;
        deviceStockService.logistics(statusData)
            .then(function(res){
                if(res) {
                    if (res.data.errcode == 1) {
                        console.log(res.data,"弹出信息");
                        $scope.logistics = res.data.object;
                    }
                }
            });
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
        $scope.isShow = function(){
            if(statusData.dNo==""){
                return true;
            }else{
                return false;

            }
        }
    }
})()