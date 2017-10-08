(function(){
	'use strict';

    angular
        .module('center')
        .controller('IncomeCtrl', IncomeCtrl);

    IncomeCtrl.$inject = [ '$scope', '$rootScope','IncomeService','dataService','$modal','utilService','$location','AlertService'];

    function IncomeCtrl($scope,$rootScope,IncomeService,dataService,$modal,utilService,$location,AlertService){
        var vm=this;

        // 分页
        vm.currentPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        // param.startTime = null;
        // param.endTime = null;
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            IncomeService.depositDetails(param)
            .then(function(res){
                vm.allRecord = res.data.depositWithTaxes;
                vm.currentPage = param.startPage;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            IncomeService.depositDetails(param)
            .then(function(res){
                vm.allRecord = res.data.depositWithTaxes;
                vm.currentPage = param.startPage;
            });
        }

        vm.titles=dataService.incomeTitles;
        vm.generalNum = [];
        // console.log(param);
        IncomeService.depositDetails(param)
        .then(function(res){
        	// console.log(res);
        	vm.allRecord = res.data.depositWithTaxes;
        	vm.totalPage = res.data.pages;
        	vm.currentPage = param.startPage;
        });

        IncomeService.getBenefitSta()
        .then(function(res){
            // console.log(res);
            vm.generalNum[0] = {
                name: "今日收益",
                number: res.data.todayBenefit
            };
            vm.generalNum[1] = {
                name: "本月收益",
                number: res.data.monthBenefit
            };
            vm.generalNum[2] = {
                name: "累计收益",
                number: res.data.totalBenefit
            };
            // console.log(vm.generalNum);
        });

        vm.search = function(){
            // console.log($scope.form);
            param.startPage = 1;
            param.startTime = $scope.form.startTime;
            param.endTime = $scope.form.endTime;
            param.keyword = $scope.form.keyword;
            IncomeService.depositDetails(param)
            .then(function(res){
                console.log(res);
                vm.allRecord = res.data.depositWithTaxes;
            })
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
        //end dataTimePicker

        // 申请结算
    }
})()