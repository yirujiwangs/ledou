(function(){
    'use strict';

    angular
        .module('center')
        .controller('ListAdShowStrategyCtrl',ListAdShowStrategyCtrl);

    ListAdShowStrategyCtrl.$inject = ['base64Service', '$scope', '$rootScope', '$route','$modalInstance','utilService','$modal','AlertService','AdDao','adData'];

    function ListAdShowStrategyCtrl(base64Service,$scope,$rootScope,$route,$modalInstance,utilService,$modal,AlertService,AdDao,adData){
        if(sessionStorage.userName) $scope.userName=sessionStorage.userName;
        else if(localStorage.userName)$scope.userNAme=localStorage.userName;
        var vm = this;
        //默认都不显示
        $scope.isShowGroup=false;
        $scope.isShowArea=false;
        AdDao.advStrategyDetail({advUUID:adData.advUUID,type:adData.type})
            .then(function(res){
                if(res.data.errcode==1){
                    vm.allInfo=angular.fromJson(res.data.object);
                    console.log(vm.allInfo);
                    if(vm.allInfo.grantType=='1'){
                        $scope.isShowGroup=true;
                    }else if(vm.allInfo.grantType=='0'){
                        $scope.isShowArea=true;
                    }
                }
            })
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }

    }
})()
