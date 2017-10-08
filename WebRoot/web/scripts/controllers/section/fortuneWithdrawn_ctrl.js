(function(){
	'use strict';

    angular
        .module('center')
        .controller('FortuneWithdrawnCtrl', FortuneWithdrawnCtrl);

    FortuneWithdrawnCtrl.$inject = [ '$scope', '$rootScope','dataService','FortuneService','AlertService','$modal'];

    function FortuneWithdrawnCtrl($scope,$rootScope,dataService,FortuneService,AlertService,$modal){
    	for(var i=0;i<dataService.buttons.length;i++){
            dataService.buttons[i].click=false
        }
        dataService.buttons[2].click=true;

        var vm=this;
        vm.titles=dataService.fortuneWithdrawnTitles;
        vm.conversion=dataService.conversion

        FortuneService.getWithdrawnItems()
        .then(function(res){
            vm.allDeposit=res.data.totalRefund;
        })

        vm.confirm=function(index){
            vm.withDrawnItem=vm.allDeposit[index];
            vm.withDrawnItemIndex=index;
            var rechangeModalInstance = $modal.open({
                templateUrl: 'views/modals/fortune_confirm.html',
                controller: 'FortuneConfirmCtrl',
                controllerAs: 'fortuneConfirmCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     confirm: function () {
                         return 'fortuneWithdrawnConfirm'
                      }
                  }
            });
        }
        $scope.$on('fortuneWithdrawnConfirm',function(event){
            FortuneService.withdrawnConfirm({outTradeNo:vm.withDrawnItem.outTradeNo,type:1})
            .then(function(res){
                if(res.data.flag){
                    vm.allDeposit.splice(vm.withDrawnItemIndex,1)
                    AlertService.alert({success:true,msg:"操作成功"})
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请重式"})
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err})
            })
        })
        vm.reject=function(index){
            vm.rejectItem=vm.allDeposit[index];
            var rechangeModalInstance = $modal.open({
                templateUrl: 'views/modals/fortune_confirm.html',
                controller: 'FortuneConfirmCtrl',
                controllerAs: 'fortuneConfirmCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     confirm: function () {
                         return 'fortuneWithdrawnRejectConfirm'
                      }
                  }
            });
        }
        $scope.$on('fortuneWithdrawnRejectConfirm',function(event){
            var rechangeModalInstance = $modal.open({
                templateUrl: 'views/modals/fortune_reject.html',
                controller: 'FortuneRejectCtrl',
                controllerAs: 'fortuneRejectCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     datas: function () {
                         return vm.rejectItem
                      }
                  }
            });
        })
    }
})()