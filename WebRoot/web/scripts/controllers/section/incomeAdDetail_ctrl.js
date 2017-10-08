(function(){
    'use strict';

    angular
        .module('center')
        .controller('IncomeAdDetailCtrl', IncomeAdDetailCtrl);

    IncomeAdDetailCtrl.$inject = [ '$scope',
        '$rootScope','IncomeService','dataService','$modal','utilService','$location','AlertService'];

    function IncomeAdDetailCtrl($scope,$rootScope,IncomeService,dataService,$modal,utilService,$location,AlertService){
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
                vm.currentPage--;
            }
            else{
                param.startPage = 1;
                vm.currentPage=1;
            }
            search();
        };
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
                vm.currentPage++;
            }
            else{
                param.startPage = vm.totalPage;
                vm.currentPage= vm.totalPage;
            }
            search();
        };
        vm.jump = function(){
            if(param.startPage  <= $scope.jumppage ||  vm.totalPage >= $scope.jumppage){
                vm.currentPage =  $scope.jumppage;
                param.startPage=$scope.jumppage;
            }
            search();
        };
        IncomeService.dateDataIndex($location.search())
            .then(function(res){
                var data = angular.fromJson(res.data.object);
                vm.items = data;
            });

        param = angular.extend({},param,$location.search());

        function search(){
            IncomeService.dateDeviceList(param)
                .then(function(res){
                    var data = angular.fromJson(res.data.object);
                    if(data){
                        if(data.pages>0) {
                            vm.totalPage = data.pages;
                        }
                        vm.allAdRecord = data.devinfos;
                    }
                });
        }
        search();
    }
})()