(function(){
	'use strict';

    angular
        .module('center')
        .controller('MerchantAllotCtrl', MerchantAllotCtrl);

    MerchantAllotCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','dealerService','dealerData'];

    function MerchantAllotCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,dealerService,dealerData){
    	var vm = this;
        var data = dealerData;
        vm.stockLeft = dealerData.stock;

        vm.ok = function(){
            dealerService.distributeMerchant({"id":dealerData.id,"grantNum":$scope.form.grantNum})
                .then(function(res){
                    // console.log(res);
                    if(res.data.errcode =="1"){
                        AlertService.alert({success:true,msg:res.data.errcode});
                    }
                    else{
                        AlertService.alert({success:false,msg:res.data.errcode});
                    }
                });
            $modalInstance.dismiss('cancel');
            $route.reload();
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()