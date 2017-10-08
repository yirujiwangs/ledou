(function(){
	'use strict';

    angular
    .module('center')
    .controller('DeviceUpdateRemarkCtrl', DeviceUpdateRemarkCtrl);

    DeviceUpdateRemarkCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$modalInstance','utilService','$modal','AlertService','deviceService','DeviceUpdateRemarkData','$route'];

    function DeviceUpdateRemarkCtrl($scope,$rootScope,dataService,$location,$modalInstance,utilService,$modal,AlertService,deviceService,DeviceUpdateRemarkData,$route){
        var vm=this;
        $scope.form=DeviceUpdateRemarkData;
        var page = {};
        page.startPage = 1;
        page.pageSize = 15;
        vm.update=function(){
            deviceService.updateRemark($scope.form)
            .then(function(res){
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"操作成功"})
                    $modalInstance.dismiss('cancel');
                    setTimeout($route.reload(),300);
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
            })  
        }
        vm.close=function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()