(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceAllotCtrl', DeviceAllotCtrl);

    DeviceAllotCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','dealerService','DeviceService','dealerData'];

    function DeviceAllotCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,dealerService,DeviceService,dealerData){
    	var vm = this;
        var id = dealerData;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        var selected = [];
        param.status = "A";
        param.startPage = 1;
        param.pageSize = 8;
        vm.selectNum = 0;
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            vm.currentPage = param.startPage;
            getDeviceInfo(param);
        }
        vm.next = function() {
            if (param.startPage < vm.totalPage) {
                param.startPage++;
            }
            else {
                param.startPage = vm.totalPage;
            }
            vm.currentPage = param.startPage;
            getDeviceInfo(param);
        }

        // 获取代理商设备信息列表
        // 默认全部设备groupId为-1
        // 未分组为0
        param.groupId = -1;
        var getDeviceInfo = function (param){
            DeviceService.search(param)
                .then(function(res){
                    console.log(res);
                    if(res.data.errcode === 1){
                        var data = angular.fromJson(res.data.object);
                        vm.totalPage = data.pages?data.pages:1;
                        for(var j = 0;j<selected.length;j++){
                            console.warn(selected[j])
                            for(var i = 0;i<data.deviceInfo.length;i++){
                                if(selected[j]==data.deviceInfo[i].iBeaconID){
                                    data.deviceInfo[i].ischecked = true;
                                }
                            }
                        }

                        vm.items = data.deviceInfo;
                    }
                    else{
                        AlertService.alert({success:false,msg:"获取设备信息失败，请稍后再试"});
                    }
                })
        }
        getDeviceInfo(param);

        //设备勾选
        vm.checkToggle = function(event,iBeaconID){
            vm.isAll = false;
            var action = event.target;
            if(action.checked){
                selected.push(iBeaconID);
            } else{
                var idx = selected.indexOf(iBeaconID);
                if( idx != -1){
                    selected.splice(idx,1);
                }
            }
            vm.selectNum = selected.length;
            console.log(selected)
        }
        //vm.allToggle = function(event){
        //    selected.length = 0;
        //    var action = event.target;
        //    if(action.checked){
        //        for(var i = 0;i<vm.items.length;i++){
        //            selected.push(vm.items[i].iBeaconID);
        //            vm.items[i].ischecked = true;
        //        }
        //    } else{
        //        for(var i = 0;i<vm.items.length;i++){
        //            selected.push(vm.items[i].iBeaconID);
        //            vm.items[i].ischecked = false;
        //        }
        //        selected.length = 0;
        //    }
        //    selectedDevice[(vm.currentPage-1)] = selected;
        //    console.log(selectedDevice)
        //
        //}
        vm.ok = function(){
            dealerService.distributeDevice({"aliveCodes":selected,"id":id})
                .then(function(res){
                    // console.log(res);
                    if(res.data.errcode =="1"){
                        AlertService.alert({success:true,msg:res.data.errmsg});
                    }else{
                        AlertService.alert({success:true,msg:res.data.errmsg});
                    }
                });
            $modalInstance.dismiss('cancel');
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
        // 数组去重
        function removeDuplicatedItem(ar) {
            var tmp = {},
                ret = [];

            for (var i = 0, j = ar.length; i < j; i++) {
                if (!tmp[ar[i]]) {
                    tmp[ar[i]] = 1;
                    ret.push(ar[i]);
                }
            }

            return ret;
        }
    }
})()