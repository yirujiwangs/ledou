(function(){
    'use strict';

    angular
        .module('center')
        .controller('RefundProcessCtrl', RefundProcessCtrl);

    RefundProcessCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','refundService','refundProcessData'];

    function RefundProcessCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,refundService,refundProcessData){
        var vm = this;
        // tradeState
        // 0: 待结算 1: 已结算 2: 已拒绝
        var param = {};
        param.tradeId = refundProcessData.tradeId;
        vm.ok = function(){
            param.tradeState = 1;
            refundService.process(param)
                .then(function(res){
                    if(res.data.flag){
                        AlertService.alert({success:true,msg:"处理成功"});
                        $route.reload();
                    }
                    else{
                        AlertService.alert({success:false,msg:"处理失败，请稍候再试"});
                        $route.reload();
                    }
                })
            $modalInstance.dismiss('cancel');
        }
        vm.refuse = function(){
            param.tradeState = 2;
            refundService.process(param)
                .then(function(res){
                    if(res.data.flag){
                        AlertService.alert({success:true,msg:"处理成功"});
                        $route.reload();
                    }
                    else{
                        AlertService.alert({success:false,msg:"处理失败，请稍候再试"});
                        $route.reload();
                    }
                })
            $modalInstance.dismiss('cancel');
        }

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()