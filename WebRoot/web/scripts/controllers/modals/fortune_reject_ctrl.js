(function(){
	'use strict';

    angular
        .module('center')
        .controller('FortuneRejectCtrl', FortuneRejectCtrl);

    FortuneRejectCtrl.$inject = [ '$scope', '$rootScope','dataService','FortuneService','AlertService','$modalInstance','datas'];

    function FortuneRejectCtrl($scope,$rootScope,dataService,FortuneService,AlertService,$modalInstance,datas){
    	$scope.reason='';
        var vm=this;
        vm.datas=datas;
        
        vm.ok=function(){
            if($scope.reason==''){
                AlertService.alert({success:false,msg:"请填写原因"})
            }
            else{
                FortuneService.withdrawnConfirm({outTradeNo:vm.datas.outTradeNo,reason:$scope.reason,type:0,fee:vm.datas.fee})
                .then(function(res){
                    console.log(res)
                    if(res.data.flag){
                        AlertService.alert({success:true,msg:"操作成功"})
                    }
                    else{
                        AlertService.alert({success:false,msg:"操作失败，请重试"})
                    }
                    $modalInstance.dismiss()
                })
                .catch(function(err){
                    AlertService.alert({success:false,msg:err})
                })
            }
            
        }
        vm.close=function(){
            $modalInstance.dismiss()
        }
    }
})()