/**
 * Created by admin on 2017/4/27.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('ReportCtrl', ReportCtrl);

    ReportCtrl.$inject = [ '$scope','$route', '$rootScope','$location','AlertService','reportService','dirDao','dataService','$modal'];

    function ReportCtrl($scope,$route,$rootScope,$location,AlertService,reportService,dirDao,dataService,$modal){
        var vm = this;
        // 璁惧畾涓夌骇鑱斿姩瀹瑰櫒鍙虫诞
        $scope.align = false;
        // 鍒嗛〉  operator 杩愯惀
        vm.currentPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        vm.generalNum = [];

        moment.locale('zh-cn');
        $scope.form = {};

        vm.pre = function(){

            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            lodaPage();
        };

        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            lodaPage();
        };
        function lodaPage(){
            reportService.reportList(param)
                .then(function(res){
                    if(res) {
                        if (res.data.errcode == 1) {
                            vm.allRecord = res.data.object.list;
                            vm.currentPage = param.startPage;
                            vm.totalPage = res.data.object.pages;
                        }
                    }
                });
        }
        lodaPage();
        vm.search = function(){
            param.startPage = 1;
            if($scope.areaCode==1)delete param.rid;
            else param.rid = $scope.areaCode;
            console.log(param)
            lodaPage()
        };
        vm.jump = function(){
            if(param.startPage <= $scope.jumppage ||  vm.totalPage >=$scope.jumppage){
                param.startPage=$scope.jumppage;
                vm.currentPage=$scope.jumppage;
            }
            lodaPage();
        };


        vm.detail = function(index){
            // console.log(vm.allRecord[index]);
            $location.path('/accountDetail').search({
                corporationid: vm.allRecord[index].corporationid,
                phone: vm.allRecord[index].phoneNum
            });
        }
        var defaultGroup = [
            {
                name: '报备中',
                status :"B"
            },{
                name: '洽谈中',
                status:"C"
            },
            {
                name: '已签约',
                status: "N"
            }

        ];
        vm.menus = defaultGroup;
        vm.updateReportStatus = function(index,id){
                var newState = {
                    id: id,
                    status: vm.menus[index].status
                }
               console.log(newState,"修改状态")
                reportService.signState(newState).
                    then(function(res){
                        if(res) {
                            if (res.data.errcode == 1) {
                                console.log(newState, "修改状态成功")
                                var flag = angular.fromJson(res.data.errcode);
                                if (flag) {
                                    AlertService.alert({success: true, msg: "修改签约状态成功"});
                                    $route.reload();
                                }
                            }
                        }
                    });

        }

    }
})()