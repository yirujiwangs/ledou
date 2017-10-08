(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceEditCtrl', DeviceEditCtrl);

    DeviceEditCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','AlertService','deviceDetail','DeviceService','utilService','dataService'];

    function DeviceEditCtrl($scope,$rootScope,$modalInstance,AlertService,deviceDetail,DeviceService,utilService,dataService){
        var vm=this;
        $scope.form=deviceDetail.device;
        vm.account=deviceDetail.account;
        vm.types=dataService.types;
        var i=utilService.arrIndexOf(vm.types,{name:$scope.form.type})
        if(i==-1){
            i=0;
        }
        $scope.form.type=vm.types[i].name
        vm.close=function(){
            $modalInstance.dismiss()
        }
        vm.ok=function(){
            DeviceService.deviceEdit(vm.account,$scope.form.ibeaconId,$scope.form.type,$scope.form.remark)
            .then(function(res){
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"修改成功"});
                    $modalInstance.dismiss('cancel');
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请重新添加"});
                    $modalInstance.dismiss('cancel');
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
                $modalInstance.dismiss('cancel');
            })
        }
    }
})()