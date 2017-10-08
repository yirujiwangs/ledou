(function(){
	'use strict';

    angular
        .module('center')
        .controller('FortuneCtrl',FortuneCtrl);

    FortuneCtrl.$inject = [ '$scope', '$rootScope','FortuneService','dataService','$modal','$location'];

    function FortuneCtrl($scope,$rootScope,FortuneService,dataService,$modal,$location){
        var vm=this;

        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
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
            FortuneService.listFortune(param)
            .then(function(res){
                vm.allStore=res.data.storeDeposits;
                vm.currentPage = param.startPage;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            FortuneService.listFortune(param)
            .then(function(res){
                vm.allStore=res.data.storeDeposits;
                vm.currentPage = param.startPage;
            });
        }
        vm.titles=dataService.fortuneTitles;

        FortuneService.listFortune(param)
        .then(function(res){
            // console.log(res);
            vm.allStore=res.data.storeDeposits;
            vm.totalPage = res.data.pages;
            vm.currentPage = param.startPage;
        })

        FortuneService.storeFinanceStatistics({
            phone: '123'
        })
        .then(function(res){
            // console.log(res);
            vm.generalNum=res.data.statistics;
        })

        vm.getDetail=function(index){
            dataService.fortuneDetail=vm.allStore[index];
            $location.path('/fortuneDetail');
        }
        vm.search = function(){
            param.keyword = $scope.form.keyword;
            // console.log(param);
            FortuneService.listFortune(param)
                .then(function(res){
                    // console.log(res);
                    vm.allStore=res.data.storeDeposits;
                    vm.totalPage = res.data.pages;
                    vm.currentPage = param.startPage;
                })
        }
    }
})()