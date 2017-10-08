
(function(){
    'use strict';

    angular
        .module('center')
        .controller('AttractFansRedPacketDetailCtrl', AttractFansRedPacketDetailCtrl);

    AttractFansRedPacketDetailCtrl.$inject = [ '$scope',
        '$rootScope','AttractFansRedPacketService','dataService','$modal','utilService','$location','AlertService'];

    function AttractFansRedPacketDetailCtrl($scope,$rootScope,AttractFansRedPacketService,dataService,$modal,utilService,$location,AlertService){
        var vm = this;
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
        param.serialNum=canshu[0];
        param.type='fans';

        vm.pre = function () {
            if (param.startPage > 1) {
                param.startPage--;
            }
            else {
                param.startPage = 1;
            }
            loadPage();
        }
        vm.next = function () {
            if (param.startPage < vm.totalPage) {
                param.startPage++;
            }
            else {
                param.startPage = vm.totalPage;

            }
            loadPage();
        }

        loadPage();

        function loadPage(){
            AttractFansRedPacketService.userlist(param)
                .then(function (res) {
                    console.log(res)
                    if(res.data.error==0){
                        if(res.data.error==0){
                            vm.currentPage = param.startPage;
                            vm.totalPage=res.data.object.totalPage;
                            vm.allInfo=res.data.object.userList;
                        }
                    }
                })
        }
    }
})()
/**
 * Created by Administrator on 2017/8/21 0021.
 */
