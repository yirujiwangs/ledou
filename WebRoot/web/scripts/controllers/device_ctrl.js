(function () {
    'use strict';

    angular
        .module('center')
        .controller('DeviceCtrl', DeviceCtrl);

    DeviceCtrl.$inject = ['$scope', '$rootScope','$route', 'DeviceService', 'dataService', '$modal', 'utilService', '$location', 'AlertService'];

    function DeviceCtrl($scope, $rootScope,$route, DeviceService, dataService, $modal, utilService, $location, AlertService) {
        var vm = this;

        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;

        vm.titles = dataService.devicetitles

        var devciceAccount = [];
        $scope.searchCon = '';

        DeviceService.listDevices(page)
            .then(function (res) {
                var isStore = angular.fromJson(res.data)
                if (isStore) {
                    var data = angular.fromJson(isStore.storeInfo);
                    if (data) {
                        console.log(data);
                        vm.totalPage = isStore.pages;
                        vm.allUser = data;
                    } else {
                        vm.noStoreInfo = "暂无商户设备，请努力推广哦~";
                    }
                }
                else {
                    vm.noStoreInfo = "暂无商户设备，请努力推广哦~";
                }

            });

        DeviceService.deviceIndex()
            .then(function (res) {
                var data = angular.fromJson(res.object);

                vm.generalNum = data;


                console.log(res,"设备指标")
            });


        vm.pre = function () {
            if (page.startPage > 1) {
                page.startPage--;
            }
            else {
                page.startPage = 1;
            }
            DeviceService.listDevices(page)
                .then(function (res) {
                    var data = angular.fromJson(res.data.object.storeInfo);
                    // console.log(data);
                    vm.currentPage = page.startPage;
                    // console.log(vm.currentPage);
                    vm.allUser = data;
                });
        }
        vm.next = function () {
            if (page.startPage < vm.totalPage) {
                page.startPage++;
            }
            else {
                page.startPage = vm.totalPage;
            }
            DeviceService.listDevices(page)
                .then(function (res) {
                    var data = angular.fromJson(res.data.object.storeInfo);
                    vm.currentPage = page.startPage;
                    vm.allUser = data;
                });
        }



        vm.addDevice = function () {
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
        vm.getDetail = function (index) {
            $location.path('/deviceDetail').search({
                storeAccount: vm.allUser[index].storeAccount
            });
        }

        vm.all = function () {
            $location.path('/allDevice');
        }

        vm.search = function () {
            page.startPage = 1;
            page.keyword = $scope.searchCon;
            DeviceService.deviceAccountSearch(page)
                .then(function (res) {
                    var data = angular.fromJson(res.data.object.storeInfo);
                    vm.totalPage = res.data.object.pages;
                    vm.allUser = data;
                    vm.currentPage = page.startPage;
                })
        }
        vm.revcomment = function (index) {
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