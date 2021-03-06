(function(){
	'use strict';

    angular
        .module('center')
        .controller('AllDeviceCtrl', AllDeviceCtrl);

    AllDeviceCtrl.$inject = [ '$scope', '$rootScope','$route','DeviceService','dataService','$modal','utilService','$location','AlertService'];

    function AllDeviceCtrl($scope,$rootScope,$route,DeviceService,dataService,$modal,utilService,$location,AlertService){
        var vm=this;
        vm.titles = dataService.allDeviceTitles;
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
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
            vm.currentPage = param.startPage;
            getDeviceInfo(param);
        };
        vm.next = function() {
            if (param.startPage < vm.totalPage) {
                param.startPage++;
            }
            else {
                param.startPage = vm.totalPage;
            }
            vm.currentPage = param.startPage;
            getDeviceInfo(param);
        };

        // 获取代理商设备信息列表
        // 默认全部设备groupId为-1
        // 未分组为0
        param.groupId = -1;
        var getDeviceInfo = function (param){
            var mutiSearch = angular.extend({},param);
            DeviceService.search(mutiSearch)
            .then(function(res){
                console.log(res);
                if(res.data.errcode === 1){
                    var data = angular.fromJson(res.data.object);
                        vm.totalPage = data.pages?data.pages:1;
                        vm.items = data.deviceInfo;

                }
                else{
                    AlertService.alert({success:false,msg:"获取设备信息失败，请稍后再试"});
                }
            })
        };

        getDeviceInfo(param);

        // 获取代理商设备分组列表
        DeviceService.deviveGroups()
        .then(function(res){
            console.log(res);
            var data = angular.fromJson(res.data.object).deviceInfo;
             //console.log(data);
            vm.groups = data;
            var defaultGroup = [
            {
                name: '未分组设备',
                id: 0
            }];
            vm.selectGroups = defaultGroup.concat(data);
        });

        // 根据分组获取代理商设备信息
        vm.getDeviceInfomation = function(index){
            var param = {
                startPage: 1,
                pageSize: 8
            };
            if(index === 'all'){
                param.groupId = -1;
            }
            else if(index === 'unAssign'){
                param.groupId = 0;
            }
            else{
                param.groupId = vm.groups[index].id;
            }
            getDeviceInfo(param);
        };

        // 状态下拉菜单搜索
        vm.stateChange = function(){
            // console.log($scope.keyword);
            delete param.status;
            if(!$scope.stateSelect){
            }
            else{
                param.status = $scope.stateSelect;
            }
            DeviceService.stateSearch(param)
            .then(function(res){
                console.log(res);
                var data = angular.fromJson(res.data.object);
                vm.totalPage = data.pages;
                vm.items = data.deviceInfo;
            })
        };
        // 搜索框多级搜索
        vm.search = function(index,type){
            if(index!=null){
                vm.activeIndex =index;
            }
            delete param.status;
            delete param.keyword;
            if(type === 'search'){
                param.keyword = $scope.keyword;
            }

            if(!$scope.stateSelect){
            }
            else{
                param.status = $scope.stateSelect;
                param.startPage = 1;
                vm.currentPage = param.startPage;
            }
            if(index||index===0) {
                param.startPage = 1;
                vm.currentPage = param.startPage;
                if (index === 'all') {
                    param.groupId = -1;
                }
                else if (index === 'unAssign') {
                    param.groupId = 0;
                }
                else {
                    param.groupId = vm.groups[index].id;
                }

                param.startPage = 1;
                vm.currentPage = param.startPage;
            }

            console.log(param);
            getDeviceInfo(param);

        };

        // 新建设备分组
        vm.addGroup = function(){
            DeviceService.createGroup({
                groupName: $scope.addGroupName
            }).then(function(res){
                console.log(res);
                var flag = angular.fromJson(res.data);
                if(flag){
                    if(flag.errcode===1) {
                        AlertService.alert({success: true, msg: "新建成功"});
                        $route.reload();
                    }else{
                        AlertService.alert({success: false, msg: "新建失败，分组名称可能已经被占用哦"});
                    }
                }
            });
            $scope.addGroupName = "";
        };

        // 删除设备分组
        vm.delGroup = function(index){
            var groupId = {
                groupId: vm.groups[index].id
            };

            DeviceService.delGroup(groupId)
                .then(function(res){
                    console.log(res);
                    var flag = angular.fromJson(res.data.object);
                    if(flag){
                        AlertService.alert({success:true,msg:"删除成功"});
                        $route.reload();
                    }
                })
        };

        // 选择分组
        vm.updateGroup = function(index,dtoken){
            var newGroup = {
                deviceId: dtoken,
                groupId: vm.selectGroups[index].id
            };
            DeviceService.updateGroup(newGroup).
            then(function(res){
                // console.log(res);
                var flag = angular.fromJson(res.data.errcode);
                if(flag){
                    AlertService.alert({success:true,msg:"选择分组成功"});
                    $route.reload();
                }
            });
        };

        // 修改备注
        vm.updateRemark = function(index){
           var editSqlModalInstance = $modal.open({
               templateUrl: 'views/modals/allDevice_rev.html',
               controller: 'AllDeviceRevCtrl',
               controllerAs: 'allDeviceRevCtrl',
               backdrop: 'static',
               windowClass: 'overflow-y-auto chart-modal',
               resolve: {
                  deviceData: function () {
                      return {
                        deviceId: vm.items[index].iBeaconID
                      };
                  }
              }
           });
        }
    }
})()