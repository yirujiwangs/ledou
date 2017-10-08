/**
 * Created by Administrator on 2017/7/15 0015.
 */
(function(){
    'use strict';

    angular
        .module('center')
        .controller('JuniorProviderDetailCtrl', JuniorProviderDetailCtrl);

    JuniorProviderDetailCtrl.$inject = [ '$scope',
        '$rootScope','JuniorProviderService','dataService','$modal','utilService','$location','AlertService'];

    function JuniorProviderDetailCtrl($scope,$rootScope,JuniorProviderService,dataService,$modal,utilService,$location,AlertService){
        var vm=this;
        vm.allInfo=null;
        //列表的内容
        vm.currentPage = 1;
        var param = {};
        param.pageSize = 8;
        param.startPage = 1;
        vm.totalPage = 1;
        var obj={
            pageSize:param.pageSize,
            startPage:1
        }
        search();
        $scope.totalDeviceNum=0;
        $scope.totalProviderNum=0;
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
        $scope.totalDeviceBuy=canshu[0];
        $scope.totalDeviceNum=canshu[1];
        $scope.totalProviderNum=canshu[2];
        param.phoneNum=canshu[3];

        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            search();
        }

        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            search();
        };

        var param = angular.fromJson(param);
        function search() {
            JuniorProviderService.juniorProvidertDetailList(param)
                .then(function (res) {
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allInfo=res.data.object.data;
                            res.data.object.pages==0?vm.totalPage=1:vm.totalPage=res.data.object.pages;
                            vm.currentPage = param.startPage;
                        }
                    }
                })
        }
    }
})()