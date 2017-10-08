
(function(){
    'use strict';
    angular
        .module('center')
        .controller('DeviceSettingCtrl', DeviceSettingCtrl);

    DeviceSettingCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','dataService','$modalInstance','$modal','refundService','AlertService','deviceService'];

    function DeviceSettingCtrl($scope,$rootScope,$location,$route,dataService,$modalInstance,$modal,refundService,AlertService,deviceService){
        var vm = this;
        // 表单数据初始化
        $scope.form = {};
        //默认选择新政策
        $scope.form.policy=2;

        $scope.pattern=/^\d{12}$/;


        vm.add = function(){
            /*
            var start=Number($scope.start);
            var end=Number($scope.end);
            if(end<start){
                AlertService.alert({success:false,msg:"结束单号不能小于起始单号"});
            }
            $scope.form.serialNumList=[];
            for(var i=start;i<=end;i++){
                $scope.form.serialNumList.push(i+'');
            }
            */
            $scope.form.startAliveCode=$scope.start+"";
            $scope.form.endAliveCode=$scope.end+"";
            $scope.form.policy=parseInt($scope.form.policy);
           deviceService.updateDeviceSetting($scope.form)
                .then(function(res){
                    if(res.data.errcode==0 ||res.data.errcode==1){
                        AlertService.alert({success:true,msg:res.data.errmsg});
                    }
                    else{
                        if( res.data.errmsg)AlertService.alert({success:false,msg:res.data.errmsg});
                        else AlertService.alert({success:false,msg:"修改失败"});
                    }
                })
                .catch(function(err){
                    AlertService.alert({success:false,msg:err});
                });
            $modalInstance.dismiss('cancel');
        };

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        };
    }
})()
/**
 * Created by Administrator on 2017/9/12 0012.
 */
