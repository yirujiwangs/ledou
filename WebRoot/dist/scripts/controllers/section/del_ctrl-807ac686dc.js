(function(){
	'use strict';

    angular
        .module('center')
        .controller('DelCtrl', DelCtrl);

    DelCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','AlertService','service'];

    function DelCtrl($scope,$rootScope,$modalInstance,AlertService,service){
    	var vm=this;
    	this.close=function(){
    		$modalInstance.dismiss('cancel');
    	}
        this.ok=function(){
            $rootScope.$broadcast(service)
            $modalInstance.dismiss('cancel');
        }
    }
})()