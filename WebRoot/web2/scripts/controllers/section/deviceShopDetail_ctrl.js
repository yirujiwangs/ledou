(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceShopDetailCtrl',DeviceShopDetailCtrl);

   DeviceShopDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dirDao','dataService','deviceService','AlertService','$modal','utilService'];

    function DeviceShopDetailCtrl($scope,$rootScope,$location,dirDao,dataService,deviceService,AlertService,$modal,utilService){
        var vm=this;
        vm.titles = [
            {name: "设备序列号"},
            {name: "微信ID"},
            {name: "设备状态"},
            {name: "添加时间"},
            {name: "备注"}
        ];

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
                vm.devices = res.data.personalDevice;
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
                vm.devices = res.data.personalDevice;
            });
        }
        // 列表数据
        var param = angular.extend({},param,$location.search());
        // console.log(param);
        deviceService.getDeviceDeposit(param)
        .then(function(res){
            // console.log(res);
            vm.totalPage = res.data.pages;
            vm.devices = res.data.personalDevice;
        })

        deviceService.getDeviceStoreDS($location.search())
        .then(function(res){
            // console.log(res);
            vm.item = res.data.deviceStatistics;
        })

        dirDao.getCorporationInfo($location.search())
        .then(function(res){
            console.log(res);
            vm.deviceShopDetailAccount = res.data.proxyInfo;
        })

        //dataTimepicker相关设置
        moment.locale('zh-cn');
        $scope.form = {};
        $scope.configFunction = function configFunction() {
            return {startView: 'month',minView: 'day'};
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.form.startTime=moment(newDate).format("YYYY-MM-DD")          
            $('#dropdown1').dropdown('toggle');
        };
        $scope.inputOnEndTimeSet=function(newDate) {
            $scope.form.endTime=moment(newDate).format("YYYY-MM-DD")          
            $('#dropdown2').dropdown('toggle');
        };
        //end dataTimePicker
    }
})()