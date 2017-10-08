(function(){
    'use strict';

    angular
        .module('center')
        .controller('RedPacketDetailCtrl', RedPacketDetailCtrl);

    RedPacketDetailCtrl.$inject = [ '$scope', '$rootScope', '$location','redPacketId', '$route','AttractFansRedPacketService','$modalInstance','$modal','AlertService','utilService'];

    function RedPacketDetailCtrl($scope,$rootScope,$location,redPacketId,$route,AttractFansRedPacketService,$modalInstance,$modal,AlertService,utilService){
        if(sessionStorage.userName) $scope.userName=sessionStorage.userName;
        else if(localStorage.userName)$scope.userNAme=localStorage.userName;
        var vm = this;
        $scope.isShowGroup=false;
        $scope.isShowArea=false;
        var param={rid:redPacketId};
        AttractFansRedPacketService.redPacketDetail(param)
            .then(function(res){
                if(res) {
                    vm.redPacketInfo=res.data;
                    if(vm.redPacketInfo.grantType=='D'){
                        $scope.isShowGroup=true;
                    }else if(vm.redPacketInfo.grantType=='W'){
                        $scope.isShowArea=true;
                    }
                }
            });
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }

    }
})()