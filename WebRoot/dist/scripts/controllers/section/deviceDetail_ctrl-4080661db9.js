(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceDetailCtrl', DeviceDetailCtrl);

    DeviceDetailCtrl.$inject = [ '$scope', '$rootScope','dataService','DeviceService','AlertService','$modal','utilService'];

    function DeviceDetailCtrl($scope,$rootScope,dataService,DeviceService,AlertService,$modal,utilService){
        var vm=this;
        var obj={};
        if(utilService.isObjectValueEqual(dataService.deviceDetail,obj)){
            AlertService.alert({success:false,msg:"请返回上一个界面"})
            return
        }
        // 设备管理数据
        vm.deviceDetail=dataService.deviceDetail;
        vm.titles=dataService.deviceDetailTitles;
        vm.status=dataService.statu;
        $scope.form={};
        $scope.form.type=vm.status[0].name;
        $scope.searchId='';
        vm.conversion=dataService.conversion;

        // 列表数据
        DeviceService.getDeviceDetail(vm.deviceDetail.account)
        .then(function(res){
            // console.log(res);
            vm.users = res.data.personalDevice;
            vm.original = res.data.personalDevice;
        })

        var phoneNum = {
            phoneNum: vm.deviceDetail.account
        };
        DeviceService.storeDS(phoneNum)
        .then(function(res){
            // console.log(res);
            vm.items = res.data.deviceStatistics;
        })        

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