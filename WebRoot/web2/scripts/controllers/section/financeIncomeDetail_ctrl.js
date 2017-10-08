(function(){
	'use strict';

    angular
        .module('center')
        .controller('FinanceIncomeDetailCtrl',FinanceIncomeDetailCtrl);

    FinanceIncomeDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dirDao','dataService','financeService','AlertService','$modal','utilService'];

    function FinanceIncomeDetailCtrl($scope,$rootScope,$location,dirDao,dataService,financeService,AlertService,$modal,utilService){
        var vm=this;
        vm.titles= dataService.financeIncomeDetailTitles;
        $scope.form={};

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
            financeService.getFinanceIncomeDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.users = res.data.depositWithTaxes;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            financeService.getFinanceIncomeDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.users = res.data.depositWithTaxes;
            });
        }

        var param = angular.extend({},param,$location.search());
        financeService.getFinanceIncomeDetail(param)
        .then(function(res){
            // console.log(res);
            vm.totalPage = res.data.pages;
            vm.allData = res.data.depositWithTaxes;
        })

        financeService.getFinanceIncomeDetailSta($location.search())
        .then(function(res){
            // console.log(res);
            vm.items = res.data;
        })

        // 获取经销商信息
        dirDao.getCorporationInfo($location.search())
        .then(function(res){
            // console.log(res);
            vm.financeIncomeDetailAccount = res.data.proxyInfo;
        })

     
        vm.search=function(){
                   
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