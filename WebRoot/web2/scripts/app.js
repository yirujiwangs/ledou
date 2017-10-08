(function () {
    'use strict';

    angular
    .module('center', ['ngRoute','ui.bootstrap','ui.bootstrap.datetimepicker']);

    angular
    .module('center')
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
        .when('/', 
        {
            templateUrl: 'views/manage.html', 
            controller: 'ManageCtrl', 
            controllerAs: 'manageCtrl'
        })
        .when('/login', 
        {
            templateUrl: 'views/login.html', 
            controller: 'LoginCtrl', 
            controllerAs: 'loginCtrl'
        })
        .when('/manage', 
        {
            templateUrl: 'views/manage.html', 
            controller: 'ManageCtrl', 
            controllerAs: 'manageCtrl'
        })
        .when('/account', 
        {
            templateUrl: 'views/account.html', 
            controller: 'AccountCtrl', 
            controllerAs: 'accountCtrl'
        })
        .when('/report',
        {
            templateUrl: 'views/report.html',
            controller: 'ReportCtrl',
            controllerAs: 'reportCtrl'
        })
        .when('/accountDetail', 
        {
            templateUrl: 'views/section/accountDetail.html',
            controller: 'AccountDetailCtrl', 
            controllerAs: 'accountDetailCtrl'
        })
        .when('/accountPurchase', 
        {
            templateUrl: 'views/accountPurchase.html', 
            controller: 'AccountPurchaseCtrl', 
            controllerAs: 'accountPurchaseCtrl'
        })
        .when('/device', 
        {
            templateUrl: 'views/device.html', 
            controller: 'DeviceCtrl', 
            controllerAs: 'deviceCtrl'
        })
        .when('/deviceStock',      //设备订单管理
        {
            templateUrl: 'views/deviceStock.html',
            controller: 'DeviceStockCtrl',
            controllerAs: 'deviceStockCtrl'
        })
        .when('/deviceUnbind', 
        {
            templateUrl: 'views/section/deviceUnbind.html', 
            controller: 'DeviceUnbindCtrl', 
            controllerAs: 'deviceUnbindCtrl'
        })
        .when('/deviceDetail', 
        {
            templateUrl: 'views/section/deviceDetail.html', 
            controller: 'DeviceDetailCtrl', 
            controllerAs: 'deviceDetailCtrl'
        })
        .when('/deviceShopDetail', 
        {
            templateUrl: 'views/section/deviceShopDetail.html', 
            controller: 'DeviceShopDetailCtrl', 
            controllerAs: 'deviceShopDetailCtrl'
        })
        .when('/deviceUndistrbute', 
        {
            templateUrl: 'views/section/deviceUndistrbute.html', 
            controller: 'UndistrbuteDeviceCtrl', 
            controllerAs: 'undistrbuteDeviceCtrl'
        })
        .when('/finance', 
        {
            templateUrl: 'views/finance.html', 
            controller: 'FinanceCtrl', 
            controllerAs: 'financeCtrl'
        })
        .when('/financeDetail', 
        {
            templateUrl: 'views/section/financeDetail.html', 
            controller: 'FinanceDetailCtrl', 
            controllerAs: 'financeDetailCtrl'
        })
        .when('/financeStoreDetail', 
        {
            templateUrl: 'views/section/financeStoreDetail.html', 
            controller: 'FinanceStoreDetailCtrl', 
            controllerAs: 'financeStoreDetailCtrl'
        })
        .when('/financeIncomeDetail', 
        {
            templateUrl: 'views/section/financeIncomeDetail.html', 
            controller: 'FinanceIncomeDetailCtrl', 
            controllerAs: 'financeIncomeDetailCtrl'
        })
        .when('/refund', 
        {
            templateUrl: 'views/refund.html', 
            controller: 'RefundCtrl', 
            controllerAs: 'refundCtrl'
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
