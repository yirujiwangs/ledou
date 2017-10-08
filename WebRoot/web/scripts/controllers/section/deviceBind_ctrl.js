(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceBindCtrl', DeviceBindCtrl);

    DeviceBindCtrl.$inject = [ '$scope', '$rootScope','AlertService','DeviceService','dataService','$modal'];

    function DeviceBindCtrl($scope,$rootScope,AlertService,DeviceService,dataService,$modal){
        var vm = this;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 15;
        vm.pre = function(){
            if(page.startPage > 1){
                page.startPage--;
            }
            else{
                page.startPage = 1;
            }
            DeviceService.undistributed(page)
            .then(function(res){
                vm.generalNum=res.data.amount;
                vm.allUser=res.data.allUser;
                vm.currentPage = page.startPage;
            });
        }
        vm.next = function(){
            if(page.startPage < vm.totalPage){
                page.startPage++;
            }
            else{
                page.startPage = vm.totalPage;
            }
            DeviceService.undistributed(page)
            .then(function(res){
                vm.generalNum=res.data.amount;
                vm.allUser=res.data.allUser;
                vm.currentPage = page.startPage;
            });
        }
        DeviceService.undistributed(page)
        .then(function(res){
            // console.log(res);
            vm.items = res.data.generalResults;
            vm.totalPage = res.data.pages;
            vm.currentPage = page.startPage;
        })

        vm.conversion=dataService.conversion;
        vm.titles=dataService.DeviceBindTitles;
        
        vm.judge=function(i){
            if(vm.items[i].status=="激活")
                return true
            else
                return false
        }
        vm.active=function(index){
            dataService.deviceActiveDates=vm.items[index]
            var deviceActiveModalInstance = $modal.open({
                templateUrl: 'views/modals/deviceBind_active.html',
                controller: 'DeviceBindActiveCtrl',
                controllerAs: 'deviceBindActiveCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     deviceDetail: function () {
                         return {id:vm.items[index].ibeaconId}
                      }
                  }
            });
        }

        vm.addToWeixin=function(){
            var deviceAddModalInstance = $modal.open({
                templateUrl: 'views/modals/deviceBind_add.html',
                controller: 'DeviceBindAddCtrl',
                controllerAs: 'deviceBindAddCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }
    }
})()