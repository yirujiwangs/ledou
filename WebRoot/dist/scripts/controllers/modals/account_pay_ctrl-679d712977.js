(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountPayCtrl', AccountPayCtrl);

    AccountPayCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance','dataService','accountService','$modal'];

    function AccountPayCtrl($scope,$rootScope,$route,$modalInstance,dataService,accountService,$modal){
        var vm=this;
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
        vm.success = function(){
            $modalInstance.dismiss('cancel');
            $route.reload();
        }
        vm.repay = function(){
            $('#confirm')[0].click();
        }
    }
})()