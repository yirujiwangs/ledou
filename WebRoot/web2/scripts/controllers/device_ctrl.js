(function(){
	'use strict';

	angular
	.module('center')
	.controller('DeviceCtrl', DeviceCtrl);

	DeviceCtrl.$inject = [ '$scope', '$rootScope','$location','dirDao','AlertService','deviceService','dataService','$modal'];

	function DeviceCtrl($scope,$rootScope,$location,dirDao,AlertService,deviceService,dataService,$modal){
		var vm = this ;
		vm.titles=dataService.devicetitles;

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
 		}
 		vm.next = function(){
 			if(param.startPage < vm.totalPage){
 				param.startPage++;
 			}
 			else{
 				param.startPage = vm.totalPage;
 			}
			loadPage();
 		}
		vm.jump = function(){
			if(param.startPage <= $scope.jumppage ||  vm.totalPage >=$scope.jumppage){
				console.log($scope.jumppage,"跳转成功");
				param.startPage=$scope.jumppage;
				vm.currentPage=$scope.jumppage;
			}
			loadPage();
		}
		function loadPage(){
			deviceService.search(param)
				.then(function(res){
					//console.log(res)
					if(!(res.data instanceof Object)|| res.data.devInfo.length==0){
						vm.allRecord=null;
						vm.currentPage = param.startPage;
						vm.totalPage=1;
						return;
					}
					vm.allRecord = res.data.devInfo;
					vm.totalPage=res.data.pages;
					vm.currentPage = param.startPage;
				})
		}
        //todo 参数不定
        vm.search = function(){
            // console.log($scope.form);
            param.startPage = 1;
			if($scope.form.keyword)param.keyword = $scope.form.keyword;
			else delete param.keyword;
			loadPage();
        };

        vm.undistributed=function(){
            $location.path('/deviceUndistrbute');
        }

        vm.generalNum = [];
        
        deviceService.listDevices(param)
        .then(function(res){
            // console.log(res);
        	var data = res.data;
        	vm.allRecord = data.devInfo;
        	vm.totalPage = data.pages;
        	vm.currentPage = param.startPage;
			data.devTotalNum==undefined?data.devTotalNum=0:data.devTotalNum;
			data.devUsedNum==undefined?data.devUsedNum=0:data.devUsedNum;
			data.workingDevToday==undefined?data.workingDevToday=0:data.workingDevToday;
			data.workingDevYesterday==undefined?data.workingDevYesterday=0:data.workingDevYesterday;
			data.activeDevToday==undefined?data.activeDevToday=0:data.activeDevToday;
			data.activeDevYesterday==undefined?data.activeDevYesterday=0:data.activeDevYesterday;
        	vm.generalNum[0] = {
        		name: "总设备数",
        		number: data.devTotalNum
        	};
        	vm.generalNum[1] = {
        		name: "已激活设备数",
        		number: data.devUsedNum
        	};
        	vm.generalNum[2] = {
        		name: "今日/昨日活跃设备数",
        		number: data.workingDevToday+"/"+data.workingDevYesterday
        	};
			vm.generalNum[3] = {
				name: "今日/昨日激活设备数",
				number: data.activeDevToday+"/"+data.activeDevYesterday
			};
        });
		//设备政策设置
		vm.setting=function(){
			var editSqlModalInstance = $modal.open({
				templateUrl: 'views/modals/device_setting.html',
				controller: 'DeviceSettingCtrl',
				controllerAs: 'deviceSettingCtrl',
				backdrop: 'static',
				windowClass: 'overflow-y-auto chart-modal'
			});
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