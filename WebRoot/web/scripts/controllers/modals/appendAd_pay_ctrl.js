(function(){
	'use strict';

    angular
        .module('center')
        .controller('AppendAdPayCtrl', AppendAdPayCtrl);

    AppendAdPayCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance','dataService','accountService','$modal',"$location"];

    function  AppendAdPayCtrl($scope,$rootScope,$route,$modalInstance,dataService,accountService,$modal,$location){
        var vm=this;

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
        vm.success = function(){
            $modalInstance.dismiss('cancel');
            if($location.path()=="/appendPageListAd"){
                $location.path("/startPageListAd")
            }else if($location.path()=="/appendAd"){
                $location.path('/listAd');
            }else if($location.path()=="/appendRedAd"){
                $location.path('/listRedAd');
            }else if($location.path()=='/createRedPacket'){
                $location.path('/attractFansRedPacket');
            }
            $route.reload();
        }
        vm.repay = function(){
            $modalInstance.dismiss('cancel');
            //$('#payConfirm')[0].click();
        }
    }
})()