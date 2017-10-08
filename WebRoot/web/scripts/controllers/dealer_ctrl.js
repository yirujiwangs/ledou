(function(){
	'use strict';

    angular
    .module('center')
    .controller('DealerCtrl', DealerCtrl);

    DealerCtrl.$inject = [ '$scope', '$rootScope','dataService','dealerService','$modal','$http'];

    function DealerCtrl($scope,$rootScope,dataService,dealerService,$modal,$http){
        var vm = this;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;

        // 后台数据对接完成取消此部分注释
        //dealerService.listTotal(page)
        //    .then(function(res){
        //        console.log(res);
        //        vm.allDealer=res.object.list;
        //        vm.totalPage = res.object.pageSize;
        //        vm.currentPage = res.object.pages;
        //    });


     //  mock 模拟数据； 后台完成删除此部分
        $http({
            url: 'http://rap.taobao.org/mockjsdata/13526/ledou/dealer/',
            method: 'GET'
        }).success(function (res) {
            console.log(res)
            vm.allDealer=res.object.list;
            vm.totalPage = res.object.pageSize;
            vm.currentPage = res.object.pages;
        });


    vm.titles=dataService.dealertitles;
    vm.conversion=dataService.conversion;

    // 分页控制
        vm.pre = function(){
            if(page.startPage > 1){
                page.startPage--;
            }
            else{
                page.startPage = 1;
            }
            accountService.listTotal(page)
                .then(function(res){
                    vm.generalNum=res.data.amount;
                    vm.allUser=res.data.allUser;
                    vm.currentPage = page.startPage;
                });
        }
        vm.next = function(){
            if(page.startPage < vm.totalPage){
                page.startPage++;
            }
            else{
                page.startPage = vm.totalPage;
            }
            accountService.listTotal(page)
                .then(function(res){
                    vm.generalNum=res.data.amount;
                    vm.allUser=res.data.allUser;
                    vm.currentPage = page.startPage;
                });
        }

        //添加经销商
        vm.add=function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/dealer_add.html',
                controller: 'DealerAddCtrl',
                controllerAs: 'dealerAddCtrl',
                size:'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }
        vm.update=function(id){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/dealer_update.html',
                controller: 'DealerUpdateCtrl',
                controllerAs: 'dealerUpdateCtrl',
                size:'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    dealerData: function () {
                        return id;
                    }
                }
            });
        }
        // 返利设置
        vm.rebateSet=function(id){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/dealer_rebate.html',
                controller: 'RebateSetCtrl',
                controllerAs: 'rebateSetCtrl',
                size:'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    dealerData: function () {
                        return id;
                    }
                }
            });
        }
        //分配商户
        vm.merchant=function(id,stock){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/dealer_merchant.html',
                controller: 'MerchantAllotCtrl',
                controllerAs: 'merchantAllotCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    dealerData: function () {
                        var data = {};
                        data.id=id;
                        data.stock=stock;
                        return data;
                    }
                }
            });
        }
        //分配设备
        vm.device=function(id){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/dealer_device.html',
                controller: 'DeviceAllotCtrl',
                controllerAs: 'deviceAllotCtrl',
                backdrop: 'static',
                size:'lg',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    dealerData: function () {
                        return id;
                    }
                }
            });
        }
        //禁用
        vm.forbidden=function(id){

            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/dealer_confirm.html',
                controller: 'DealerConfirmCtrl',
                controllerAs: 'dealerConfirmCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    dealerData: function () {
                        return id;
                    }
                }
            });
        }
    }
})();