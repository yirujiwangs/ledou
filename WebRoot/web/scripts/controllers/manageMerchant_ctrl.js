/**
 * Created by Administrator on 2017/7/25 0025.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('ManageMerchantCtrl', ManageMerchantCtrl);

    ManageMerchantCtrl.$inject = [ '$scope',
        '$rootScope','ManageMerchantService','dataService','$modal','utilService','$location','AlertService'];

    function ManageMerchantCtrl($scope,$rootScope,ManageMerchantService,dataService,$modal,utilService,$location,AlertService) {
        var vm = this;
        vm.allInfo = null;
        //列表的内容
        vm.currentPage = 1;
        var param = {};
        param.pageSize = 8;
        param.startPage = 1;
        vm.totalPage = 1;
        ManageMerchantService.statistics()
            .then(function(res){
                if(res) {
                    if(res.data.errcode==1){
                        vm.generalNum = res.data.object[0];
                    }
                }
            });
        vm.titles = dataService.manageMerchantTitles;
        ManageMerchantService.manageMerchantList(param)
            .then(function(res){
                if (res) {
                    if (res.data.errcode == 1) {
                        vm.allInfo = res.data.object.storeList;
                        vm.totalPage = res.data.object.pages;
                        vm.currentPage = param.startPage;
                    }
                }
            });
        vm.checkStore=function(index){
            $location.path('/checkStore').search({
                status:vm.allInfo[index].check_status,
                id:vm.allInfo[index].id
            });
        }
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            ManageMerchantService.manageMerchantList(param)
                .then(function(res){
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allInfo = res.data.object.storeList;
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
            ManageMerchantService.manageMerchantList(param)
                .then(function(res){
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allInfo = res.data.object.storeList;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;
                        }
                    }
                });
        }
        vm.canSubmit=function(msg){
            switch (msg){
                case 0:
                    return true;
                case 1:
                    return false;
                case 2:
                    return true;
                case 3:
                    return false;
                case 4:
                    return true;
            }
        }
    }
})()