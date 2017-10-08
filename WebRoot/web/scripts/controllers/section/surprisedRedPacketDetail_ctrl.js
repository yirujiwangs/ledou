
(function(){
    'use strict';

    angular
        .module('center')
        .controller('SurprisedRedPacketDetailCtrl', SurprisedRedPacketDetailCtrl);

    SurprisedRedPacketDetailCtrl.$inject = [ '$scope',
        '$rootScope','AdService','dataService','$modal','utilService','$location','AlertService'];

    function SurprisedRedPacketDetailCtrl($scope,$rootScope,AdService,dataService,$modal,utilService,$location,AlertService){
        var vm = this;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.currentPage = 1;
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
        param.advUUID=canshu[0];
        param.type='surprise';
        vm.pre = function () {
            if (param.currentPage > 1) {
                param.currentPage--;
            }
            else {
                param.currentPage = 1;
            }
            loadPage();
        }
        vm.next = function () {
            if (param.currentPage < vm.totalPage) {
                param.currentPage++;
            }
            else {
                param.currentPage = vm.totalPage;

            }

            loadPage();
        }
        loadPage();
        function loadPage(){
            AdService.bigRedData(param)
                .then(function (res) {
                    if(res.data.error==0 && res.data.error_reason !="暂无数据"){
                        vm.totalPage =res.data.object.totalPage;
                        vm.currentPage=param.currentPage;
                        vm.allInfo = res.data.object.data;
                    }
                })
        }
    }
})()
