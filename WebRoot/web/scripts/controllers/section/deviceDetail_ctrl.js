(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceDetailCtrl', DeviceDetailCtrl);

    DeviceDetailCtrl.$inject = [ '$scope', '$rootScope','$location','dataService','DeviceService','AlertService','$modal','utilService'];

    function DeviceDetailCtrl($scope,$rootScope,$location,dataService,DeviceService,AlertService,$modal,utilService){
        var vm=this;

        var param = {};
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        param.startPage = 1;
        param.pageSize = 15;
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            DeviceService.getDeviceDetail(getDeviceDetailParam)
                .then(function(res){
                    //console.log(res);
                    var data = angular.fromJson(res.data.object);
                    vm.totalPage = data.pages;
                    vm.users = data.deviceInfo;
                })
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            DeviceService.getDeviceDetail(getDeviceDetailParam)
                .then(function(res){
                    console.log(res);
                    var data = angular.fromJson(res.data.object);
                    vm.totalPage = data.pages;
                    vm.users = data.deviceInfo;
                })
        }

        vm.titles = dataService.deviceDetailTitles;
        var getDeviceDetailParam = angular.extend({},param,$location.search());
        // 列表数据
        DeviceService.getDeviceDetail(getDeviceDetailParam)
        .then(function(res){
            console.log(res);  
            var data = angular.fromJson(res.data.object);
            vm.totalPage = data.pages;
            vm.users = data.deviceInfo;
        })

        DeviceService.accountDeviceIndex($location.search())
        .then(function(res){
            console.log(res);
            var data = angular.fromJson(res.data.object);
            vm.generalNum = data;
        })

        // 状态下拉菜单搜索
        vm.stateChange = function(){
            // console.log($scope.keyword);
            delete param.deviceStatus;
            if($scope.stateSelect){
                param.deviceStatus = $scope.stateSelect;
            }
            var getState = angular.extend({},param,$location.search());
            DeviceService.detailStateSearch(getState)
            .then(function(res){
                console.log(res);
                var data = angular.fromJson(res.data.object);
                vm.totalPage = data.pages;
                vm.users = data.deviceInfo;
            })
        }  

        vm.deviceUpdate=function(){
            DeviceService.deviceUpdate(vm.deviceDetail.account,$scope.remark)
            .then(function(res){
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"添加成功"})
                }
                else{
                    AlertService.alert({success:false,msg:"操作失败，请重新添加"})
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err})
            })
        }

        vm.update=function(index){
            var deviceUpdateModalInstance = $modal.open({
                templateUrl: 'views/modals/device_edit.html',
                controller: 'DeviceEditCtrl',
                controllerAs: 'deviceEditCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     deviceDetail: function () {
                         return {device:vm.users[index],account:vm.deviceDetail.account}
                      }
                  }
            });
        }
        vm.del=function(index){
            $scope.ibeaconId=vm.users[index].ibeaconId;
            $scope.ibeaconIdIndex=index
            var deviceDelModalInstance = $modal.open({
                templateUrl: 'views/section/del.html',
                controller: 'DelCtrl',
                controllerAs: 'delCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    service:function(){
                        return 'DeviceService'
                    }
                }
            });
        }
        $rootScope.$on('DeviceService',function(event){
            DeviceService.delDevice({ibeaconId:$scope.ibeaconId})
            .then(function(res){
                if(res.data.flag){
                    vm.users.splice($scope.ibeaconIdIndex,1);
                    AlertService.alert({success:true,msg:"删除成功"})
                }
                else{
                    AlertService.alert({success:false,msg:"删除失败，请重试"})
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err})
            })
        })
        vm.addDevice=function(){
            var datas=[];
            datas.push(vm.deviceDetail);
            // vm.users.push(vm.deviceDetail);
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/device_add.html',
                controller: 'DeviceAddCtrl',
                controllerAs: 'deviceAddCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     shopData: function () {
                         return datas;
                      }
                  }
            });
        }
        vm.search=function(){
            var form={};
            form=utilService.copy($scope.form,form);
            form.account = vm.deviceDetail.account;
            if($scope.form.type=='设备状态'){
                form.type='all';
            }
            else{
                form.type=$scope.form.type;
            }
            if($scope.form.startTime==''&&$scope.form.endTime!=''){
                form.endTime=$scope.form.endTime;
            }
            else if($scope.form.startTime!=''&&$scope.form.endTime==''){
                form.startTime=$scope.form.startTime;
            }
            else if($scope.form.startTime!=''&&$scope.form.endTime!=''){
                if($scope.form.startTime){
                    form.startTime=$scope.form.startTime;
                }
                if($scope.form.endTime){
                    form.endTime=$scope.form.endTime;
                }
                if(Date.parse(form.endTime)<Date.parse(form.startTime)){
                    AlertService.alert({success:false,msg:"查询终止时间不能小于起始时间"})
                    return 
                }
                else{
                    // console.log(form);
                    DeviceService.detailSearch(form)
                    .then(function(res){
                        // console.log(res);
                        vm.users=res.data.general;
                    })
                }
            }                
        }
        vm.removeBind = function(index){
            // console.log(vm.users[index].serialNum);
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/device_unbind.html',
                controller: 'DeviceUnbindCtrl',
                controllerAs: 'deviceUnbindCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                     serialNum: function () {
                         return {serialNum:vm.users[index].serialNum};
                      }
                  }
            });
            
        }

        //dataTimepicker相关设置
        moment.locale('zh-cn');
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