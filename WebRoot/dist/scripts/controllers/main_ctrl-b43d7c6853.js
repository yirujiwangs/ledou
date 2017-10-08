(function(){
	'use strict';

    angular
        .module('center')
        .controller('MainCtrl', MainCtrl);

    MainCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route'];

    function MainCtrl($scope,$rootScope,dataService,$location,$route){
        if(sessionStorage.userName){
            $scope.user=sessionStorage.userName;
        }
        else if(localStorage.userName){
            $scope.user=localStorage.userName;
            var today=new Date();
            var date=today.getTime()-localStorage.startTime;
            var days=Math.floor(date/(24*3600*1000));
            if(days>7){
                $location.path('/login')
                return
            }
        }
        else{
            $location.path('/login')
            return
        }
        $scope.navs=dataService.buttons;
        /*$scope.changView=function(index){
            if($scope.navs[index].url){
                // 将要跳转的路由
                var toURL = '/' + $scope.navs[index].url;
                // 如果将要跳转的是当前路由，刷新当前路由，否则跳转至新的路由
                if(toURL === $location.path()){
                    $route.reload();
                }
                else{
                    $location.path($scope.navs[index].url);
                }
            }
            for(var i=0;i<$scope.navs.length;i++){
                $scope.navs[i].click=false;
            }
            // 点击的不是资产管理时，将资产管理的isShow设置为false
            if($scope.navs[2].isShow && index !== 2){
                $scope.navs[2].isShow=false;
            }
            $scope.navs[index].click=true;
            // index=2即选择有二级菜单的资产管理时
            if(index === 2){
                // 若isShow状态为true，则将click和isShow设置为false，实现二级菜单的开关
                if($scope.navs[index].isShow){
                    $scope.navs[index].click=false;
                    $scope.navs[index].isShow=false;
                }
                else{
                    $scope.navs[index].click=true;
                    $scope.navs[index].isShow=true;
                }
            }    
            else{
                $scope.navs[index].isShow=true;
            } 
        }*/
        $scope.changSubView=function(subIndex,parentIndex){
            // 将要跳转的路由
            var toURL = '/' + $scope.navs[parentIndex].subnav[subIndex].url;
            // 如果将要跳转的是当前路由，刷新当前路由，否则跳转至新的路由
            if(toURL === $location.path()){
                $route.reload();
            }
            else{
                $location.path($scope.navs[parentIndex].subnav[subIndex].url);
            }
            // 其余所有二级菜单click设置为false
            for(var i=0;i<$scope.navs.length;i++){
                for(var j=0;j<$scope.navs[i].subnav.length;j++){
                    $scope.navs[i].subnav[j].click = false;
                }
            }
            // 设置当前二级菜单click状态为true
            $scope.navs[parentIndex].subnav[subIndex].click=true;
            $scope.title = $scope.navs[parentIndex].name;
            $scope.subTitle = $scope.navs[parentIndex].subnav[subIndex].name;
            // 将当前选中的状态存入localstorage
            localStorage.setItem('subIndex',subIndex);
            localStorage.setItem('parentIndex',parentIndex);
            localStorage.setItem('title',$scope.navs[parentIndex].name);
            localStorage.setItem('subTitle',$scope.navs[parentIndex].subnav[subIndex].name);
        }
        // 更新导航状态，不刷新数据
        $scope.loadView=function(subIndex,parentIndex){
            // 其余所有二级菜单click设置为false
            for(var i=0;i<$scope.navs.length;i++){
                for(var j=0;j<$scope.navs[i].subnav.length;j++){
                    $scope.navs[i].subnav[j].click = false;
                }
            }
            // 设置当前二级菜单click状态为true
            $scope.navs[parentIndex].subnav[subIndex].click=true;
            $scope.title = $scope.navs[parentIndex].name;
            $scope.subTitle = $scope.navs[parentIndex].subnav[subIndex].name;
        }
        var subIndex,parentIndex;
        // 如果localstorage中没有subIndex或parentIndex就设置为0，否则取出
        if(!localStorage.getItem('subIndex') || !localStorage.getItem('parentIndex')){
            subIndex = 0;
            parentIndex = 0;
        }
        else{
            subIndex = localStorage.getItem('subIndex');
            parentIndex = localStorage.getItem('parentIndex');
        }
        $scope.loadView(subIndex,parentIndex);
        $scope.date=moment().format('YYYY年MM月DD日');
        $scope.logOut=function(){
            localStorage.clear();
            sessionStorage.clear();
            $location.path('/login');
        }
    }
})()