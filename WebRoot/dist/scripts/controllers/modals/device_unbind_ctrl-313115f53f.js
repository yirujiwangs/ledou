(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceUnbindCtrl', DeviceUnbindCtrl);

    DeviceUnbindCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance','AlertService','serialNum','DeviceService'];

    function DeviceUnbindCtrl($scope,$rootScope,$route,$modalInstance,AlertService,serialNum,DeviceService){
        var vm=this;
        // console.log(serialNum);
        vm.close=function(){
            $modalInstance.dismiss()
        }
        vm.ok=function(){
            DeviceService.removeBind(serialNum)
            .then(function(res){
                // console.log(res);
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"解绑成功"});
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请稍后重试"});
                }
                $modalInstance.dismiss('cancel');
                $route.reload();
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
                $modalInstance.dismiss('cancel');
            })
        }
    }
})()