/**
 * Created by Administrator on 2017/8/18 0018.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('AuthorizationRefreshCtrl', AuthorizationRefreshCtrl);

    AuthorizationRefreshCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance', '$location','$timeout'];

    function AuthorizationRefreshCtrl($scope,$rootScope,$route,$modalInstance, $location,$timeout){
        var vm = this;

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
        vm.refresh = function(){
            $timeout(function(){
                $route.reload();
            },1000)
            $modalInstance.close();
        }
    }
})()