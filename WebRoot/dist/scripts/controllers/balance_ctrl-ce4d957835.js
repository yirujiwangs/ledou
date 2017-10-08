(function(){
	'use strict';

    angular
        .module('center')
        .controller('BalanceCtrl', BalanceCtrl);

    BalanceCtrl.$inject = [ '$scope', '$rootScope','$route','baseHttp','personService','BalanceService','dataService','$modal','utilService','$location','AlertService'];

    function BalanceCtrl($scope,$rootScope,$route,baseHttp,personService,BalanceService,dataService,$modal,utilService,$location,AlertService){
        var vm=this;
        vm.status=dataService.balanceStatus;
        $scope.form = {};
        $scope.form.keyword = vm.status[0].name;
        // 分页
        vm.currentPage = 1;
        var listParam = {};
        listParam.startPage = 1;
        listParam.pageSize = 5;
        vm.pre = function(){
            if(listParam.startPage > 1){
                listParam.startPage--;
            }
            else{
                listParam.startPage = 1;
            }
            BalanceService.getFinanceDetail(listParam)
            .then(function(res){
                vm.allData=res.data.financeRecords;
                vm.currentPage = listParam.startPage;
            });
        }
        vm.next = function(){
            if(listParam.startPage < vm.totalPage){
                listParam.startPage++;
            }
            else{
                listParam.startPage = vm.totalPage;
            }
            BalanceService.getFinanceDetail(listParam)
            .then(function(res){
                vm.allData=res.data.financeRecords;
                vm.currentPage = listParam.startPage;
            });
        }

        vm.titles=dataService.BalanceTitles;
        // 获取财务总览
        var time = {};
        vm.generalNum = [];
        BalanceService.finance(time)
        .then(function(res){
            // console.log(res);
            vm.generalNum[0] = {
                name: "当前余额",
                number: res.data.proxyFinance.avaiable
            };
            vm.generalNum[1] = {
                name: "累计收益",
                number: res.data.proxyFinance.sum_income
            };
            vm.generalNum[2] = {
                name: "已结算金额",
                number: res.data.proxyFinance.balanced
            };
            vm.generalNum[3] = {
                name: "待结算金额",
                number: res.data.proxyFinance.balancing
            };
        });

        listParam.startTime = null;
        listParam.endTime = null;
        // 1为抽成记录，2为提现记录
        listParam.type = 2;
        // 1代表成功，2代表正在处理，null代表所有
        listParam.state = null;
        // console.log(param)
        BalanceService.getFinanceDetail(listParam)
        .then(function(res){
            // console.log(res);
            vm.allData = res.data.financeRecords;
            vm.totalPage = res.data.pages;
            vm.currentPage = listParam.startPage;
            // console.log(vm.allData);
        })

        // 申请结算默认数据
        var withdrawData;
        personService.accountInfo()
        .then(function(res){
            // console.log(res);
            withdrawData = res;
        });
        vm.withdraw = function(){
    		var editSqlModalInstance = $modal.open({
    	        templateUrl: 'views/modals/balance_withdraw.html',
    	        controller: 'BalanceWithdrawCtrl',
    	        controllerAs: 'balanceWithdrawCtrl',
    	        backdrop: 'static',
    	        windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    personData : function(){
                        return withdrawData;
                    }
                }
    	    });
        };
        vm.search = function(){
            // console.log($scope.form);
            listParam.startPage = 1;
            listParam.startTime = $scope.form.startTime;
            listParam.endTime = $scope.form.endTime;
            if($scope.form.keyword == "待结算"){
                listParam.state = 0;
            }
            else if($scope.form.keyword == "已结算"){
                listParam.state = 1;
            }
            else if($scope.form.keyword == "已拒绝"){
                listParam.state = 2;
            }
            else{
                $route.reload();
            }
            BalanceService.getFinanceDetail(listParam)
            .then(function(res){
                // console.log(res);
                vm.allData = res.data.financeRecords;
            })
        };
        // 详情
        vm.detail=function(index){
            // console.log(index);
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/balance_detail.html',
                controller: 'BalanceDetailCtrl',
                controllerAs: 'balanceDetailCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    balanceData : function(){
                        // console.log(vm.allData[index].id);
                        var data = {
                            id: vm.allData[index].id
                        }
                        return data;
                    }
                }
            });
        }
        //dataTimepicker相关设置
        moment.locale('zh-cn');
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