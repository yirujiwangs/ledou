(function(){
  'use strict';

    angular
        .module('center')
        .controller('AppendAdAreaCtrl', AppendAdAreaCtrl);

    AppendAdAreaCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','AdService','$modalInstance','$modal','AlertService','utilService','items','type'];

    function AppendAdAreaCtrl($scope,$rootScope,$location,$route,AdService,$modalInstance,$modal,AlertService,utilService,items,type){
      if(sessionStorage.userName)$scope.proxyName=sessionStorage.userName;
      else if(localStorage.userName)$scope.proxyName=localStorage.userName;
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
      // 已添加的分组为items
      vm.items=items;
      //提取选中项

      $scope.isAll = false;
      $scope.areaGroup = [];

      $scope.areaSearch='';
      //搜索
      vm.search=function() {
        AdService.advStrategyDetail({
          advUUID: null,
          proxyType:$rootScope.proxyType,
          choose:'area',
          type:type
        }).then(function (res) {
          if (res) {
            if(res.data.errcode==1) {
              //搜索后清空所有选中
              $scope.isAll = false;
              $scope.toAdd = [];
              $scope.areaGroup = res.data.object;
              if ($scope.areaSearch == '') {
                return;
              }
              var len = $scope.areaGroup.length;
              var reg = new RegExp($scope.areaSearch);
              var searchAreaGroup = [];
              for (var i = 0; i < len; ++i) {
                if (reg.test($scope.areaGroup[i].area_name)) {
                  searchAreaGroup.push($scope.areaGroup[i])
                }
              }
              $scope.areaGroup = searchAreaGroup;
            }
          }
        })
      }
      // console.log(param);
      function getAreaGroup(){
        AdService.advStrategyDetail({
          advUUID: null,
          proxyType:$rootScope.proxyType,
          choose:'area',
          type:type
        }).then(function(res){
          if(res){
            if(res.data.errcode==1){
              $scope.areaGroup = res.data.object;
              if(!items)return;
              var allLen= $scope.areaGroup.length;
              var choosedLen=items.length;
              for(var i=0;i<choosedLen;++i){
                $scope.toAdd.push(items[i]);
              }
              for(var i= 0;i<allLen;++i){
                  for(var j=0;j<choosedLen;++j){
                    if(items[j].area_rid==$scope.areaGroup[i].area_rid) {
                      items[i].area_name=$scope.areaGroup[i].area_name;
                      $scope.areaGroup[i].selected =true;
                      continue;
                    }
                  }
              }
              if($scope.toAdd.length === $scope.areaGroup.length){
                $scope.isAll = true;
              }
            }
          }
        })
      }
      getAreaGroup();

      // 待添加的分组
      $scope.toAdd = [];

      vm.allToggle = function(){
        if($scope.isAll){
          for(var i=0;i<$scope.areaGroup.length;i++){
            $scope.areaGroup[i].selected = true;
          }
          copyArr(
            $scope.areaGroup,
            $scope.toAdd,
            ['area_name','area_rid']
          );
        }
        else{
          for(var i=0;i<$scope.areaGroup.length;i++){
            $scope.areaGroup[i].selected = false;
          }
          $scope.toAdd = [];
        }
      }

      vm.checkToggle = function(index){
        var isSelected = $scope.areaGroup[index].selected;
        if($scope.isAll){
          if(!isSelected){
            var exsitIndex = isExsit(
              'area_rid',
              $scope.areaGroup[index].area_rid,
              $scope.toAdd
            );
            $scope.isAll = false;
            $scope.toAdd.splice(exsitIndex,1);
          }
        }
        else{
          if(isSelected){
            $scope.toAdd.push($scope.areaGroup[index]);
            if($scope.toAdd.length === $scope.areaGroup.length){
              $scope.isAll = true;
            }
          }
          else{
            var exsitIndex = isExsit(
              'groupId',
              $scope.areaGroup[index].groupId,
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

      // 添加到已选择区域
      vm.add = function(){
        vm.items = [];
        copyArr(
          $scope.toAdd,
            vm.items,
          ['area_name','area_rid']
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