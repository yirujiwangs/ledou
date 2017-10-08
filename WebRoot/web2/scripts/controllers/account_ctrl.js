(function(){
	'use strict';

	angular
	.module('center')
	.controller('AccountCtrl', AccountCtrl);

	AccountCtrl.$inject = [ '$scope', '$rootScope','$location','AlertService','accountService','dirDao','dataService','$modal'];

	function AccountCtrl($scope,$rootScope,$location,AlertService,accountService,dirDao,dataService,$modal){
        var vm = this;
        // 设定三级联动容器右浮动
        $scope.align = false;
 		// 分页  operator 运营商
 		vm.currentPage = 1;
 		var param = {};
 		param.startPage = 1;
 		param.pageSize = 8;

 		vm.pre = function(){
 			if(param.startPage > 1){
 				param.startPage--;
 			}
 			else{
 				param.startPage = 1;
 			}
            loadPage();
 		};
 		vm.next = function(){
 			if(param.startPage < vm.totalPage){
 				param.startPage++;
 			}
 			else{
 				param.startPage = vm.totalPage;
 			}
            loadPage();
 		};
        vm.jump = function(){
            if(param.startPage <= $scope.jumppage ||  vm.totalPage >=$scope.jumppage){
                console.log($scope.jumppage,"跳转成功");
                param.startPage = $scope.jumppage;
                vm.currentPage = $scope.jumppage;
            }
            //console.log("跳转页",$scope.jumppage,"开始页",param.startPage,"当前页", vm.currentPage,"跳转成功");
            loadPage();
        }
        vm.add=function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_add.html',
                controller: 'AccountAddCtrl',
                controllerAs: 'accountAddCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'

            });
        }
        vm.stateList = function(status){
            param.status = status;
            param.startPage =1;
            accountService.stateList(param)
            .then(function(res){
                var data = res.data;
                vm.allRecord = data.corporationInfo;
                vm.totalPage = data.pages;
                vm.currentPage = param.startPage;
            })
        }
        //禁用&&解禁
        vm.status=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_status.html',
                controller: 'AccountStatusCtrl',
                controllerAs: 'accountStatusCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    statusData: function () {
                        var sta = {};
                        sta.account = vm.allRecord[index].phoneNum;
                        sta.status = vm.allRecord[index].status;
                        return sta;
                    }
                }
            });
        }
        //修改
        vm.update=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_edit.html',
                controller: 'AccountEditCtrl',
                controllerAs: 'accountEditCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                   shopData: function () {
                       return vm.allRecord[index];
                   }
                }
            });
        }
        //搜索 TODO 完善
        //搜索时初始化
        vm.search = function(){
            param.startPage = 1;
            if($scope.form.keyword)param.keyword = $scope.form.keyword;
            else delete param.keyword;
            param.areaCode = $scope.areaCode;
            loadPage();
        };
        function loadPage(){
            accountService.search(param)
                .then(function(res){
                    //console.log(res)
                    if(!(res.data instanceof Object)|| res.data.corporationInfo.length==0){
                        vm.allRecord=null;
                        vm.currentPage = param.startPage;
                        vm.totalPage=1;
                        return;
                    }
                    vm.allRecord = res.data.corporationInfo;
                    if(res.data.pages[0]==0)vm.totalPage=1;
                    else vm.totalPage=res.data.pages[0];
                    vm.currentPage = param.startPage;
                })
        }
        vm.detail = function(index){
            // console.log(vm.allRecord[index]);
            $location.path('/accountDetail').search({
                corporationid: vm.allRecord[index].corporationid,
                phone: vm.allRecord[index].phoneNum
            });
        };

        vm.generalNum = [];
        function getInfo() {
            accountService.operatorAccounts(param)
                .then(function (res) {
                    // console.log(res);
                    var data = res.data;
                    vm.allRecord = data.corporationInfo;
                    vm.totalPage = data.pages;
                    vm.currentPage = param.startPage;
                    vm.generalNum[0] = {
                        name: "运营商总数",
                        number: data.corporationNum
                    };
                    vm.generalNum[1] = {
                        name: "商户总数",
                        number: data.totalUserNum
                    };
                    vm.generalNum[2] = {
                        name: "普通类型",
                        number: data.normalNum
                    };
                    vm.generalNum[3] = {
                        name: "平台类型",
                        number: data.totalUserNum-data.normalNum
                    };
                });
        }
        getInfo();
		 //dataTimepicker相关设置
		 moment.locale('zh-cn');
		 $scope.form = {};
		 $scope.configFunction = function configFunction() {
		 	return {startView: 'month',minView: 'day'};
		 };
		 $scope.inputOnTimeSet = function (newDate) {
		 	$scope.form.startTime=moment(newDate).format("YYYY-MM-DD")          
		 	$('#dropdown1').dropdown('toggle');
		 };
		 $scope.inputOnEndTimeSet=function(newDate) {
		 	$scope.form.endTime=moment(newDate).format("YYYY-MM-DD")          
		 	$('#dropdown2').dropdown('toggle');
		 };
        //end dataTimePicker
    }
})()