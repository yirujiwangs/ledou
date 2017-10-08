(function () {
    'use strict';

    angular
        .module('center')
        .controller('IncomeCtrl', IncomeCtrl);

    IncomeCtrl.$inject = ['$scope', '$rootScope','base64Service', 'IncomeService', 'dataService', '$modal', 'utilService', '$location', 'AlertService'];

    function IncomeCtrl($scope, $rootScope, base64Service,IncomeService, dataService, $modal, utilService, $location, AlertService) {
        var vm = this;
        if(sessionStorage.proxyType) {
            $scope.proxyType = base64Service.base64decode(sessionStorage.proxyType);
            $scope.policy=sessionStorage.policy;
        }
        else if(localStorage.proxyType){
            $scope.proxyType = base64Service.base64decode(localStorage.proxyType);
            $scope.policy=localStorage.policy;
        }
        //初始化
        vm.items={};
        vm.items.combinationDeviceNum=0;
        vm.items.combinationDeviceBenefit=0;
        vm.items.indirectDeviceNum=0;
        vm.items.indirectDeviceBenefit=0;
        vm.items.combinationAdPrice=0;
        vm.items.combinationAdBenefit=0;
        vm.items.activatedDeviceNum=0;
        vm.items.activatedDeviceBenefit=0;
        vm.items.storeMarketingPrice=0;
        vm.items.storeMarketingBenefit=0;
        vm.items.storeAdDepositPrice=0;
        vm.items.storeAdDepositBenefit=0;
        vm.items.totalFranchiseArea=0;
        vm.items.totalFranchiseBenefit=0;
        vm.items.selfAdPrice=0;
        vm.items.rebateAdBenefit=0;
        vm.items.distDeviceBuyNum=0;
        vm.items.distDeviceBuyBenefit=0;
        vm.items.dist_device_red=0;
        vm.items.benefit_dist_device_red=0;
        vm.items.dist_merchant_market=0;
        vm.items.benefit_dist_merchant_market=0;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;

        var param = {};
        param.startPage = 1;
        param.pageSize = 8;

        var paramObj={};
        paramObj.page=1;
        //默认第一个为设备激活记录
        paramObj.classify='device';

        $scope.tabType = "activerecord";//刷新页面默认的选项卡显示页
        vm.adthirdTitles = dataService.incomeAdthird;//组合采购设备记录title
        vm.adTitles = dataService.incomeAdTitles;//间接推荐采购记录title
        vm.activerecordTitles = dataService.activerecordTitles;//设备激活记录title
        vm.adfirstTitles = dataService.incomeAdfirstTitles;//自有设备品牌红包title
        vm.adscendTitles = dataService.incomeAdscend;//直接推荐设备品牌红包title
        vm.titles = dataService.incomeTitles;//商户广告金充值title
        vm.dealerTitles = dataService.incomeDealerTitles;//原来注销掉了
        vm.storeSharingTitles=dataService.incomeStoreSharingTitles;//商户服务费分成奖励记录title
        vm.initialFeeTitles=dataService.incomeInitialFeeTitles;//加盟费记录title
        vm.juniorPurchaseTitles=dataService.juniorPurchaseTitles;//下级采购记录title
        vm.districtRedTitles=dataService.districtRedTitles;//辖区设备品牌红包title
        vm.districtBenefitTitles=dataService.districtBenefitTitles;//辖区商户次年服务费title
        //关键指标的获取
        function getInfo() {
            IncomeService.getBenefitSta({proxyType: $rootScope.proxyType})
                .then(function (res) {
                    //console.log(res,"关键指标",res.data.object,"单个数值");
                    var data = res.data.object.financeStatistics;
                    vm.items = data;
                    $("[data-toggle='tooltip']").tooltip();
                });
        }
        //刷新延迟一下就好因为有时后加载main_ctrl.js
        setTimeout(getInfo,500);
        param.type=1;
        loadData($scope.tabType, paramObj,false);

        vm.pre = function () {
            if($scope.tabType=='activerecord'||$scope.tabType=='storeAdDeposit'){
                if (paramObj.page > 1) {
                    paramObj.page--;
                }
                else {
                    paramObj.page = 1;
                }
                vm.currentPage = paramObj.page;
            }
            else {
                if (param.startPage > 1) {
                    param.startPage--;
                }
                else {
                    param.startPage = 1;
                }
                vm.currentPage = param.startPage;
            }
            //向前一页和向后一夜类型不会变所以可以这么写
            if($scope.tabType=='activerecord'||$scope.tabType=='storeAdDeposit')loadData($scope.tabType, paramObj,true);
            else loadData($scope.tabType, param,true);
        };


        vm.next = function () {
            if($scope.tabType=='activerecord'||$scope.tabType=='storeAdDeposit'){
                if (paramObj.page < vm.totalPage) {
                    paramObj.page++;
                }
                else {
                    paramObj.page = vm.totalPage;
                }
                vm.currentPage = paramObj.page;
            }
            else{
                if (param.startPage < vm.totalPage) {
                    param.startPage++;
                }
                else {
                    param.startPage = vm.totalPage;
                }
                vm.currentPage = param.startPage;
            }
            if($scope.tabType=='activerecord'||$scope.tabType=='storeAdDeposit')loadData($scope.tabType, paramObj,true);
            else loadData($scope.tabType, param,true);
        }


        //选项卡点击
        vm.tabToggle = function (type) {

            switch (true){
                case type==="combinationDevice":
                    param.type=1;
                    vm.currentPage = 1;
                    param.startPage=1;
                    delete param.benefitType;
                    break;
                case type==="indirectDevice":
                    param.type=2;
                    vm.currentPage = 1;
                    param.startPage=1;
                    delete param.benefitType;
                    break;
                case type==='storeSharingBenefit':
                    vm.currentPage = 1;
                    param.startPage=1;
                    delete param.type;
                    delete param.benefitType;
                    break;
                case type==='initialFee':
                    vm.currentPage = 1;
                    param.startPage=1;
                    delete param.type;
                    delete param.benefitType;
                    break;
                case type === 'deviceadred':
                    param.type=1;
                    vm.currentPage = 1;
                    param.startPage=1;
                    param.benefitType=1;
                    break;
                case type=== 'directadred':
                    param.type=2;
                    vm.currentPage = 1;
                    param.startPage=1;
                    delete param.benefitType;
                    break;
                case type === 'storeAdDeposit':
                    paramObj.classify='account';
                    vm.currentPage = 1;
                    paramObj.page=1;
                    break;
                case type === 'activerecord':
                    paramObj.classify='device';
                    vm.currentPage = 1;
                    paramObj.page=1;
                    break;
                case type==="juniorPurchase":
                    vm.currentPage = 1;
                    param.startPage=1;
                    delete param.type;
                    delete param.benefitType;
                    break;
                case type==="districtRed":
                    vm.currentPage = 1;
                    param.startPage=1;
                    param.benefitType=2;
                    delete param.type;
                    break;
                default :
                    break;
            }
            if(type=='activerecord'||type=='storeAdDeposit')loadData(type, paramObj,false);
            else loadData(type, param,false);
        }


        //自有设备品牌红包的详情
        vm.getAdDetail = function (index) {
            var strDate=vm.deviceadredlist[index].date;
            $location.path('/incomeAdDetail').search({
                date:new Date(Number(strDate.slice(0,4)),Number(strDate.slice(5,7))-1,Number(strDate.slice(8,10)),8,0,0).getTime()
            });
        }
        //设备激活记录的详情
        vm.recordDetail = function (index) {
            var recordDetailParam = {};
            recordDetailParam.num= vm.activerecordDevicelist[index].num;
            recordDetailParam.day=vm.activerecordDevicelist[index].day ;
            recordDetailParam.benefit= vm.activerecordDevicelist[index].benefit;
            $location.path('/incomerecordDetail').search({
                num: recordDetailParam.num,
                day:recordDetailParam.day,
                benefit:recordDetailParam.benefit
            });
        }

        //商户服务费分成奖励的详情
        vm.sharingDetail=function(index){
            $location.path('/incomeSharingDetail').search({
                createtime:vm.storeSharingBenefitlist[index].createDate
            });
        }

        //商户广告金充值详情
        vm.getStoreAdDeposit=function(index){
            $location.path('/incomeStoreAdDepositDetail').search({
                date:vm.storeAdDepositlist[index].month
            });
        }
         //下级采购记录详情
        vm.getJuniorPurchase=function(index){
            $location.path('/juniorPurchaseDetail').search({
                date:vm.distBuyRecordList[index].times,
                num:vm.distBuyRecordList[index].distDeviceBuyNum,
                benefit:vm.distBuyRecordList[index].distDeviceBuyBenefit
            });
        }
        //下级采购记录详情
        vm.getDistrictRed=function(index){
            $location.path('/districtRedDetail').search({
                date:vm.advBenefitList[index].date
            });
        }
        function loadData(tabType, param,isSearch) {
            if(tabType != $scope.tabType ){
                //触发切换
                vm.currentPage = 1;
                $scope.tabType =tabType;
            }
            switch (tabType) {
                case "combinationDevice":
                    IncomeService.combinationDevice(param)   //组合采购设备记录
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    console.log(res, "第一个选项卡", res.data.object.list)
                                    var data = res.data.object;

                                    vm.combinationDevicelist = data.list;
                                    vm.totalPage = data.pages;
                                }
                            }
                        });
                    break;
                case "indirectDevice":
                    IncomeService.indirectDevice(param)//间接推荐采购记录
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    var data = res.data.object;
                                    vm.indirectDevicelist = data.list;
                                    vm.totalPage = data.pages;
                                }
                            }
                        });
                    break;
                case 'deviceadred':
                    IncomeService.deviceadred(param)//自有设备品牌红包
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                        var obj=angular.fromJson(res.data.object);
                                        vm.deviceadredlist = obj.records;
                                        if(!isSearch)vm.totalPage = obj.pages;
                                }
                            }
                        });
                    break;
                case 'directadred':
                    IncomeService.directadred(param)//直接推荐设备品牌红包
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    var data = angular.fromJson(res.data.object);
                                    vm.directadredlist = data.records;
                                    if(!isSearch)vm.totalPage = data.pages;
                                }
                            }
                        });
                    break;
                case 'activerecord':
                    IncomeService.activerecord(param)   //设备激活记录
                        .then(function (res) {
                            if (res) {
                                if (res.data.errcode== 1) {
                                        var data = res.data.object;
                                        vm.activerecordDevicelist = data.list;
                                        vm.totalPage = data.pages;
                                        if(!isSearch)vm.totalPage=1;
                                }
                            }
                        });
                    break;
                case 'storeAdDeposit':
                    IncomeService.storeAdDepositMonthRecord(param)//商户广告金充值
                        .then(function (res) {
                           // console.log(res);
                            if(res) {
                                if (res.data.error == 0) {
                                    var data = res.data.object;
                                    vm.storeAdDepositlist= data.list;
                                    if(!isSearch)vm.totalPage = data.pages;
                                    if(vm.totalPage<1)vm.totalPage=1;
                                }
                            }
                        });
                    break;
                case 'initialFee':
                    IncomeService.initialFee(param)//加盟费记录
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    if(!isSearch)vm.totalPage = res.data.object.pages;
                                    vm.initialFeelist = res.data.object.franchiseFeeList;
                                }
                            }
                        });
                    break;
                case 'storeSharingBenefit':
                    IncomeService.storeSharingBenefit(param)//商户服务费分成奖励
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    if(!isSearch)vm.totalPage = res.data.object.pages;
                                    vm.storeSharingBenefitlist = res.data.object.proxyMonthBenefitList;
                                }
                            }
                        });
                    break;
                case 'juniorPurchase':
                    IncomeService.distBuyRecordList(param)//商户服务费分成奖励
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    if(!isSearch)vm.totalPage = res.data.object.pages;
                                    vm.distBuyRecordList = res.data.object.distBuyRecordList;
                                }
                            }
                        });
                    break;
                case 'districtRed':
                    IncomeService.advBenefitList(param)//辖区设备品牌红包
                        .then(function (res) {
                            if(res) {
                                if (res.data.errcode == 1) {
                                    var obj=angular.fromJson(res.data.object);
                                    if(!isSearch)vm.totalPage = obj.pages;
                                    vm.advBenefitList = obj.records;
                                }
                            }
                        });
                    break;
                case 'districtBenefit':
                    vm.totalPage = 1;
                    break;
                default:break;
            }
        }
    }
})();