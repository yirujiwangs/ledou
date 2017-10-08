(function(){
    'use strict';

    angular
        .module('center')
        .controller('AllDeviceRevCtrl', AllDeviceRevCtrl);

    AllDeviceRevCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance','AlertService','DeviceService','utilService','dataService','deviceData'];

    function AllDeviceRevCtrl($scope,$rootScope,$route,$modalInstance,AlertService,DeviceService,utilService,dataService,deviceData){
        var vm=this;
        vm.close=function(){
            $modalInstance.dismiss()
        }
        vm.ok=function(){
            var newRemark = $scope.form.remark;
            if(newRemark === deviceData.remark){
                AlertService.alert({success:true,msg:"修改成功"});
                $modalInstance.dismiss('cancel');
            }
            else{
                deviceData.remark = newRemark;
                DeviceService.allDeviceRev(deviceData)
                .then(function(res){
                    var flag = angular.fromJson(res.data.object);
                    if(flag){
                        AlertService.alert({success:true,msg:"修改成功"});
                        $route.reload();
                    }
                    else{
                        AlertService.alert({success:false,msg:"修改失败，请稍后再试"});                    
                    }
                })
                $modalInstance.dismiss('cancel');
            }
        }
    }
})()