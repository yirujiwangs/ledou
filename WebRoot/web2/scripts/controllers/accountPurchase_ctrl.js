(function(){
	'use strict';

	angular
	.module('center')
	.controller('AccountPurchaseCtrl', AccountPurchaseCtrl);

	AccountPurchaseCtrl.$inject = [ '$scope', '$rootScope','$location','AlertService','accountPurchaseService','dataService','$modal'];

	function AccountPurchaseCtrl($scope,$rootScope,$location,AlertService,accountPurchaseService,dataService,$modal){
		var vm = this ;
		vm.titles=dataService.accountPurchaseTitles;


 		// 分页  operator 运营商
 		vm.currentPage = 1;
 		var param = {};
 		param.startPage = 1;
 		param.pageSize = 8;
 		param.state = null;
		//初始化状态未选中
		$scope.state=undefined;
 		vm.pre = function(){
 			if(param.startPage > 1){
 				param.startPage--;
 			}
 			else{
 				param.startPage = 1;
 			}
			if($scope.state)loadPage($scope.state);
			else loadPage();
 		}
 		vm.next = function(){
 			if(param.startPage < vm.totalPage){
 				param.startPage++;
 			}
 			else{
 				param.startPage = vm.totalPage;
 			}
			if($scope.state)loadPage($scope.state);
			else loadPage();
         };
		vm.jump = function(){
			if(param.startPage <= $scope.jumppage  ||  vm.totalPage >=$scope.jumppage){

				param.startPage=$scope.jumppage;
				vm.currentPage=$scope.jumppage;

				console.log($scope.jumppage,param.startPage,vm.currentPage,"跳转成功");
			}
			if($scope.state)loadPage($scope.state);
			else loadPage();
		}
		function loadPage(){
			accountPurchaseService.search(param)
				.then(function(res){
					if(!(res.data instanceof Object)|| res.data.accountOrders.length==0){
						vm.allRecord=null;
						vm.currentPage = param.startPage;
						vm.totalPage=1;
						return;
					}
					vm.allRecord = res.data.accountOrders;
					vm.currentPage = param.startPage;
					vm.totalPage = res.data.pages;
				});
		}

        //搜索 TODO 完善
        vm.search = function(state){
            param.startPage = 1;
			if($scope.form.startTime && $scope.form.endTime){
				var startDate=new Date(Number($scope.form.startTime.slice(0,4)),Number($scope.form.startTime.slice(5,7))-1,Number($scope.form.startTime.slice(8)));
				var endDate=new Date(Number($scope.form.endTime.slice(0,4)),Number($scope.form.endTime.slice(5,7))-1,Number($scope.form.endTime.slice(8)));
				var nowDate=new Date();
				if(startDate.getTime()>endDate.getTime()){
					AlertService.alert({success:false,msg:"起始时间大于结束时间"});
					return;
				}
			}
			if($scope.form.startTime)param.startTime = $scope.form.startTime;
			else delete param.startTime;
			if( $scope.form.endTime)param.endTime = $scope.form.endTime;
			else delete param.endTime;
			if($scope.form.keyword)param.keyword = $scope.form.keyword;
			else delete param.keyword;
			if($scope.form.keyword)param.keyword = $scope.form.keyword;
			else delete param.keyword;
			if(state){
				param.state = state;
				$scope.state=state;
			}
			else{
				delete param.state;
				$scope.state=undefined;
			}
			console.log(param)
			loadPage();
        };
        //核实
        vm.check=function(index){
            // var editSqlModalInstance = $modal.open({
            //     templateUrl: 'views/modals/accountPurchase_check.html',
            //     controller: 'AccountPurchaseCtrl',
            //     controllerAs: 'accountPurchaseCtrl',
            //     backdrop: 'static',
            //     windowClass: 'overflow-y-auto chart-modal',
            //     resolve: {
            //         statusData: function () {
            //             var sta = {};
            //             sta.account = vm.allRecord[index].account;
            //             // sta.status = vm.allRecord[index].status;
            //             return sta;
            //         }
            //     }
            // });
            AlertService.alert({success:true,msg:"核实功能暂时取消"});
        }
        vm.generalNum = [];
        // param.state = 1;
        accountPurchaseService.listTotal(param)
			.then(function(res){
				console.log(param)
				var data = res.data;
				vm.allRecord = data.accountOrders;
				vm.totalPage = data.pages;
				vm.currentPage = param.startPage;
			});

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