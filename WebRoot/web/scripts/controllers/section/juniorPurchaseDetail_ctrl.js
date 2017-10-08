/**
 * Created by Administrator on 2017/8/28 0028.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('JuniorPurchaseDetailCtrl',JuniorPurchaseDetailCtrl);

    JuniorPurchaseDetailCtrl.$inject = [ '$scope',
        '$rootScope','IncomeService','base64Service','$modal','utilService','$location','AlertService'];

    function JuniorPurchaseDetailCtrl($scope,$rootScope,IncomeService,base64Service,$modal,utilService,$location,AlertService){
        var vm=this;
        vm.allInfo=null;
        //列表的内容
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.pageSize = 8;
        param.startPage=1;
        $scope.jumppage=1;
        $scope.num=0;
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
        param.times=canshu[0];
        $scope.num=canshu[1];
        $scope.benefit=(Number(canshu[2])/100).toFixed(2);
        search();

        vm.jump = function(){
            if(param.startPage  <= $scope.jumppage ||  vm.totalPage >= $scope.jumppage){
                vm.currentPage =  $scope.jumppage;
                param.startPage=$scope.jumppage;
            }
            search()
        };
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
            IncomeService.distBuyRecordDetail(param)
                .then(function (res) {
                    if (res) {
                        if (res.data.errcode == 1){
                            vm.allInfo = res.data.object.distBuyDetailList;
                            if(res.data.object.pages==0)res.data.object.pages=1;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;
                        }
                    }
                })
        }
    }
})()