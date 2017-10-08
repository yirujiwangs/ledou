(function(){
	'use strict';

    angular
        .module('center')
        .controller('DealerConfirmCtrl', DealerConfirmCtrl);

    DealerConfirmCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','$modalInstance','$modal','AlertService','dealerService','dealerData'];

    function DealerConfirmCtrl($scope,$rootScope,$location,$route,$modalInstance,$modal,AlertService,dealerService,dealerData){
    	var vm = this;
        var id=dealerData;
        vm.ok = function(){
            dealerService.status({"status":"N","id":id})
                .then(function(res){
                    console.log(res);
                    if(res.data.errcode =="1"){
                        AlertService.alert({success:true,msg:res.data.errmsg});
                    }else{
                        AlertService.alert({success:true,msg:res.data.errmsg});
                    }
            });
            $modalInstance.dismiss('cancel');
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()