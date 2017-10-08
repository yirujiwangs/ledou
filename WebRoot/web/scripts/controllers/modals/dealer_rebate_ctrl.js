(function(){
	'use strict';

    angular
        .module('center')
        .controller('RebateSetCtrl', RebateSetCtrl);

    RebateSetCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','accountService'];

    function RebateSetCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,accountService){
    	var vm = this;

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()