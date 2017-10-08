(function(){
    'use strict';

    angular
        .module('center')
        .controller('AfDeviceCtrl', AfDeviceCtrl);

    AfDeviceCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','AttractFansRedPacketService','$modalInstance','$modal','AlertService','utilService','items'];

    function AfDeviceCtrl($scope,$rootScope,$location,$route,AttractFansRedPacketService,$modalInstance,$modal,AlertService,utilService,items){
        var copyArr = function(arr1,arr2,arr){
            for(var i=0;i<arr1.length;i++){
                arr2[i]={};
                for(var j=0;j<arr.length;j++){
                    arr2[i][arr[j]]=arr1[i][arr[j]];
                }
            }
            return arr2;
        };

        var isExsit = function(key,value,arr){
            var num = -1;
            for(var i=0;i<arr.length;i++){
                if(value === arr[i][key]){
                    num = i;
                }
            }
            return num;
        };

        var vm = this;
        if(items&&items.length!=0&&items[0].groupId){
            var objArr=[];
            var itemsLen=items.length;
            for(var i=0;i<itemsLen;++i){
                objArr.push({
                    name:items[i].groupName,
                    id:items[i].groupId,
                    proxy_name:items[i].proxyName,
                    proxy_token:items[i].proxyToken
                })
            }
            vm.items=objArr;
        }
        else vm.items=items;
        $scope.isAll = false;
        $scope.deviceGroup = [];
        $scope.deviceSearch='';
        //搜索
        vm.search=function(){
            AttractFansRedPacketService.getGroupInfo().
                then(function(res) {
                if (res) {
                    $scope.isAll = false;
                    $scope.toAdd=[];
                    $scope.deviceGroup = res.data.object;
                    if ($scope.deviceSearch == '') {
                        return;
                    }
                    var len = $scope.deviceGroup.length;
                    var reg = new RegExp($scope.deviceSearch);
                    var searchDeviceGroup = [];
                    for (var i = 0; i < len; ++i) {
                        if (reg.test($scope.deviceGroup[i].groupName)) {
                            searchDeviceGroup.push($scope.deviceGroup[i])
                        }
                    }
                    $scope.deviceGroup = searchDeviceGroup;
                }
            })
            //异步必须写在回调函数里
        }

        function getDeviceGroup(){
            AttractFansRedPacketService.getGroupInfo().
                then(function(res){
                    if(res)$scope.deviceGroup = res.data.object;
                    if(!vm.items)return;
                    var allLen= $scope.deviceGroup.length;
                    var choosedLen=vm.items.length;
                    for(var i=0;i<choosedLen;++i){
                        $scope.toAdd.push(vm.items[i]);
                    }
                    for(var i= 0;i<allLen;++i){
                        for(var j=0;j<choosedLen;++j){
                            if(vm.items[j].id==$scope.deviceGroup[i].id) {
                                $scope.deviceGroup[i].selected =true;
                                continue;
                            }
                        }
                    }
                    if($scope.toAdd.length === $scope.deviceGroup.length) {
                        $scope.isAll = true;
                    }
            })
        }
        getDeviceGroup();

        $scope.toAdd = [];

        vm.allToggle = function(){
            if($scope.isAll){
                for(var i=0;i<$scope.deviceGroup.length;i++){
                    $scope.deviceGroup[i].selected = true;
                }
                copyArr(
                    $scope.deviceGroup,
                    $scope.toAdd,
                    ['name','proxy_name','id','proxy_token']
                );
            }
            else{
                for(var i=0;i<$scope.deviceGroup.length;i++){
                    $scope.deviceGroup[i].selected = false;
                }
                $scope.toAdd = [];
            }
        }

        vm.checkToggle = function(index){
            var isSelected = $scope.deviceGroup[index].selected;
            if($scope.isAll){
                if(!isSelected){
                    var exsitIndex = isExsit(
                        'id',
                        $scope.deviceGroup[index].id,
                        $scope.toAdd
                    );
                    $scope.isAll = false;
                    $scope.toAdd.splice(exsitIndex,1);
                }
            }
            else{
                if(isSelected){
                    $scope.toAdd.push($scope.deviceGroup[index]);
                    if($scope.toAdd.length === $scope.deviceGroup.length){
                        $scope.isAll = true;
                    }
                }
                else{
                    var exsitIndex = isExsit(
                        'id',
                        $scope.deviceGroup[index].id,
                        $scope.toAdd
                    );
                    if(exsitIndex !== -1){
                        $scope.toAdd.splice(exsitIndex,1);
                    }
                }
            }
        }

        vm.remove = function(index){
            vm.items.splice(index,1);
        }


        vm.add = function(){
            vm.items = [];
            copyArr(
                $scope.toAdd,
                vm.items,
                ['name','proxy_name','id','proxy_token']
            );
        }

        vm.ok = function(){
            $modalInstance.close(vm.items);
        }

        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }

    }
})()