/**
 * Created by admin on 2017/4/17.
 */
/**
 * Created by admin on 2017/4/14.
 */
(function(){
    'use strict';
    angular
        .module('center')
        .controller('DeviceStockCtrl', DeviceStockCtrl);
    DeviceStockCtrl.$inject = [ '$scope', '$rootScope','dataService','DeviceStockService','$modal'];
    function DeviceStockCtrl($scope,$rootScope,dataService,deviceStockService,$modal){
        var vm = this;
        vm.titles = dataService.deviceStockTitles;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        $scope.keyword = null;

        search();

            //点击操作弹出的模态框
        vm.status=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/deviceStock_status.html',
                controller: 'DeviceStockStatusCtrl',
                controllerAs: 'deviceStockStatusCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    statusData: function () {
                        var sta = {};
                        sta.dNo = vm.allUser[index].logistic_no;
                        return sta;
                    }
                }
            });
        },
        vm.pre = function(){
            console.log('pre');
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            search();

        };
        vm.next = function(){
            console.log('next');
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            search();

        };
        //订单编号搜索
        vm.search = function(){
            param.startPage = 1;
            vm.currentPage = param.startPage;
            search();
        }

        function search(){
            param.tradeNo = $scope.keyword;//input中的搜索值
            deviceStockService.listTotal(param)
                .then(function(res){
                    if(res) {
                        if (res.data.errcode == 1) {
                            vm.allUser = res.data.object.list;
                            vm.totalPage = res.data.object.pages;
                            vm.currentPage = param.startPage;

                            for(var i=0;i< vm.allUser.length;i++){
                                if(vm.allUser[i].status == 'N'){
                                    vm.allUser[i].statusName = '已支付';
                                }else if(vm.allUser[i].status == 'P'){
                                    vm.allUser[i].statusName = '待支付';
                                }else if(vm.allUser[i].status == 'B'){
                                    vm.allUser[i].statusName = '备货中';
                                }else if(vm.allUser[i].status == 'F'){
                                    vm.allUser[i].statusName = '已发货';
                                }
                            }
                        }
                    }
                });
        }

    }
})()