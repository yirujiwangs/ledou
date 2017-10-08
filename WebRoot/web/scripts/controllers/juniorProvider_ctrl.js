(function(){
    'use strict';
    angular
        .module('center')
        .controller('JuniorProviderCtrl', JuniorProviderCtrl);
    JuniorProviderCtrl.$inject = [ '$scope', '$rootScope','$route','JuniorProviderService','dataService','DeviceService','$modal','$location'];
    function JuniorProviderCtrl($scope,$rootScope,$route,JuniorProviderService,dataService,deviceService,$modal,$location) {
        var vm = this;
        //换页
        vm.allInfo=null;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        $scope.isShow=false;
        $(function () {
            $("[data-toggle='tooltip']").tooltip();
        });

        vm.titles = dataService.juniorProviderTitles;

          JuniorProviderService.statistics()
            .then(function(res){
                if(res) {
                    if(res.data.errcode==1){
                        vm.generalNum = res.data.object;
                    }
                }
            });
        function load() {
                $scope.isShow=false;
                JuniorProviderService.juniorProvidertList(param)
                    .then(function (res) {
                        if (res) {
                            if (res.data.errcode == 1) {
                                $scope.isShow=true;
                                vm.allInfo = res.data.object.data;
                                vm.totalPage = res.data.object.pages;
                                vm.currentPage = param.startPage;
                            }
                        }
                    });
        }
        load();
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            load();
        }
        vm.next = function(){

            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            load();
        }
        // 详情跳转
        vm.detail = function (index) {
            var detailParam = {};
            detailParam.totalDeviceBuy=vm.allInfo[index].totalDeviceBuy;
            detailParam.totalDeviceNum=vm.allInfo[index].allProxyDevices;
            detailParam.totalProviderNum=vm.allInfo[index].allDevices;
            detailParam.phoneNum=vm.allInfo[index].phoneNum;
            $location.path('/juniorProviderDetail').search({
                totalDeviceBuy:detailParam.totalDeviceBuy,
                totalDeviceNum:detailParam.totalDeviceNum,
                totalProviderNum:detailParam.totalProviderNum,
                phoneNum:detailParam.phoneNum
            });
        }
    }
})()