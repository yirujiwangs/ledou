(function(){
    'use strict';
    angular
        .module('center')
        .controller('AttractFansRedPacketCtrl',AttractFansRedPacketCtrl);
    AttractFansRedPacketCtrl.$inject = [ '$scope', '$rootScope','$route','AttractFansRedPacketService','dataService','AlertService','$modal','$location','$timeout'];
    function AttractFansRedPacketCtrl($scope,$rootScope,$route,AttractFansRedPacketService,dataService,AlertService,$modal,$location,$timeout) {
        var vm = this;
        //换页
        vm.allInfo=null;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        param.type='fans';
        vm.goCreateRedPacket=function(){
            $location.path('/createRedPacket')
        }
        vm.goAuthorization=function(){
            $location.path('/authorization')
        }

        vm.titles = dataService.attractFansRedPacketTitles;

        function load() {
            AttractFansRedPacketService.attractFansRedPacketList(param)
                .then(function (res) {
                   // console.log(res)
                    if (res.data.error==0) {
                        vm.totalPage = res.data.object.totalPage;
                        vm.currentPage = param.startPage;
                        var data= res.data.object.data;

                        for (var i = 0; i < data.length; i++) {
                            if (data[i].red_status == "WG") {
                                data[i].isPay = "true";
                            } else {
                                data[i].isPay = "false";
                            }
                            switch (data[i].red_status) {
                                case "N":
                                    data[i].showStatus = "投放中";
                                    data[i].isDelete = "true";
                                    data[i].isEdit = "false";
                                    data[i].sendStatus = "PM";
                                    data[i].upShow="true";
                                    break;
                                case "PM":
                                    data[i].showStatus = "已下架";
                                    data[i].isDelete = "true";
                                    if(data[i].left_num>0)data[i].isEdit = "true";
                                    else data[i].isEdit = "false";
                                    data[i].sendStatus = "N";
                                    data[i].upShow="true";
                                    break;
                                case "UP":
                                    data[i].showStatus = "审核中";
                                    data[i].isDelete = "false";
                                    data[i].isEdit = "false";
                                    data[i].upShow="true";
                                    break;
                                case "WG":
                                    data[i].showStatus = "待支付";
                                    data[i].isDelete = "true";
                                    data[i].isEdit = "false";
                                    data[i].upShow="false";
                                    break;
                                case "ERR":
                                    data[i].showStatus = "审核未通过";
                                    data[i].isDelete = "true";
                                    data[i].isEdit = "true";
                                    data[i].upShow="true";
                                    break;
                            }
                        }
                        vm.allInfo= data;
                    }
                });
        }
        load();

        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            load();
        }
        vm.next = function(){

            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            load();
        }

        vm.handle = function(index){
            vm.advUUID = vm.allInfo[index].serial_num;
            switch (vm.allInfo[index].red_status){
                case "WG":
                    vm.status = "待支付";
                    break;
                case "N":
                    vm.status = "下架";
                    break;
                case "PM":
                    vm.status = "上架";
                    break;
                case "UP":
                    vm.status = "审核中";
                    break;
                case "ERR":
                    vm.status = "审核未通过";
                    break;
            }
        }

        //改变状态
        vm.changeStatus=function(index){
            if(vm.allInfo[index].status == "WG"){
                AlertService.alert({success:false,msg:"请先支付"});
            }
           else if(vm.allInfo[index].red_status == "UP"){
                AlertService.alert({success:false,msg:"等待审核"});
            }
            else if(vm.allInfo[index].red_status== "ERR"){
                AlertService.alert({success:false,msg:"审核未通过"});
            }
            else{
                AttractFansRedPacketService.updateRedPacketStatus({
                    advUUID:vm.allInfo[index].serial_num,
                    status:vm.allInfo[index].sendStatus,
                    type:'fans'
                })
                    .then(function(res){
                        AlertService.alert({success:true,msg:res.data.errmsg});
                        if(res.data.errcode == 1){
                            $timeout(function(){
                                $route.reload();
                            },1500);
                        }
                    })
            }
        }
        vm.delete = function(index){
            if(vm.allInfo[index].red_status!="PM"){
                AlertService.alert({success:false,msg:"请先下架红包!"});
                return;
            }
            AttractFansRedPacketService.updateRedPacketStatus({
                advUUID: vm.allInfo[index].serial_num,
                status:'D',
                type:'fans'
            })
                .then(function (res) {
                    AlertService.alert({success:true,msg:res.data.errmsg});
                    if(res.data.errcode == 1){
                        $timeout(function(){
                            $route.reload();
                        },1500);
                    }
                })
        }
        vm.edit=function(index){
            sessionStorage.setItem("redInfo",angular.toJson(vm.allInfo[index]));
            $location.path('/editRed');
        }
        vm.submit = function(){
            console.log("test");

            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAd_pay.html',
                controller: 'AppendAdPayCtrl',
                controllerAs: 'AppendAdPayCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }
        vm.getRedPacketInfo=function(index){
            var serialNum=vm.allInfo[index].serial_num;
            $location.path('/attractFansRedPacketDetail').search({
                serialNum:serialNum
            });
        }
        //详情模态框
        vm.redPacketDetail = function (index) {
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/redPacket_detail.html',
                controller: 'RedPacketDetailCtrl',
                controllerAs: 'redPacketDetailCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    redPacketId: function () {
                        return vm.allInfo[index].id;
                    }
                }
            });
        }

    }
})()