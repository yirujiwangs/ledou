(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceCtrl', DeviceCtrl);

    DeviceCtrl.$inject = [ '$scope', '$rootScope','DeviceService','dataService','$modal','utilService','$location','AlertService'];

    function DeviceCtrl($scope,$rootScope,DeviceService,dataService,$modal,utilService,$location,AlertService){
        var vm=this;

        // 分页
        vm.currentPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;
        vm.pre = function(){
            if(page.startPage > 1){
                page.startPage--;
            }
            else{
                page.startPage = 1;
            }
            DeviceService.listDevices(page)
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
            DeviceService.listDevices(page)
            .then(function(res){
                vm.generalNum=res.data.amount;
                vm.allUser=res.data.allUser;
                vm.currentPage = page.startPage;
            });
        }

        var devciceAccount=[];
        $scope.searchCon='';
        DeviceService.listDevices(page)
        .then(function(res){
            // console.log(res);
        	vm.generalNum=res.data.amount;
            vm.allUser=res.data.allUser;
            vm.totalPage = res.data.pages;
            vm.currentPage = page.startPage;
        	devciceAccount=utilService.copyArr(vm.allUser,devciceAccount,'storeName');
        })
        vm.titles=dataService.devicetitles;

        vm.addDevice=function(){
        	var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/device_add.html',
                controller: 'DeviceAddCtrl',
                controllerAs: 'deviceAddCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     shopData: function () {
                         return devciceAccount;
                      }
                  }
            });
        }
        vm.getDetail=function(index){
        	dataService.deviceDetail=vm.allUser[index];
        	$location.path('/deviceDetail');
        }
        vm.import=function(){
        	DeviceService.import()
        	.then(function(res){
        		window.open(res.data.url)
        	})
        }
        vm.undistributed=function(){
            $location.path('/deviceBind');
        }
        vm.search=function(){
            page.startPage = 1;
            page.value = $scope.searchCon;
            DeviceService.deviceAccountSearch(page)
            .then(function(res){
                    vm.allUser=res.data.allUser;
                    vm.currentPage = page.startPage;
                })
                .catch(function(err){
                    AlertService.alert({success:false,msg:err})
                    
                })
        }
        vm.revcomment=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/device_rev.html',
                controller: 'DeviceRevCtrl',
                controllerAs: 'deviceRevCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    deviceData: function () {
                        return vm.allUser[index];
                    }
                }
            });
        }
    }
})()