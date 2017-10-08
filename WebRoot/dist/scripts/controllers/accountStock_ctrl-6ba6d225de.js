(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountStockCtrl', AccountStockCtrl);

    AccountStockCtrl.$inject = [ '$scope', '$rootScope','dataService','accountService','$modal'];

    function AccountStockCtrl($scope,$rootScope,dataService,accountService,$modal){
        var vm=this;
        vm.titles = dataService.accountStockTitles;
        vm.generalNum = [
            {
                name:"可用账号/账号总个数"
            },
            {
                name:"可用普通账号/普通账号个数"
            },
            {
                name:"可用平台账号/平台账号总个数"
            }
        ];

        // 分页
        vm.currentPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;
        vm.pre = function(){
            if(page.startPage > 1){
                page.startPage--;
            }
            else{
                page.startPage = 1;
            }
            accountService.accountStockHistory(page)
            .then(function(res){
                vm.currentPage = page.startPage;
                var result = res.data.accountbuyrecord;
                vm.allAccount = [];
                for(var i=0;i<result.length;i++){
                    vm.allAccount[i] = {};
                    vm.allAccount[i].modifytime = result[i].modifytime;
                    if(result[i].normal_num_pay == 0){
                        vm.allAccount[i].accountType = "平台类型";
                        vm.allAccount[i].accountNum = result[i].platform_num_pay;
                        vm.allAccount[i].money = vm.allAccount[i].accountNum * 300;
                    }
                    else if(result[i].platform_num_pay == 0){
                        vm.allAccount[i].accountType = "普通类型";
                        vm.allAccount[i].accountNum = result[i].normal_num_pay;
                        vm.allAccount[i].money = vm.allAccount[i].accountNum * 100;
                    }
                }
            });
        }
        vm.next = function(){
            if(page.startPage < vm.totalPage){
                page.startPage++;
            }
            else{
                page.startPage = vm.totalPage;
            }
            accountService.accountStockHistory(page)
            .then(function(res){
                vm.currentPage = page.startPage;
                var result = res.data.accountbuyrecord;
                vm.allAccount = [];
                for(var i=0;i<result.length;i++){
                    vm.allAccount[i] = {};
                    vm.allAccount[i].modifytime = result[i].modifytime;
                    if(result[i].normal_num_pay == 0){
                        vm.allAccount[i].accountType = "平台类型";
                        vm.allAccount[i].accountNum = result[i].platform_num_pay;
                        vm.allAccount[i].money = vm.allAccount[i].accountNum * 300;
                    }
                    else if(result[i].platform_num_pay == 0){
                        vm.allAccount[i].accountType = "普通类型";
                        vm.allAccount[i].accountNum = result[i].normal_num_pay;
                        vm.allAccount[i].money = vm.allAccount[i].accountNum * 100;
                    }
                }
            });
        }

        accountService.accountStockSta()
        .then(function(res){
            // console.log(res);
            var result = res.data.proxyAccountCount;
            vm.generalNum[0].total = result.normal_num_total + result.platform_num_total;
            vm.generalNum[0].active = vm.generalNum[0].total - result.normal_num_used - result.platform_num_used;
            vm.generalNum[1].total = result.normal_num_total;
            vm.generalNum[1].active = vm.generalNum[1].total - result.normal_num_used;
            vm.generalNum[2].total = result.platform_num_total;
            vm.generalNum[2].active = vm.generalNum[2].total - result.platform_num_used;
        });
        var form = {};
        accountService.accountStockHistory(page)
        .then(function(res){
            // console.log(res);
            var result = res.data.accountbuyrecord;
            vm.totalPage = res.data.pages;
            vm.allAccount = [];
            for(var i=0;i<result.length;i++){
                vm.allAccount[i] = {};
                vm.allAccount[i].modifytime = result[i].modifytime;
                // 如果normal数为0，则购买的为平台类型
                if(result[i].normal_num_pay == 0){
                    vm.allAccount[i].accountType = "平台类型";
                    vm.allAccount[i].accountNum = result[i].platform_num_pay;
                    vm.allAccount[i].money = vm.allAccount[i].accountNum * 300;
                }
                else if(result[i].platform_num_pay == 0){
                    vm.allAccount[i].accountType = "普通类型";
                    vm.allAccount[i].accountNum = result[i].normal_num_pay;
                    vm.allAccount[i].money = vm.allAccount[i].accountNum * 100;
                }
            }
            // console.log(vm.allAccount);
        });
    }
})()