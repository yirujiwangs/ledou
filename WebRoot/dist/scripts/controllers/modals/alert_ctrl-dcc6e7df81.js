(function(){
	'use strict';

    angular
        .module('center')
        .controller('AlertCtrl', AlertCtrl);

    AlertCtrl.$inject = [ '$scope', '$rootScope','msgObj','$modalInstance','$timeout'];

    function AlertCtrl($scope,$rootScope,msgObj,$modalInstance,$timeout){
    	$scope.success=msgObj.success;
    	$scope.msg=msgObj.msg;
        var timer = $timeout(
            function() {
                $modalInstance.dismiss('cancel');
                $timeout.cancel( timer );
            },
            1000
        );
    }
})()