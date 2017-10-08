(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountDetailCtrl',AccountDetailCtrl);

   AccountDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dataService','accountService','AlertService','$modal','utilService'];

    function AccountDetailCtrl($scope,$rootScope,$location,dataService,accountService,AlertService,$modal,utilService){
        var vm=this;

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
            accountService.getAccountDetail(param)
            .then(function(res){
               // console.log(res);
               vm.allRecord = res.data.allUser;
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
            accountService.getAccountDetail(param)
            .then(function(res){
                vm.allRecord = res.data.allUser;
                vm.currentPage = param.startPage;
            });
        }

        param = angular.extend({},param,$location.search());
        // 列表数据
        accountService.getAccountDetail(param)
        .then(function(res){
            // console.log(res);
            vm.allRecord = res.data.allUser;
            vm.totalPage = res.data.pages;
            vm.items = res.data.amount;
        });

        //禁用&&解禁
        vm.status=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/accountDetail_status.html',
                controller: 'AccountDetailStatusCtrl',
                controllerAs: 'accountDetailStatusCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    statusData: function () {
                        var sta = {};
                        sta.id = vm.allRecord[index].id;
                        sta.status = vm.allRecord[index].status;
                        return sta;
                    }
                }
            });
        }

        vm.add = function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/accountDetail_add.html',
                controller: 'AccountDetailAddCtrl',
                controllerAs: 'accountDetailAddCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {}
            });
        }

        vm.update = function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/accountDetail_edit.html',
                controller: 'AccountDetailEditCtrl',
                controllerAs: 'accountDetailEditCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    shopData: function(){
                        return vm.allRecord[index];
                    }
                }
            });
        }

        vm.search = function(){
            param.startPage = 1;
            param.startTime = $scope.form.startTime;
            param.endTime = $scope.form.endTime;
            param.keyword = $scope.form.keyword;
            accountService.getAccountDetail(param)
            .then(function(res){
                console.log(res);
                vm.allRecord = res.data.allUser;
                vm.totalPage = res.data.pages;
            });
        }

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
    }
})()