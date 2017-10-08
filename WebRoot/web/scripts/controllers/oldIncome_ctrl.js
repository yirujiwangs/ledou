(function () {
    'use strict';

    angular
        .module('center')
        .controller('OldIncomeCtrl', OldIncomeCtrl);

    OldIncomeCtrl.$inject = ['$scope', '$rootScope', 'OldIncomeService','IncomeService', 'dataService', '$modal', 'utilService', '$location', 'AlertService'];

    function OldIncomeCtrl($scope, $rootScope, OldIncomeService,IncomeService, dataService, $modal, utilService, $location, AlertService) {
        var vm = this;
        $scope.tabType = true;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;

        vm.items={};
        vm.items.combinationDeviceNum=0;
        vm.items.indirectDeviceNum=0;
        vm.items.combinationDeviceBenefit=0;
        vm.items.indirectDeviceBenefit=0;
        vm.items.activatedDeviceNum=0;
        vm.items.activatedDeviceBenefit=0;
        vm.items.combinationAdPrice=0;
        vm.items.combinationAdBenefit=0;
        vm.items.storeAdDepositPrice=0;
        vm.items.storeAdDepositBenefit=0;
        vm.items.selfAdPrice=0;
        vm.items.rebateAdBenefit=0;

        var param = {};
        param.startPage = 1;
        param.pageSize = 8;

        $scope.tabType = "activerecord";//刷新页面默认的选项卡显示页

        vm.activerecordTitles = dataService.activerecordTitles;//第一个选项卡title
        vm.adfirstTitles = dataService.incomeAdfirstTitles;//第一个选项卡title
        vm.adscendTitles = dataService.incomeAdscend;//第2个选项卡title
        vm.adthirdTitles = dataService.incomeAdthird;//第3个选项卡title
        vm.adTitles = dataService.incomeAdTitles;//第4个选项卡title
        vm.titles = dataService.incomeTitles;//第5个选项卡title
        vm.dealerTitles = dataService.incomeDealerTitles;//原来注销掉了

        $(function () { $("[data-toggle='tooltip']").tooltip(); });


        //关键指标的获取
        OldIncomeService.getBenefitSta()
            .then(function (res) {
                var data = res.data.object.financeStatistics;
                vm.items = data;
            });

        param.type=1;
        loadData($scope.tabType, param);

        vm.pre = function () {
            if (param.startPage > 1) {
                param.startPage--;
            }
            else {
                param.startPage = 1;
            }
            vm.currentPage = param.startPage;
            loadData($scope.tabType, param);
        };


        vm.next = function () {
            if (param.startPage < vm.totalPage) {
                param.startPage++;
            }
            else {
                param.startPage = vm.totalPage;
            }
            vm.currentPage = param.startPage;
            loadData($scope.tabType, param);
        }


        //选项卡点击
        vm.tabToggle = function (type) {

            if (type === 'combinationDevice') {
                param.type=1;
                vm.currentPage = 1;
                param.startPage=1;
                delete param.benefitType;
                delete param.classify;
                delete param.page;
            }
            else if (type === 'indirectDevice') {
                param.type=2;
                vm.currentPage = 1;
                param.startPage=1;
                delete param.benefitType;
                delete param.classify;
                delete param.page;
            }
            else if (type === 'deviceadred') {
                param.type=1;
                vm.currentPage = 1;
                param.startPage=1;
                param.benefitType=1;
                delete param.classify;
                delete param.page;
            }
            else if (type === 'directadred') {
                param.type=2;
                vm.currentPage = 1;
                param.startPage=1;
                param.benefitType=1;
                delete param.classify;
                delete param.page;
            }

            else if (type === 'storeAdDeposit') {
                vm.currentPage = 1;
                param.startPage=1;
                delete param.benefitType;
                delete param.type;
            }
            else if (type === 'dealer') {
                vm.currentPage = 1;
                param.startPage=1;
                delete param.benefitType;
                delete param.type;
                delete param.classify;
                delete param.page;
            }
            loadData(type, param);
        }


        //第3个选项卡中的详情
        vm.getAdDetail = function (index) {
            var getAdDetailParam = {};
            var date = new Date(vm.deviceadredlist[index].date);
            getAdDetailParam.date = date.getTime();
            $location.path('/incomeAdDetail').search({
                date: getAdDetailParam.date
            });
        }
        //设备激活记录
        vm.recordDetail = function (index) {
            var recordDetailParam = {};
            recordDetailParam.num= vm.activerecordDevicelist[index].num;
            recordDetailParam.benefit= vm.activerecordDevicelist[index].benefit;
            recordDetailParam.day=vm.activerecordDevicelist[index].day;
            $location.path('/incomerecordDetail').search({
                num: recordDetailParam.num,
                day:recordDetailParam.day,
                benefit:recordDetailParam.benefit
            });
        }
        //商户广告金充值详情
        vm.getStoreAdDeposit=function(index){
            $location.path('/incomeStoreAdDepositDetail').search({
                date:vm.storeAdDepositlist[index].month
            });
        }

        function loadData(tabType, param) {
            if(tabType != $scope.tabType ){
                //触发切换
                vm.currentPage = 1;
                $scope.tabType =tabType;
            }

            if (tabType == 'combinationDevice') {
                OldIncomeService.combinationDevice(param)   //第1个选项卡
                    .then(function (res) {
                        if(res) {
                            if (res.data.errcode == 1) {
                                var data = res.data.object;
                                vm.combinationDevicelist = data.list;
                                vm.totalPage = data.pages;
                            }
                        }
                    });
            }else if(tabType == 'indirectDevice'){
                //第2个选项卡
                OldIncomeService.indirectDevice(param)
                    .then(function (res) {
                        if(res) {
                            if (res.data.errcode == 1) {
                                var data = res.data.object;
                                vm.indirectDevicelist = data.list;
                                vm.totalPage = data.pages;
                            }
                        }
                    });
            }else if(tabType=='deviceadred'){
                OldIncomeService.deviceadred(param)   //第3个选项卡
                    .then(function (res) {
                        if(res) {
                            if (res.data.errcode == 1) {
                                var obj=angular.fromJson(res.data.object);
                                vm.deviceadredlist = obj.records;
                                vm.totalPage = obj.pages;
                                if(vm.totalPage<1)vm.totalPage=1;
                            }
                        }
                    })
            }else if(tabType=='directadred'){
                OldIncomeService.directadred(param)   //第4个选项卡
                    .then(function (res) {
                        if(res) {
                            if (res.data.errcode == 1) {
                                var data = angular.fromJson(res.data.object);
                                vm.directadredlist = data.records;
                                vm.totalPage = data.pages;
                            }
                        }
                    })
            }else if (tabType == 'activerecord') {
                OldIncomeService.activerecord(param)   //设备激活记录
                    .then(function (res) {
                        if (res) {
                            if (res.data.errcode == 1) {
                                var data = res.data.object;
                                vm.activerecordDevicelist = data.list;
                                vm.totalPage = data.pages;
                            }
                        }
                    })
            }
            else {
                param.classify='account';
                param.page=param.startPage;
                IncomeService.storeAdDepositMonthRecord(param)
                    .then(function (res) {
                        if(res) {
                            if (res.data.error == 0) {
                                var data = res.data.object;
                                vm.storeAdDepositlist= data.list;
                                vm.totalPage = data.pages;
                                if(vm.totalPage<1)vm.totalPage=1;
                            }
                        }
                    });
            }
        }
    }
})()