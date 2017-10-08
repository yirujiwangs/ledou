/**
 * Created by admin on 2017/4/15.
 */
(function(){
    'use strict';
    angular
        .module('center')
        .controller('MyreportedCtrl', MyreportedCtrl);

    MyreportedCtrl.$inject =  [ '$scope', '$rootScope','$location','AlertService','myreportedService','dataService','$modal'];

    function MyreportedCtrl($scope,$rootScope,$location,AlertService,myreportedService,dataService,$modal){
        var vm=this;
        vm.allUser=null;
        //列表的内容
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize =15;
        vm.titles=dataService.myreportedMessage;
        $scope.jumppage=1;
        vm.jump = function(){
            if(page.startPage  <= $scope.jumppage ||  vm.totalPage >= $scope.jumppage){
                vm.currentPage =  $scope.jumppage;
                page.startPage=$scope.jumppage;
                console.log($scope.jumppage,vm.currentPage,page.startPage,"页面跳转")

            }
            search()
        };
        //搜索 区域搜索
        vm.search = function(){
            page.startPage = 1;
            page.cid = $scope.areaCode;
            search()
        };
        //页面直接的渲染
        page.cid=1;
        search();

        $scope.isShow = function(){
            if(vm.allUser){
                return true;
            }else{
                return false;
            }
        }
        vm.pre = function(){
            if(page.startPage > 1){
                page.startPage--;
            }
            else{
                page.startPage = 1;
            }
            search()
        }
        vm.next = function(){
            if(page.startPage < vm.totalPage){
                page.startPage++;
            }
            else{
                page.startPage = vm.totalPage;
            }
            search()
        };
        function search() {
            myreportedService.listTotal(page)
                .then(function (res) {
                    if (res) {
                        if (res.data.errcode == 1) {
                            vm.allUser = res.data.object.list;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = page.startPage;
                            for(var i=0;i<vm.allUser.length;i++){
                                if(vm.allUser[i].state == 'N'){
                                    vm.allUser[i].noReport = true;
                                }else{
                                    vm.allUser[i].canReport = false;
                                }
                            }
                        }
                    }
                })
        }
        //报备弹出框
        vm.report=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/myreported_messge.html',
                controller: 'MyreportedMessgeCtrl',
                controllerAs: 'myreportedMessgeCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    statusData: function () {
                        var sta = {};
                        sta.rid = vm.allUser[index].rid;
                        sta.areaCode =  $scope.areaCode;//搜索的内容;
                        sta.index =vm.allUser[index]
                        return sta;
                    },

                }
            });
        };
    }
})();