(function(){
	'use strict';

    angular
        .module('center')
        .controller('FinanceStoreDetailCtrl',FinanceStoreDetailCtrl);

   FinanceStoreDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dataService','financeService','AlertService','$modal','utilService'];

    function FinanceStoreDetailCtrl($scope,$rootScope,$location,dataService,financeService,AlertService,$modal,utilService){
        var vm=this;
        vm.titles = dataService.financeStoreDetailTitles;
        // var obj={};
        // if(utilService.isObjectValueEqual(dataService.accountDetailId,obj)){
        //     AlertService.alert({success:false,msg:"请返回上一个界面"})
        //     return
        // }
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
            financeService.getFinanceStoreDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.allData = res.data.deposiWithTaxes;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            financeService.getFinanceStoreDetail(param)
            .then(function(res){
                vm.currentPage = param.startPage;
                vm.allData = res.data.deposiWithTaxes;
            });
        }

        // 浅拷贝对象
        var param = angular.extend({},param,$location.search());
        // console.log(param);
        // 列表数据
        financeService.getFinanceStoreDetail(param)
        .then(function(res){
            // console.log(res);
            vm.totalPage = res.data.pages;
            vm.allData = res.data.depositWithTaxes;
        })
    }
})()