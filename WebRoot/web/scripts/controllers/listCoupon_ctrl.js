(function () {
    'use strict';

    angular
        .module('center')
        .controller('ListCouponCtrl', ListCouponCtrl);

    ListCouponCtrl.$inject = ['$scope','$route', '$rootScope', 'AdService', 'dataService', '$modal', 'utilService', '$location', 'AlertService','$timeout'];

    function ListCouponCtrl($scope,$route, $rootScope, AdService, dataService, $modal, utilService, $location, AlertService,$timeout) {
        var vm = this;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;
        page.type='ad';
        vm.pre = function () {
            if (page.startPage > 1) {
                page.startPage--;
                vm.currentPage--;
            }
            else {
                page.startPage = 1;
            }
            loadAd();
        }
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

        vm.titles = dataService.listCouponTitles;
        //获取广告信息并根据状态判断是否可编辑/删除以及上下架状态的切换

        loadAd();

        function loadAd(){

        }

        vm.appendCoupon=function(){
            $location.path('/appendCoupon');
        }
    }
})()