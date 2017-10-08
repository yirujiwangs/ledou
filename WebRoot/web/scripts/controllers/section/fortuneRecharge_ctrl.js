(function(){
	'use strict';

    angular
        .module('center')
        .controller('FortuneRechargeCtrl', FortuneRechargeCtrl);

    FortuneRechargeCtrl.$inject = [ '$scope', '$rootScope','dataService','FortuneService','AlertService','$modal','loginDao'];

    function FortuneRechargeCtrl($scope,$rootScope,dataService,FortuneService,AlertService,$modal,loginDao){
    	for(var i=0;i<dataService.buttons.length;i++){
            dataService.buttons[i].click=false
        }
        dataService.buttons[2].click=true;

        var vm=this;
        vm.fortuneDetail=dataService.fortuneDetail;
        vm.titles=dataService.fortuneRechargeTitles;
        vm.conversion=dataService.conversion
        FortuneService.getRechargeTips()
        .then(function(res){
            vm.allDeposit=res.data.allDeposit
        })

        vm.confirm=function(index){
            vm.rechangeItem=vm.allDeposit[index];
            vm.rechangeItemIndex=index;
            var rechangeModalInstance = $modal.open({
                templateUrl: 'views/modals/fortune_confirm.html',
                controller: 'FortuneConfirmCtrl',
                controllerAs: 'fortuneConfirmCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     confirm: function () {
                         return 'fortuneRechargeConfirm'
                      }
                  }
            });
        }
        $scope.$on('fortuneRechargeConfirm',function(event,form){
            loginDao.check(form)
            .then(function(res){
                if(res.data.loginFlag){
                    FortuneService.rechargeConfirm({outTradeNo:vm.rechangeItem.outTradeNo})
                    .then(function(res){
                        if(res.data.flag){
                            vm.allDeposit.splice(vm.rechangeItemIndex,1)
                            AlertService.alert({success:true,msg:"添加成功"})
                        }
                        else{
                            AlertService.alert({success:false,msg:"操作失败，请重新添加"})
                        }
                    })
                    .catch(function(err){
                        AlertService.alert({success:false,msg:err})
                    })
                }
                else{
                    AlertService.alert({success:false,msg:"用户名或密码错误"})
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err})
            })
            /*FortuneService.rechargeConfirm({outTradeNo:vm.allDeposit[index].outTradeNo})
            .then(function(res){
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"添加成功"})
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请重新添加"})
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err})
            })*/
        })
    }
})()