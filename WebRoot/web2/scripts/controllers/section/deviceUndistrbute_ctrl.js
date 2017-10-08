(function(){
	'use strict';

    angular
    .module('center')
    .controller('UndistrbuteDeviceCtrl', UndistrbuteDeviceCtrl);

    UndistrbuteDeviceCtrl.$inject = [ '$scope', '$rootScope','AlertService','deviceService','dataService','$modal'];

    function UndistrbuteDeviceCtrl($scope,$rootScope,AlertService,deviceService,dataService,$modal){
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
            deviceService.undistributed(page)
            .then(function(res){
               vm.allRecord = res.data.unbindDevInfo;
               vm.totalPage = res.data.pages;
           });
        }
        vm.next = function(){
            if(page.startPage < vm.totalPage){
                page.startPage++;
            }
            else{
                page.startPage = vm.totalPage;
            }
            deviceService.undistributed(page)
            .then(function(res){
               vm.allRecord = res.data.unbindDevInfo;
               vm.totalPage = res.data.pages;
           });
        }
        deviceService.undistributed(page)
        .then(function(res){
            vm.allRecord = res.data.unbindDevInfo;
            vm.totalPage = res.data.pages;
            vm.currentPage = page.startPage;
        })

        vm.conversion=dataService.conversion;
        vm.titles=dataService.deviceUnBindTitles;
        vm.dealImport = function(){
            AlertService.alert({success:true,msg:"此模块暂时未开通"})
        }
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