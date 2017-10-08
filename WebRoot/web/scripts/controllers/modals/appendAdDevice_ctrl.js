(function(){
    'use strict';

    angular
        .module('center')
        .controller('AppendAdDeviceCtrl', AppendAdDeviceCtrl);

    AppendAdDeviceCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','AdService','$modalInstance','$modal','AlertService','utilService','items','type'];

    function AppendAdDeviceCtrl($scope,$rootScope,$location,$route,AdService,$modalInstance,$modal,AlertService,utilService,items,type){
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
        vm.items=items;
        $scope.isAll = false;
        $scope.deviceGroup = [];
        $scope.deviceSearch='';
        //搜索
        vm.search=function(){
            AdService.advStrategyDetail({
                advUUID:null,
                proxyType:$rootScope.proxyType,
                choose:'group',
                type:type
            }).then(function(res) {
                if (res) {
                    //搜索后清空所有选中
                    $scope.isAll = false;
                    $scope.toAdd=[];
                    var data = angular.fromJson(res.data.object);
                    $scope.deviceGroup = data.allStrategyList;
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

        // console.log(param);
        function getDeviceGroup(){
            AdService.advStrategyDetail({
                advUUID:null,
                proxyType:$rootScope.proxyType,
                choose:'group',
                type:type
            }).then(function(res){
                if(res){
                    var data = angular.fromJson(res.data.object);
                    $scope.deviceGroup = data.allStrategyList;
                    if(!items)return;
                    var allLen= $scope.deviceGroup.length;
                    var choosedLen=items.length;
                    for(var i=0;i<choosedLen;++i){
                        $scope.toAdd.push(items[i]);
                    }
                    for(var i= 0;i<allLen;++i){
                        for(var j=0;j<choosedLen;++j){
                            if(items[j].groupId==$scope.deviceGroup[i].groupId) {
                                $scope.deviceGroup[i].selected =true;
                                continue;
                            }
                        }
                    }
                    if($scope.toAdd.length === $scope.deviceGroup.length) {
                        $scope.isAll = true;
                    }
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
                    ['groupName','proxyName','groupId','proxyToken']
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
                        'groupId',
                        $scope.deviceGroup[index].groupId,
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
                        'groupId',
                        $scope.deviceGroup[index].groupId,
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
            // console.log($scope.toAdd);
            vm.items = [];
            copyArr(
                $scope.toAdd,
                vm.items,
                ['groupName','proxyName','groupId','proxyToken']
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