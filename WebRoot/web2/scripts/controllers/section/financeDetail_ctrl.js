(function(){
	'use strict';

    angular
        .module('center')
        .controller('FinanceDetailCtrl',FinanceDetailCtrl);

   FinanceDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dirDao','dataService','financeService','AlertService','$modal','utilService'];

    function FinanceDetailCtrl($scope,$rootScope,$location,dirDao,dataService,financeService,AlertService,$modal,utilService){
        var vm=this;
        vm.titles = dataService.financeDetailTitles;
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
            financeService.getFinanceDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.stores = res.data.storeDeposits;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            financeService.getFinanceDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.stores = res.data.storeDeposits;
            });
        }

        vm.getFinanceStoreDetail = function(index){
            // console.log(vm.stores[index].phonenum);
            $location.path('/financeStoreDetail').search({
                phone: vm.stores[index].phonenum
            })
        }

        vm.fortune = function(){
            $location.path('/financeIncomeDetail');
        }

        // 浅拷贝对象
        var param = angular.extend({},param,$location.search());
        // console.log(param);
        // 列表数据
        financeService.getFinanceDetail(param)
        .then(function(res){
            // console.log(res);
            vm.totalPage = res.data.pages;
            vm.stores = res.data.storeDeposits;
        });
        // 获取数据指标
        financeService.getFinanceDetailSta($location.search())
        .then(function(res){
            // console.log(res);
            vm.items = res.data.statistics;
        });

        // 获取经销商信息
        dirDao.getCorporationInfo($location.search())
        .then(function(res){
            // console.log(res);
            vm.financeDetailAccount = res.data.proxyInfo;
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