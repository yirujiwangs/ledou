(function () {
    'use strict';

    angular
    .module('center', ['ngRoute','ui.bootstrap','ui.bootstrap.datetimepicker']);
    angular
    .module('center')
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                .when(
                    '/', 
                    {
                        templateUrl: 'views/device.html',
                        controller: 'AllDeviceCtrl',
                        controllerAs: 'allDeviceCtrl'
                        //templateUrl: 'views/account.html',
                        //controller: 'AccountCtrl',
                        //controllerAs: 'accountCtrl'
                    })
                .when(
                    '/login', 
                    {
                        templateUrl: 'views/login.html', 
                        controller: 'LoginCtrl', 
                        controllerAs: 'loginCtrl'
                    })
                .when(
                    '/device', 
                    {
                        templateUrl: 'views/device.html', 
                        controller: 'DeviceCtrl',
                        controllerAs: 'deviceCtrl'
                    })
                .when(
                '/device',
                {
                    templateUrl: 'views/device.html',
                    controller: 'AllDeviceCtrl',
                    controllerAs: 'allDeviceCtrl'
                })
                .when(
                '/devicePurchasePay',
                {
                    templateUrl: 'views/devicePurchasePay.html',
                    controller: 'DevicePurchasePayCtrl',
                    controllerAs: 'devicePurchasePayCtrl'
                })
                .when(
                '/manageMerchant',//新增商户管理模块
                {   templateUrl: 'views/manageMerchant.html',
                    controller: 'ManageMerchantCtrl',
                    controllerAs: 'manageMerchantCtrl'
                 })
                .when(
                '/checkStore',//新增门店审核页面
                {   templateUrl: 'views/checkStore.html',
                    controller: 'CheckStoreCtrl',
                    controllerAs: 'checkStoreCtrl'
                })
                .when(
                '/extendedProvider',
                {
                    templateUrl: 'views/extendedProvider.html',
                    controller: 'ExtendedProviderCtrl',
                    controllerAs: 'extendedProviderCtrl'
                 })
                .when(
                '/juniorProvider',//新增下级服务商
                {
                    templateUrl:'views/juniorProvider.html',
                    controller:'JuniorProviderCtrl',
                    controllerAs:'juniorProviderCtrl'
                  })
                .when(
                '/juniorProviderDetail',//新增下级服务商详情
                {
                    templateUrl:'views/section/juniorProviderDetail.html',
                    controller:'JuniorProviderDetailCtrl',
                    controllerAs:'juniorProviderDetailCtrl'
                })
                .when(
                    '/account', 
                    {
                        templateUrl: 'views/account.html', 
                        controller: 'AccountCtrl', 
                        controllerAs: 'accountCtrl'
                    })
                .when(
                    '/dealer',
                    {
                        templateUrl: 'views/dealer.html',
                        controller: 'DealerCtrl',
                        controllerAs: 'dealerCtrl'
                    })
                .when(
                    '/accountPurchase', 
                    {
                        templateUrl: 'views/accountPurchase.html', 
                        controller: 'AccountPurchaseCtrl', 
                        controllerAs: 'accountPurchaseCtrl'
                    })
                .when(
                '/devicePurchase',
                {
                    templateUrl: 'views/devicePurchase.html',
                    controller: 'DevicePurchaseCtrl',
                    controllerAs: 'devicePurchaseCtrl'
                })
                .when(
                '/deviceStock',
                {
                    templateUrl: 'views/deviceStock.html',
                    controller: 'DeviceStockCtrl',
                    controllerAs: 'deviceStockCtrl'
                })
                .when(
                    '/accountStock', 
                    {
                        templateUrl: 'views/accountStock.html', 
                        controller: 'AccountStockCtrl', 
                        controllerAs: 'accountStockCtrl'
                    })
                .when(
                    '/appendAd', 
                    {
                        templateUrl: 'views/appendAd.html', 
                        controller: 'AppendAdCtrl', 
                        controllerAs: 'appendAdCtrl'
                    })
                .when(
                    '/editAd',
                    {
                        templateUrl: 'views/editAd.html',
                        controller: 'EditAdCtrl',
                        controllerAs: 'EditAdCtrl'
                    })
                .when(
                '/listAd',
                    {
                        templateUrl: 'views/listAd.html', 
                        controller: 'ListAdCtrl', 
                        controllerAs: 'listAdCtrl'
                    })
                .when(
                '/editRedAd',
                {
                    templateUrl: 'views/editRedAd.html',
                    controller: 'EditRedAdCtrl',
                    controllerAs: 'editRedAdCtrl'
                })
                .when(
                '/surprisedRedPacketDetail',
                {
                    templateUrl: 'views/section/surprisedRedPacketDetail.html',
                    controller: 'SurprisedRedPacketDetailCtrl',
                    controllerAs: 'surprisedRedPacketDetailCtrl'
                })
                .when(
                '/appendRedAd',
                {
                    templateUrl: 'views/appendRedAd.html',
                    controller: 'AppendRedAdCtrl',
                    controllerAs: 'appendRedAdCtrl'
                })
                .when(
                     '/listRedAd',
                {
                    templateUrl: 'views/listRedAd.html',
                    controller: 'ListRedAdCtrl',
                    controllerAs: 'listRedAdCtrl'
                })
                .when(          //新加的
                '/startPageListAd',
                {
                    templateUrl: 'views/startPageListAd.html',
                    controller: 'StartPageListCtrl',
                    controllerAs: 'startPageListCtrl'
                })
                .when(      //新加的
                '/appendPageListAd',
                {
                    templateUrl: 'views/appendPageListAd.html',
                    controller: 'AppendPageListAdCtrl',
                    controllerAs: 'appendPageListAdCtrl'
                })
                .when(      //新加的
                '/editStartPageAd',
                {
                    templateUrl: 'views/editStartPageAd.html',
                    controller: 'EditStartPageAdCtrl',
                    controllerAs: 'EditStartPageAdCtrl'
                })
                .when(
                '/editRed',
                {
                    templateUrl: 'views/editRed.html',
                    controller: 'EditRedCtrl',
                    controllerAs: 'editRedCtrl'
                })
                .when(      //吸粉红包
                '/attractFansRedPacketDetail',
                {
                    templateUrl: 'views/section/attractFansRedPacketDetail.html',
                    controller: 'AttractFansRedPacketDetailCtrl',
                    controllerAs: 'attractFansRedPacketDetailCtrl'
                })
                .when(      //吸粉红包
                '/attractFansRedPacket',
                {
                    templateUrl: 'views/attractFansRedPacket.html',
                    controller: 'AttractFansRedPacketCtrl',
                    controllerAs: 'attractFansRedPacketCtrl'
                })
                .when(      //授权
                '/authorization',
                {
                    templateUrl: 'views/authorization.html',
                    controller: 'AuthorizationCtrl',
                    controllerAs: 'authorizationCtrl'
                })
                .when(      //创建吸粉红包
                '/createRedPacket',
                {
                    templateUrl: 'views/createRedPacket.html',
                    controller: 'CreateRedPacketCtrl',
                    controllerAs: 'createRedPacketCtrl'
                })
                .when(      //卡劵列表
                '/listCoupon',
                {
                    templateUrl: 'views/listCoupon.html',
                    controller: 'ListCouponCtrl',
                    controllerAs: 'listCouponCtrl'
                })
                .when(      //创建卡劵
                '/appendCoupon',
                {
                    templateUrl: 'views/appendCoupon.html',
                    controller: 'AppendCouponCtrl',
                    controllerAs: 'appendCouponCtrl'
                })
                .when(      //编辑卡劵
                '/editCoupon',
                {
                    templateUrl: 'views/editCoupon.html',
                    controller: 'EditCouponCtrl',
                    controllerAs: 'editCouponCtrl'
                })
                .when(
                    '/allDevice', 
                    {
                        templateUrl: 'views/section/allDevice.html', 
                        controller: 'AllDeviceCtrl', 
                        controllerAs: 'allDeviceCtrl'
                    })
                .when(
                    '/deviceDetail', 
                    {
                        templateUrl: 'views/section/deviceDetail.html', 
                        controller: 'DeviceDetailCtrl', 
                        controllerAs: 'deviceDetailCtrl'
                    })
                .when(
                    '/fortune', 
                    {
                        templateUrl: 'views/fortune.html', 
                        controller: 'FortuneCtrl', 
                        controllerAs: 'fortuneCtrl'
                    })
                    .when(
                    '/oldIncome',
                    {
                        templateUrl: 'views/oldIncome.html',
                        controller: 'OldIncomeCtrl',
                        controllerAs: 'oldIncomeCtrl'
                    })
                .when(
                    '/income', 
                    {
                        templateUrl: 'views/income.html', 
                        controller: 'IncomeCtrl', 
                        controllerAs: 'incomeCtrl'
                    })
                .when(
                    '/incomeAdDetail', 
                    {
                        templateUrl: 'views/section/incomeAdDetail.html', 
                        controller: 'IncomeAdDetailCtrl', 
                        controllerAs: 'incomeAdDetailCtrl'
                    })
                .when(
                '/incomerecordDetail',
                {
                    templateUrl: 'views/section/incomerecordDetail.html',
                    controller: 'IncomerecordDetailCtrl',
                    controllerAs: 'incomerecordDetailCtrl'
                })
                .when(
                '/incomeSharingDetail',
                {
                    templateUrl: 'views/section/incomeSharingDetail.html',
                    controller: 'IncomeSharingDetailCtrl',
                    controllerAs: 'incomeSharingDetailCtrl'
                })
                .when(
                '/incomeStoreAdDepositDetail',
                {
                    templateUrl: 'views/section/incomeStoreAdDepositDetail.html',
                    controller: 'IncomeStoreAdDepositDetailCtrl',
                    controllerAs: 'incomeStoreAdDepositDetailCtrl'
                })
                .when(
                    '/incomeDealerDetail',
                    {
                        templateUrl: 'views/section/incomeDealerDetail.html',
                        controller: 'IncomeDealerDetailCtrl',
                        controllerAs: 'incomeDealerDetailCtrl'
                    })
                .when(
                '/districtRedDetail',
                {
                    templateUrl: 'views/section/districtRedDetail.html',
                    controller: 'DistrictRedDetailCtrl',
                    controllerAs: 'districtRedDetailCtrl'
                })
                .when(
                '/juniorPurchaseDetail',
                {
                    templateUrl: 'views/section/juniorPurchaseDetail.html',
                    controller: 'JuniorPurchaseDetailCtrl',
                    controllerAs: 'juniorPurchaseDetailCtrl'
                })
                .when(
                    '/balance', 
                    {
                        templateUrl: 'views/balance.html', 
                        controller: 'BalanceCtrl', 
                        controllerAs: 'balanceCtrl'
                    })
                .when(
                    '/fortuneDetail', 
                    {
                        templateUrl: 'views/section/fortuneDetail.html', 
                        controller: 'FortuneDetailCtrl', 
                        controllerAs: 'fortuneDetailCtrl'
                    })      
                .when(
                    '/deviceBind', 
                    {
                        templateUrl: 'views/section/deviceBind.html', 
                        controller:'DeviceBindCtrl', 
                        controllerAs:'deviceBindCtrl'
                    })
                .when(
                    '/myaccount', 
                    {
                        templateUrl: 'views/myaccount.html', 
                        controller:'MyaccountCtrl', 
                        controllerAs:'myaccountCtrl'
                    })
                .when(
                '/reported',
                {
                    templateUrl: 'views/reported.html',
                    controller:'ReportedCtrl',
                    controllerAs:'reportedCtrl'
                })
                .when(
                '/myreported',
                {
                    templateUrl: 'views/myreported.html',
                    controller:'MyreportedCtrl',
                    controllerAs:'myreportedCtrl'
                })
                .otherwise(
                    {
                        templateUrl: 'views/404.html', 
                        controller: 'MainCtrl', 
                        controllerAs: 'mainCtrl'
                    });
            }]);
    angular
    .module('center')
    .run(['$rootScope', function ($rootScope) {           
        $rootScope.CONFIG = {
            baseUrl:"http://192.168.1.219:8080"
        };

    }]);
})();
