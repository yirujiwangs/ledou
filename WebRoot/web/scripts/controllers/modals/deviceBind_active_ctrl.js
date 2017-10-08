(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceBindActiveCtrl', DeviceBindActiveCtrl);

    DeviceBindActiveCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','AlertService','deviceDetail','DeviceService','utilService','dataService'];

    function DeviceBindActiveCtrl($scope,$rootScope,$modalInstance,AlertService,deviceDetail,DeviceService,utilService,dataService){
        var vm=this;
        $scope.id=deviceDetail.id;
        
        vm.close=function(){
            $modalInstance.dismiss();
        }
        vm.ok=function(){
            DeviceService.activate($scope.id)
            .then(function(res){
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"激活成功"})
                    $modalInstance.dismiss('cancel');
                    dataService.deviceActiveDates.status="激活"
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请重新添加"})
                    $modalInstance.dismiss('cancel');
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err})
                $modalInstance.dismiss('cancel');
            })
        }
    }
})()