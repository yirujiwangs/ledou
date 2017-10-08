/**
 * Created by admin on 2017/5/27.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceCancelCtrl', DeviceCancelCtrl);

    DeviceCancelCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','DeviceService',"statusData"];

    function DeviceCancelCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,DeviceService,statusData){
            var vm = this;
            var param = {};
            $scope.groupId=statusData;
            vm.add = function(record) {
                DeviceService.delGroup(groupId)
                        .then(function (res) {
                            console.log(res);
                            var flag = angular.fromJson(res.data.object);
                            if (flag) {
                                AlertService.alert({success: true, msg: "É¾³ý³É¹¦"});
                                $route.reload();
                            }


                })
            }
            vm.close = function(){
                $modalInstance.dismiss('cancel');
            }

    }
})()