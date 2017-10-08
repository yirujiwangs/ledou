(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceRevCtrl', DeviceRevCtrl);

    DeviceRevCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','AlertService','DeviceService','utilService','dataService','deviceData'];

    function DeviceRevCtrl($scope,$rootScope,$modalInstance,AlertService,DeviceService,utilService,dataService,deviceData){
        var vm=this;
        vm.close=function(){
            $modalInstance.dismiss()
        }
        vm.ok=function(){
            // console.log(deviceData);
            var newRemark = $scope.form.remark;
            // console.log(newRemark);
            if(newRemark === deviceData.remark){
                AlertService.alert({success:true,msg:"修改成功"});
                $modalInstance.dismiss('cancel');
            }
            else{
                deviceData.remark = newRemark;
                // console.log(deviceData);
                DeviceService.deviceRev(deviceData)
                .then(function(res){
                    // console.log(res);
                    if(res.data.flag){
                        AlertService.alert({success:true,msg:"修改成功"});
                    }
                    else{
                        AlertService.alert({success:false,msg:"修改失败，请稍后再试"});                    
                    }
                    $modalInstance.dismiss('cancel');
                })
            }
        }
    }
})()