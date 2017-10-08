(function(){
    'use strict';

    angular
        .module('center')
        .controller('IncomeStoreAdDepositDetailCtrl',IncomeStoreAdDepositDetailCtrl);

    IncomeStoreAdDepositDetailCtrl.$inject = [ '$scope',
        '$rootScope','IncomeService','dataService','$modal','utilService','$location','AlertService'];

    function IncomeStoreAdDepositDetailCtrl($scope,$rootScope,IncomeService,dataService,$modal,utilService,$location,AlertService){
        var vm=this;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param={};
        param.currentPage = 1;
        param.pageSize = 8;

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

        param.month=(new Date(Number(canshu[0].slice(0,4)),(Number(canshu[0].slice(5,7))-1),1)).getTime()/1000;

        function getInfo(){
            IncomeService.storeAdDepositRecordDetail(param)
                .then(function (res) {
                    if (res.data.error == 0) {
                        console.log(res)
                        vm.totalPage = res.data.object.pages;
                        vm.pageInfo = res.data.object.list;
                    }
            });
        }
        getInfo();

        vm.pre = function () {
            if (param.currentPage > 1) {
                param.currentPage--;
            }
            else {
                param.currentPage = 1;
            }
            vm.currentPage = param.currentPage;
            getInfo();
        };

        vm.next = function () {
            if (param.currentPage < vm.totalPage) {
                param.currentPage++;
            }
            else {
                param.currentPage = vm.totalPage;
            }
            vm.currentPage = param.currentPage;
            getInfo();
        }
    }
})()