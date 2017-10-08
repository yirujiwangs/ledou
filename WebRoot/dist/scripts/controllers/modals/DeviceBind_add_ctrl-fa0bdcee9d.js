(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceBindAddCtrl', DeviceBindAddCtrl);

    DeviceBindAddCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','AlertService','DeviceService','utilService','dataService','$location'];

    function DeviceBindAddCtrl($scope,$rootScope,$modalInstance,AlertService,DeviceService,utilService,dataService,$location){
        var vm=this;
        $scope.form={};
        vm.types=dataService.types;
        $scope.form.type=vm.types[0].name
        vm.close=function(){
            $modalInstance.dismiss();
        }
        vm.ok=function(){
            var reg=new RegExp("^.{12}$");
            if(!reg.test($scope.form.serialNum)){
                AlertService.alert({success:false,msg:"设备序列号只能为12位"})
                return
            }
            AlertService.alert({success:true,msg:"添加时间较长，请耐心等待"})
            DeviceService.addToWeixin($scope.form)
            .then(function(res){
                // console.log(res);
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"添加成功"});
                    $modalInstance.dismiss('cancel');
                    $location.path('/deviceBind');
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