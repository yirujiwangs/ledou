(function () {
    'use strict';

    angular
        .module('center')
        .controller('StartPageListCtrl', StartPageListCtrl);

    StartPageListCtrl.$inject = ['$scope','$route', '$rootScope', 'AdService', 'dataService', '$modal', 'utilService', '$location', 'AlertService','$timeout'];

    function StartPageListCtrl($scope,$route, $rootScope, AdService, dataService, $modal, utilService, $location, AlertService,$timeout) {
        var vm = this;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;
        page.type="screen";
        vm.pre = function () {
            if (page.startPage > 1) {
                page.startPage--;
                vm.currentPage --;
            }
            else {
                page.startPage = 1;
            }

            loadAd(page);
        };
        vm.next = function () {
            if (page.startPage < vm.totalPage) {
                page.startPage++;
                vm.currentPage++;
            }
            else {
                page.startPage = vm.totalPage;
            }

            loadAd();
        }

        vm.titles = dataService.startPageListAdTitles;

        //获取广告信息并根据状态判断是否可编辑/删除以及上下架状态的切换

        loadAd();

        function loadAd(){
            AdService.startPageListAd(page)
                .then(function (res) {
                    if (res.data.errcode==1) {
                        var json=angular.fromJson(res.data.object);
                        vm.totalPage =json.page;
                        var data = json.advlist;

                        for (var i = 0; i < data.length; i++) {
                            if (data[i].status == "WG") {
                                data[i].isPay = "true";
                            } else {
                                data[i].isPay = "false";
                            }
                        }
                        for (var i = 0; i < data.length; i++) {
                            switch (data[i].status) {
                                case "N":
                                    data[i].showStatus = "投放中";
                                    data[i].isDelete = "true";
                                    data[i].isEdit = "false";
                                    data[i].sendStatus = "PM";
                                    data[i].upShow="true";
                                    break;
                                case "PM":
                                    data[i].showStatus = "已下架";
                                    data[i].isDelete = "true";
                                    if(data[i].leftNum>0)data[i].isEdit = "true";
                                    else data[i].isEdit = "false";
                                    data[i].sendStatus = "N";
                                    data[i].upShow="true";
                                    break;
                                case "UP":
                                    data[i].showStatus = "审核中";
                                    data[i].isDelete = "false";
                                    data[i].isEdit = "false";
                                    data[i].upShow="true";
                                    break;
                                case "WG":
                                    data[i].showStatus = "待支付";
                                    data[i].isDelete = "true";
                                    data[i].isEdit = "false";
                                    data[i].upShow="false";
                                    break;
                                case "ERR":
                                    data[i].showStatus = "审核未通过";
                                    data[i].isDelete = "true";
                                    data[i].isEdit = "true";
                                    data[i].upShow="true";
                                    break;
                            }
                        }
                        vm.allAdRecord = data;
                        sessionStorage.setItem("adInfo",angular.toJson(vm.allAdRecord[0]));
                    }
                })
        }


        vm.updateAdvStatus = function (index) {
            if(vm.allAdRecord[index].status == "WG"){
                AlertService.alert({success:false,msg:"请先支付"});
            }
            else if(vm.allAdRecord[index].status == "UP"){
                AlertService.alert({success:false,msg:"等待审核"});
            }
            else if(vm.allAdRecord[index].status == "ERR"){
                AlertService.alert({success:false,msg:"审核未通过"});
            }
            else {
                AdService.updateAdvStatus({
                    advUUID: vm.allAdRecord[index].advUUID,
                    status: vm.allAdRecord[index].sendStatus,
                    type:'screen'
                })
                    .then(function (res) {
                        AlertService.alert({success:true,msg:res.data.errmsg});
                        if(res.data.errcode == 1){
                            $timeout(function(){
                                $route.reload();
                            },1500);
                        }
                    })
            }
        }
        vm.handle = function(index){
            vm.advUUID = vm.allAdRecord[index].advUUID;
            switch (vm.allAdRecord[index].status){
                case "WG":
                    vm.status = "待支付";
                    break;
                case "N":
                    vm.status = "下架";
                    break;
                case "PM":
                    vm.status = "上架";
                    break;
                case "UP":
                    vm.status = "审核中";
                    break;
                case "ERR":
                    vm.status = "审核未通过";
                    break;
            }
        }
        vm.editAd = function(index){
            sessionStorage.setItem("adInfo",angular.toJson(vm.allAdRecord[index]));
            $location.path('/editStartPageAd');
        }
        vm.delete = function(index){
            if(vm.allAdRecord[index].status!="PM"){
                AlertService.alert({success:false,msg:"请先下架广告!"});
                return;
            }
            AdService.updateAdvStatus({
                advUUID: vm.allAdRecord[index].advUUID,
                status: 'D',
                type:'screen'
            })
                .then(function (res) {
                    AlertService.alert({success:true,msg:res.data.errmsg});
                    if(res.data.errcode == 1){
                        $timeout(function(){
                            $route.reload();
                        },1500);
                    }
                    console.log(res.data.errmsg);
                })
        }
        vm.submit = function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAd_pay.html',
                controller: 'AppendAdPayCtrl',
                controllerAs: 'AppendAdPayCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        };
        vm.pay = function (index) {
            //console.log(index);
            AdService.rePay({
                advUUID: vm.allAdRecord[index].advUUID,
            });
        }

        vm.showArea = function (index) {
            AdService.advStrategyDetail({advUUID: vm.allAdRecord[index].advUUID})
                .then(function(data){
                    var res = angular.fromJson(data.data.object);
                    vm.listArea = res.selectedStrategyList;
                    console.log(res.selectedStrategyList)
                })
        }
        vm.appendAd = function () {
            // AlertService.alert({success:true,msg:"稍后即将开放"});
            $location.path('/appendPageListAd');
        }
        //详情模态框
        vm.showStrategy = function (index) {
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/listAd_showStrategy.html',
                controller: 'ListAdShowStrategyCtrl',
                controllerAs: 'listAdShowStrategyCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    adData: function () {
                        var ad = {};
                        ad.advUUID = vm.allAdRecord[index].advUUID;
                        ad.type='screen';
                        return ad;
                    }
                }
            });
        }
    }

})()