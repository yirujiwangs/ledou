(function(){
	'use strict';

    angular
        .module('center')
        .controller('MainCtrl', MainCtrl);

    MainCtrl.$inject = [ '$scope', '$rootScope','dataService','base64Service','$location','$route'];

    function MainCtrl($scope,$rootScope,dataService,base64Service,$location,$route){
        if(sessionStorage.userName){
            $scope.user=sessionStorage.userName;
            $rootScope.areaName=sessionStorage.areaName;
            $rootScope.areaRid=sessionStorage.areaRid;
            $rootScope.proxyType=base64Service.base64decode(sessionStorage.proxyType);
            $rootScope.policy=sessionStorage.policy;

         //   alert( $rootScope.proxyType);
        }
        //判断7天免登陆
        else if(localStorage.userName){
            $scope.user=localStorage.userName;
            $rootScope.areaName=localStorage.areaName;
            $rootScope.areaRid=localStorage.areaRid;
            $rootScope.proxyType=base64Service.base64decode(localStorage.proxyType);
            $rootScope.policy=localStorage.policy;
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
        sessionStorage.setItem("bind",0);
        $scope.navs=dataService.buttons;

        //判断权限是否是市级代理(是否显示下级服务商模块)
        //引用类型改变了$scope.navs，dataService.buttons也会改变
        //通过函数判断区县和市级代理菜单栏显示,后期也可以添加
        $scope.isShow=function(subnavName){
            if($rootScope.proxyType=="P"&&subnavName=="下级服务商")return false;
            else return true;
        }
        $scope.showThirdTitle=true;
        var routePath;
        $location.url().indexOf('?')==-1? routePath=$location.url():routePath = $location.url().slice(0,$location.url().indexOf('?'));
        //判断三级标题
        switch (routePath){
            case '/juniorProviderDetail':
                $scope.thirdTitle='拓展详情';
                break;
            case '/incomerecordDetail':
                $scope.thirdTitle='设备激活详情';
                break;
            case '/incomeAdDetail':
                $scope.thirdTitle='品牌红包';
                break;
            case '/surprisedRedPacketDetail':
                $scope.thirdTitle='惊喜红包详情';
                break;
            case '/attractFansRedPacketDetail':
                $scope.thirdTitle='吸粉红包详情';
                break;
            case '/incomeSharingDetail':
                $scope.thirdTitle='商户服务费分成奖励详情';
                break;
            case '/incomeStoreAdDepositDetail':
                $scope.thirdTitle='商户广告金充值详情';
                break;
            case '/juniorPurchaseDetail':
                $scope.thirdTitle='下级采购详情';
                break;
            case '/authorization':
                $scope.thirdTitle='授权列表';
                break;
            default:
                $scope.showThirdTitle=false;
                break;
        }
        $scope.changeSubRouting=function(){
            switch($scope.subTitle){
                case '设备管理':
                    $location.path('/device');
                    break;
                case '设备采购':
                    $location.path('/devicePurchase');
                    break;
                case '订单管理':
                    $location.path('/deviceStock');
                    break;
                case '商户管理':
                    $location.path('/manageMerchant');
                    break;
                case '拓展业绩':
                    $location.path('/extendedProvider');
                    break;
                case '下级服务商':
                    $location.path('/juniorProvider');
                    break;
                case '报备列表':
                    $location.path('/reported');
                    break;
                case '我要报备':
                    $location.path('/myreported');
                    break;
                case '图片广告':
                    $location.path('/listAd');
                    break;
                case '惊喜红包广告':
                    $location.path('/listRedAd');
                    break;
                case '启动页广告':
                    $location.path('/startPageListAd');
                    break;
                case '吸粉红包':
                    $location.path('/attractFansRedPacket');
                    break;
                case '收益明细':
                    $location.path('/income');
                    break;
                case '结算中心':
                    $location.path('/balance');
                    break;
                case '我的账号':
                    $location.path('/myaccount');
                    break;
                default :
                    break;
            }
        }
        $scope.changeRouting=function(){
            switch ($scope.title){
                case '设备':
                    $scope.changSubView(0,0);
                    break;
                case '商户':
                    $scope.changSubView(0,1);
                    break;
                case '拓展服务商':
                    $scope.changSubView(0,2);
                    break;
                case '报备':
                    $scope.changSubView(0,3);
                    break;
                case '营销':
                    $scope.changSubView(0,4);
                    break;
                case '资产管理':
                    $scope.changSubView(0,5);
                    break;
                case '个人中心':
                    $scope.changSubView(0,6);
                    break;
                default :
                    break;
            }
        }
        $scope.changSubView=function(subIndex,parentIndex){
            // 将要跳转的路由
            if( $scope.navs[parentIndex].subnav[subIndex].url=="income"&&$rootScope.policy==1)$scope.navs[parentIndex].subnav[subIndex].url="oldIncome";
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
        var isBindWechat = sessionStorage.getItem("isBindWechat");

        if(isBindWechat == 0){
                subIndex = 0;
                parentIndex = 4;
            }

        else{
                if(!localStorage.getItem('subIndex') || !localStorage.getItem('parentIndex')) {
                    subIndex = 0;
                    parentIndex = 0;
                }
                else {
                    subIndex = localStorage.getItem('subIndex');
                    parentIndex = localStorage.getItem('parentIndex');
                }
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