/**
 * Created by admin on 2017/4/14.
 */
(function(){
    'use strict';
    angular
        .module('center')
        .controller('ExtendedProviderCtrl', ExtendedProviderCtrl);
    ExtendedProviderCtrl.$inject = [ '$scope', '$rootScope','$route','ExtendedProviderService','dataService','DeviceService','$modal'];
    function ExtendedProviderCtrl($scope,$rootScope,$route,ExtendedProviderService,dataService,deviceService,$modal){
        var vm=this;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;

        $(function () { $("[data-toggle='tooltip']").tooltip(); });

        vm.titles=dataService.extendedProviderTitles;

        ExtendedProviderService.statistics()
            .then(function(res){
                if(res) {
                    if(res.data.errcode==1){
                        vm.generalNum = res.data.object;
                }
            }

            });

        ExtendedProviderService.extendedProvidertList(param)
            .then(function(res){
                if (res) {
                    if (res.data.errcode == 1) {
                        vm.allStore = res.data.object.list;
                        vm.totalPage = res.data.object.pages;
                        vm.currentPage = param.startPage;
                    }
                }
            });

        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            ExtendedProviderService.extendedProvidertList(param)
                .then(function(res){
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allStore = res.data.object.list;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;
                        }
                    }
                });
        }
        vm.next = function(){

            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            ExtendedProviderService.extendedProvidertList(param)
                .then(function(res){
                    console.log(res);
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allStore = res.data.object.list;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;
                        }
                    }
                });
        }


    }
})()