(function(){
	'use strict';

	angular
	.module('center')
	.controller('RefundCtrl',RefundCtrl);

	RefundCtrl.$inject = [ '$scope', '$rootScope','refundService','dataService','$modal','$location'];

	function RefundCtrl($scope,$rootScope,refundService,dataService,$modal,$location){
		var vm=this;

        // 分页
        vm.currentPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        param.tradeState = undefined;
		//初始化全为0
		vm.generalNum ={};
		vm.generalNum.totalAvaiable = 0;
		vm.generalNum.totalIncome = 0;
		vm.generalNum.totalBalanced = 0;
		vm.generalNum.totalBalancing = 0;
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

        refundService.listRefund(param)
        .then(function(res){
            console.log(res);
        	vm.allRecord=res.data.tradeOrderList;
        	vm.totalPage = res.data.pages;
        	vm.currentPage = param.startPage;
        	vm.generalNum.totalAvaiable = res.data.totalAvaiable;
        	vm.generalNum.totalIncome = res.data.totalIncome;
        	vm.generalNum.totalBalanced = res.data.totalBalanced;
        	vm.generalNum.totalBalancing = res.data.totalBalancing;
        })

        vm.process=function(index){
            var editSqlModalInstance = $modal.open({
            	templateUrl: 'views/modals/refund_process.html',
            	controller: 'RefundProcessCtrl',
            	controllerAs: 'refundProcessCtrl',
            	backdrop: 'static',
            	windowClass: 'overflow-y-auto chart-modal',
            	resolve: {
            		refundProcessData : function(){
                        return {
                            tradeId: vm.allRecord[index].tradeId,
                        };
                    }
                }
            });
        }
          // 详情
        vm.detail=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/refund_detail.html',
                controller: 'RefundDetailCtrl',
                controllerAs: 'refundDetailCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    RefundDetailData : function(){
                        var data = {
                            tradeId: vm.allRecord[index].tradeId
                        }
                        return data;
                    }
                }
            });
        }

        //todo 
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
			if(state){
				param.state = state;
				$scope.state=state;
			}
			else{
				delete param.state;
				$scope.state=undefined;
			}
			loadPage();
        }

		function loadPage(){
			refundService.search(param)
				.then(function(res){
					vm.allRecord=res.data.tradeOrderList;
					vm.totalPage = res.data.pages[0];
					vm.currentPage = param.startPage;
					vm.generalNum.totalAvaiable = res.data.totalAvaiable;
					vm.generalNum.totalIncome = res.data.totalIncome;
					vm.generalNum.totalBalanced = res.data.totalBalanced;
					vm.generalNum.totalBalancing = res.data.totalBalancing;
				})
		}


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