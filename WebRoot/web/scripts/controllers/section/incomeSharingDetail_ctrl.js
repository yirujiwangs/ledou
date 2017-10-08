(function(){
    'use strict';

    angular
        .module('center')
        .controller('IncomeSharingDetailCtrl',IncomeSharingDetailCtrl);

    IncomeSharingDetailCtrl.$inject = [ '$scope',
        '$rootScope','IncomeService','base64Service','$modal','utilService','$location','AlertService'];

    function IncomeSharingDetailCtrl($scope,$rootScope,IncomeService,base64Service,$modal,utilService,$location,AlertService){
       var vm=this;
        vm.allInfo=[];
        vm.pageInfo=[];
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param={};
        param.startPage = 1;
        param.pageSize = 8;
        //不用$rootScope.proxyType是因为它是全局变量，而页面刷新会释放内存,而先加载IncomeSharingDetailCtrl模块后才加载main模块，$rootScope.proxyType在main模块中定义。
        //所以加载IncomeSharingDetailCtrl模块时$rootScope.proxyType未定义！
        if(sessionStorage.proxyType){
            param.proxyType = base64Service.base64decode(sessionStorage.proxyType);
            var areaRid=sessionStorage.areaRid;
        }
        else if(localStorage.proxyType){
            param.proxyType = base64Service.base64decode(localStorage.proxyType);
            var areaRid=localStorage.areaRid;
        }
        //参数获取
        var canshu=[];
        var url = $location.absUrl();
        if(url.indexOf("?")!=-1)
        {
            var str=url.substr(url.indexOf("?")+1);
            var strs=str.split("&");
            for(var i=0;i<strs.length;i++)
            {
                canshu.push(strs[i].split("=")[1]);
            }
        }
        param.createtime=canshu[0];

        //if里没法放函数，函数只能存在函数里或者顶层作用域里（window下）
        //执行市级代理逻辑
        if(param.proxyType=='M') {
            vm.allInfo = [];
            $scope.districts = allCity.district[areaRid];
            var len = $scope.districts.length;
            //创造一个对象，它的key是区id，值是区名
            var districtsObj = {};
            for (var i = 0; i < len; i++) {
                districtsObj[$scope.districts[i].id] = $scope.districts[i].name
            }
            getInfoCityLevel();
            //市级按区搜索
            $scope.searchByDistrict = function () {
                vm.currentPage = 1;
                param.startPage = 1;
                searchCityLevel();
            }
            vm.pre = function () {
                if (param.startPage > 1) {
                    param.startPage--;
                }
                else {
                    param.startPage = 1;
                }
                vm.currentPage = param.startPage;
                searchCityLevel();
            };

            vm.next = function () {
                if (param.startPage < vm.totalPage) {
                    param.startPage++;
                }
                else {
                    param.startPage = vm.totalPage;
                }
                vm.currentPage = param.startPage;
                searchCityLevel();
            }
        }
        //前端分页
        function searchCityLevel(){
            vm.pageInfo = [];
            if($scope.district){
                vm.totalPage = 1;
                var len=vm.allInfo.length;
                for(var i=0;i<len;++i){
                    if($scope.district.id==vm.allInfo[i].area_rid){
                        vm.pageInfo.push(vm.allInfo[i]);
                        break;
                    }
                }
            }
            else if(!$scope.district){
                var len;
                param.startPage<vm.totalPage?len=param.startPage*param.pageSize:len=vm.allInfo.length;
                for(var i=(param.startPage-1)*param.pageSize;i<len;++i){
                    vm.pageInfo.push(vm.allInfo[i]);
                }
                vm.currentPage=param.startPage;
            }
        }

        //取数据
        function getInfoCityLevel(){
            IncomeService.incomeSharingDetail(param).then(function (res) {
                if (res.data.errcode == 1) {
                    var lists = res.data.object;
                    for (var i = 0; i < lists.length; i++) {
                        if (lists[i].distProxyBenefit) {
                            lists[i].distProxyBenefit.areaName = districtsObj[lists[i].area_rid]
                            vm.allInfo.push(lists[i])
                        }
                    }
                    vm.allInfo.length==0?vm.totalPage=1:vm.totalPage = Math.ceil(vm.allInfo.length / param.pageSize);
                    var len;
                    param.startPage<vm.totalPage?len=param.startPage*param.pageSize:len=vm.allInfo.length;
                    for(var i=(param.startPage-1)*param.pageSize;i<len;++i){
                        vm.pageInfo.push(vm.allInfo[i]);
                    }
                    //也就是确保当前页是第一页
                    vm.currentPage=param.startPage;
                }
             });
        }

        //执行区县代理逻辑
       if(param.proxyType=='P'){
           loadPageDistrictLevel();
           vm.pre = function () {
               if (param.startPage > 1) {
                   param.startPage--;
               }
               else {
                   param.startPage = 1;
               }
               vm.currentPage = param.startPage;
               loadPageDistrictLevel();
           };

           vm.next = function () {
               if (param.startPage < vm.totalPage) {
                   param.startPage++;
               }
               else {
                   param.startPage = vm.totalPage;
               }
               vm.currentPage = param.startPage;
               loadPageDistrictLevel();
           }
        }
        function loadPageDistrictLevel(){
            IncomeService.incomeSharingDetail(param)
                .then(function (res) {
                    if (res.data.errcode == 1) {
                        vm.pageInfo=res.data.object.proxyDayBenefitList;
                        vm.totalPage=res.data.object.pages;
                        vm.currentPage=param.startPage;
                    }
                })
        }
    }
})()