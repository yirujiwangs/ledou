(function(){
    'use strict';

    angular
        .module('center')
        .controller('IncomeDealerDetailCtrl', IncomeDealerDetailCtrl);

    IncomeDealerDetailCtrl.$inject = [ '$scope',
        '$rootScope','IncomeService','dataService','$modal','utilService','$location','AlertService'];

    function IncomeDealerDetailCtrl($scope,$rootScope,IncomeService,dataService,$modal,utilService,$location,AlertService){
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
            IncomeService.dateDeviceList(param)
                .then(function(res){
                    console.log(res);

                    var data = angular.fromJson(res.data.object);
                    console.log(data)
                    if(data){
                        vm.totalPage=data.pages;
                        vm.allAdRecord = data.devinfos;
                    }
                    // console.log(data);
                })
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
                vm.currentPage++;
            }
            else{
                param.startPage = vm.totalPage;
                vm.currentPage= vm.totalPage;
            }
            IncomeService.dateDeviceList(param)
                .then(function(res){
                    // console.log(res);
                    var data = angular.fromJson(res.data.object);
                    if(data){
                        vm.totalPage=data.pages;
                        vm.allAdRecord = data.devinfos;
                    }
                })
        }

        IncomeService.dateDataIndex($location.search())
            .then(function(res){
                // console.log(res);
                var data = angular.fromJson(res.data.object);
                vm.items = data;
            })

        param = angular.extend({},param,$location.search());
        IncomeService.dateDeviceList(param)
            .then(function(res){
                // console.log(res);
                var data = angular.fromJson(res.data.object);
                if(data){
                    vm.totalPage=data.pages;
                    vm.allAdRecord = data.devinfos;
                }
            })

        vm.search = function(){
            // console.log($scope.form);
            param.startPage = 1;
            param.startTime = $scope.form.startTime;
            param.endTime = $scope.form.endTime;
            param.keyword = $scope.form.keyword;
            // IncomeService.depositDetails(param)
            // .then(function(res){
            //     // console.log(res);
            //     vm.allRecord = res.data.depositWithTaxes;
            // })
        };

        //dataTimepicker相关设置
        moment.locale('zh-cn');
        $scope.form = {};
        $scope.configFunction = function configFunction() {
            return {startView: 'month',minView: 'day'};
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.form.startTime=moment(newDate).format("YYYY-MM-DD")
            $('#dropdown1').dropdown('toggle');
        };
        $scope.inputOnEndTimeSet=function(newDate) {
            $scope.form.endTime=moment(newDate).format("YYYY-MM-DD")
            $('#dropdown2').dropdown('toggle');
        };
        //end dataTimePicker

        // 申请结算
    }
})()