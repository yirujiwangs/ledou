(function(){
    'use strict';

    angular
        .module('center')
        .controller('DistrictRedDetailCtrl', DistrictRedDetailCtrl);

    DistrictRedDetailCtrl.$inject = [ '$scope','IncomeService','$location'];

    function DistrictRedDetailCtrl($scope,IncomeService,$location){
        var vm=this;

        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
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
        var strDate=canshu[0];
        param.date=new Date(Number(strDate.slice(0,4)),Number(strDate.slice(5,7))-1,Number(strDate.slice(8,10))).getTime();
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
        function search(){
            IncomeService.dateRedDataIndex(param)
                .then(function(res){
                    if(res.data.errcode==0){
                        vm.totalPage = 1;
                        vm.allAdRecord = res.data.object.list;
                        vm.items=res.data.object.statistic;
                    }
                });
        }
        search();
    }
})()