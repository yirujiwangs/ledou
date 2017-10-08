(function(){
	'use strict';

    angular
        .module('center')
        .controller('ListAdAreaCtrl', ListAdAreaCtrl);

    ListAdAreaCtrl.$inject = [ '$scope', '$rootScope', '$location', '$route','AdService','$modalInstance','$modal','AlertService','utilService','param'];

    function ListAdAreaCtrl($scope,$rootScope,$location,$route,AdService,$modalInstance,$modal,AlertService,utilService,param){
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
      $scope.isAll = false;
      $scope.deviceGroup = [];

      console.log(param);
      AdService.advStrategyDetail(param)
      .then(function(res){
        console.log(res);
        var data = angular.fromJson(res.data.object);
        $scope.deviceGroup = data.allStrategyList;
        vm.items = data.selectedStrategyList;
        $rootScope.updateParam = vm.items;
        console.log(vm.items);
      })

      // 待添加的分组
      $scope.toAdd = [];
      // 已添加的分组
      vm.items = [];
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

      // 添加到已选择区域
      vm.add = function(){
        // console.log($scope.toAdd);
        vm.items = [];
        copyArr(
          $scope.toAdd,
          vm.items,
          ['groupName','proxyName','groupId','proxyToken']
        );
        // console.log(vm.items);
      }

      vm.ok = function(){
        $rootScope.updateParam = vm.items;
        console.log($rootScope.updateParam);
        //AdService.updateAdvStrategy(updateParam)
        //.then(function(res){
        //  // console.log(res.data);
        //  var data = angular.fromJson(res.data.object);
        //  // console.log(angular.fromJson(res.data.object).error);
        //  if(data.error === 0){
        //    AlertService.alert({success:true,msg:"更新成功"});
        //  }
        //  else{
        //    AlertService.alert({success:false,msg:"更新失败，请稍后再试"});
        //  }
        //})
      	$modalInstance.dismiss('cancel');
      }

      vm.close = function(){
      	$modalInstance.dismiss('cancel');
      }

    }
})()