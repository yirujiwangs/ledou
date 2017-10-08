/**
 * Created by admin on 2017/5/27.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('IncomerecordDetailCtrl', IncomerecordDetailCtrl);

    IncomerecordDetailCtrl.$inject = [ '$scope',
        '$rootScope','IncomeService','dataService','$modal','utilService','$location','AlertService'];

    function IncomerecordDetailCtrl($scope,$rootScope,IncomeService,dataService,$modal,utilService,$location,AlertService){

        var vm=this;
        vm.allUser=null;
        //列表的内容
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.pageSize = 8;
        param.currentPage = 1;
        param.startPage=1;
        $scope.jumppage=1;
        $scope.benefit=0;
        $scope.num=0;

        if(sessionStorage.proxyType) {

            $scope.policy=sessionStorage.policy;
        }
        else if(localStorage.proxyType){

            $scope.policy=localStorage.policy;
        }

        //顶部参数的获取
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
        $scope.num=canshu[0];
        var strDate=canshu[1];
        param.date=new Date(Number(strDate.slice(0,4)),Number(strDate.slice(5,7))-1,Number(strDate.slice(8,10))).getTime();
        $scope.benefit=Number(canshu[2]);
        if(isNaN($scope.benefit))$scope.benefit=0;
        vm.jump = function(){
            if(param.startPage  <= $scope.jumppage ||  vm.totalPage >= $scope.jumppage){
                vm.currentPage =  $scope.jumppage;
                param.startPage=$scope.jumppage;
            }
            search()
        };
        search();
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            search()
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            search()
        };
        var param = angular.fromJson(param);
        function search() {
            IncomeService.recordData(param)
                .then(function (res) {
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allUser = res.data.object.list;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;
                        }
                    }
                })
        }
    }
})()