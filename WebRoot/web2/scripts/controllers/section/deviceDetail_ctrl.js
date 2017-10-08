(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceDetailCtrl',DeviceDetailCtrl);

   DeviceDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dirDao','dataService','deviceService','AlertService','$modal','utilService'];

    function DeviceDetailCtrl($scope,$rootScope,$location,dirDao,dataService,deviceService,AlertService,$modal,utilService){
        var vm=this;
        vm.titles = dataService.deviceDetailTitles;
        vm.deviceDetailAccount = dataService.deviceDetailAccount;

        // 分页
        vm.currentPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            deviceService.getDeviceInfoDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.shops = res.data.allUser;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            deviceService.getDeviceInfoDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.shops = res.data.allUser;
            });
        }
        // 列表数据
        var param = angular.extend({},param,$location.search());
        deviceService.getDeviceInfoDetail(param)
        .then(function(res){
            // console.log(res);
            vm.items = res.data.amount;
            vm.shops = res.data.allUser;
            vm.totalPage = res.data.pages;
        })

        dirDao.getCorporationInfo($location.search())
        .then(function(res){
            // console.log(res);
            vm.deviceDetailAccount = res.data.proxyInfo;
        })

        // vm.distrbute = function(){
        //     var editSqlModalInstance = $modal.open({
        //         templateUrl: 'views/modals/distrbuteDevice.html',
        //         controller: 'DistrbuteDeviceCtrl',
        //         controllerAs: 'distrbuteDeviceCtrl',
        //         backdrop: 'static',
        //         windowClass: 'overflow-y-auto chart-modal',
        //         resolve: {
        //             shopData: function(){
        //                 return {
        //                     account: vm.deviceDetailAccount.account,
        //                     storeName: vm.deviceDetailAccount.storeName,
        //                 }
        //             }   
        //         }
        //     });
        // }

        vm.unbindDevice = function(){
            $location.path('/deviceUnbind');
        }

        vm.getDeviceShopDetail = function(index){
            $location.path('/deviceShopDetail').search({
                phoneNum: vm.shops[index].account,
                corporationid: $location.search().corporationid
            });
        }
    }
})()