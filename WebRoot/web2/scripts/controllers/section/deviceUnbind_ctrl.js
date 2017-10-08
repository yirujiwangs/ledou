(function(){
	'use strict';

    angular
    .module('center')
    .controller('DeviceUnbindCtrl', DeviceUnbindCtrl);

    DeviceUnbindCtrl.$inject = [ '$scope', '$rootScope','$location','AlertService','deviceService','dataService','$modal'];

    function DeviceUnbindCtrl($scope,$rootScope,$location,AlertService,deviceService,dataService,$modal){
        var vm = this;
        // 分页
        vm.currentPage = 1;
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
            deviceService.getUnbind(page)
            .then(function(res){
               vm.allRecord = res.data.unbindDevInfo;
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
            deviceService.getUnbind(page)
            .then(function(res){
               vm.allRecord = res.data.unbindDevInfo;
               vm.currentPage = page.startPage;
           });
        }

        var page = angular.extend({},page,$location.search());
        deviceService.getUnbind(page)
        .then(function(res){
            vm.allRecord = res.data.generalResults;
            vm.totalPage = res.data.pages;
        })

        vm.conversion = dataService.conversion;
        vm.titles = dataService.deviceUnBindTitles;
        // console.log(dataService.deviceUnBindTitles);
        vm.updateRemark = function (index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/device_update_remark.html',
                controller: 'DeviceUpdateRemarkCtrl',
                controllerAs: 'deviceUpdateRemarkCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    DeviceUpdateRemarkData: function () {
                        var data ={};
                        data.serialNum =vm.allRecord[index].serialNum;
                        data.remark = vm.allRecord[index].remark;
                        data.deviceId = vm.allRecord[index].deviceId; 
                        return data;
                    }
                }
            })
        }
    }
})()