/**
 * Created by admin on 2017/4/26.
 */

(function(){
    'use strict';

    angular
        .module('center')
        .controller('DeviceStockCtrl',DeviceStockCtrl);

    DeviceStockCtrl.$inject = [ '$scope', '$rootScope','deviceStockService','dataService','$modal','$location'];

    function DeviceStockCtrl($scope,$rootScope,deviceStockService,dataService,$modal,$location){
        var vm=this;

        // 分页
        vm.currentPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        vm.totalPage=1;
        $scope.form={};

        search();

        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            search();
        };
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }

            search();
        };

        vm.jump = function(){
            if(param.startPage <= $scope.jumppage &&  vm.totalPage >=$scope.jumppage){
                console.log($scope.jumppage,"跳转成功");
                param.startPage=$scope.jumppage;
                vm.currentPage=$scope.jumppage;
            }
            search();
        };
        //创建设备订单
        vm.devicelist=function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/devicelist.html',
                controller: 'DevicelistCtrl',
                controllerAs: 'devicelistCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }

        //todo
        vm.search = function(){
            param.startPage =1;
            if($scope.form.startTime)param.startTime = $scope.form.startTime;
            else delete param.startTime;
            if($scope.form.endTime) param.endTime = $scope.form.endTime;
            else delete param.endTime;
            if($scope.form.keyword)param.keyword = $scope.form.keyword;
            else delete param.keyword;
            search();
        };

        vm.process=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/refund_process.html',
                controller: 'RefundProcessCtrl',
                controllerAs: 'refundProcessCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    refundProcessData : function(){
                        return {
                            tradeId: vm.allRecord[index].tradeId,
                        };
                    }
                }
            });
        };
         //详情
        vm.detail=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/deviceStock_detail.html',
                controller: 'DeviceStockDetailCtrl',
                controllerAs: 'deviceStockDetailCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    statusData : function(){
                        var data = {
                            tradeId: vm.allRecord[index].tradeId,
                            tradeNo: vm.allRecord[index].partner_trade_no,
                            logisticNo: vm.allRecord[index].logistic_no
                        };
                        return data;
                    }
                }


            });
        };

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


        function search(){
            deviceStockService.search(param)
                .then(function(res){
                    if(res) {
                        if (res.data.errcode==1) {
                            vm.allRecord = res.data.object.list;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;

                            for(var i=0;i<vm.allRecord.length;i++){
                                if(vm.allRecord[i].status == 'N'){
                                    vm.allRecord[i].statusName = '已支付';
                                }else if(vm.allRecord[i].status == 'P'){
                                    vm.allRecord[i].statusName = '待支付';
                                }else if(vm.allRecord[i].status == 'B'){
                                    vm.allRecord[i].statusName = '备货中';
                                }else if(vm.allRecord[i].status == 'F'){
                                    vm.allRecord[i].statusName = '已发货';
                                }
                            }
                        }
                    }
                })
        };

        //创建设备订单
        vm.deviceList=function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/devicelist.html',
                controller: 'DevicelistCtrl',
                controllerAs: 'devicelistCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }
    }
})()