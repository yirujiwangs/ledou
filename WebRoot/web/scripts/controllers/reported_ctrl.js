/**
 * Created by admin on 2017/4/15.
 */
(function(){
    'use strict';
    angular
        .module('center')
        .controller('ReportedCtrl', ReportedCtrl);

    ReportedCtrl.$inject = [ '$scope', '$rootScope','$route','dataService','reportedService','$modal'];

    function ReportedCtrl($scope,$rootScope,$route,dataService,reportedService,$modal) {
        var vm = this;
        vm.currentPage = 1;
        vm.totalPage = 1;
        $scope.jumppage = 1;

        var page = {};
        page.startPage = 1;
        page.pageSize = 10;
        page.cid = 1;

        vm.toptitle = dataService.reportedtitles;


        search();


        vm.jump = function () {
            if (page.startPage <= $scope.jumppage || vm.totalPage >= $scope.jumppage) {
                console.log($scope.jumppage, "跳转成功");
                vm.currentPage = $scope.jumppage;
                page.startPage = $scope.jumppage;
            }

            search();

        };
        vm.pre = function () {
            console.log("pre");
            if (page.startPage > 1) {
                page.startPage--;
            }
            else {
                page.startPage = 1;
            }

            search();

        }
        vm.next = function () {
            console.log("next");
            if (page.startPage < vm.totalPage) {
                page.startPage++;
            }
            else {
                page.startPage = vm.totalPage;
            }
            search();
        };


        function search() {

            reportedService.listTotal(page)

                .then(function (res) {
                    vm.allUser = res.data.object.list;
                    vm.totalPage = res.data.object.pages;
                    vm.currentPage = page.startPage;
                    for(var i=0;i<vm.allUser.length;i++){

                        if(vm.allUser[i].status == 'N'){
                            vm.allUser[i].signState = "已签约";
                        }else if(vm.allUser[i].status == 'B'){
                            vm.allUser[i].signState = "报备中";
                        }else if(vm.allUser[i].status == 'C'){
                            vm.allUser[i].signState = "洽谈中";
                        }else{
                            vm.allUser[i].signState = "正常";
                        }
                    }

                });
        }
    }
})();