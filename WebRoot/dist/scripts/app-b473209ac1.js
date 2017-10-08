(function () {
    'use strict';

    angular
        .module('center', ['ngRoute','ui.bootstrap','ui.bootstrap.datetimepicker']);

    angular
        .module('center')
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                // .when('/', {templateUrl: 'views/main.html', controller: 'MainCtrl', controllerAs: 'mainCtrl'})
                .when('/', {templateUrl: 'views/account.html', controller: 'AccountCtrl', controllerAs: 'accountCtrl'})
                .when('/login', {templateUrl: 'views/login.html', controller: 'LoginCtrl', controllerAs: 'loginCtrl'})
                .when('/device', {templateUrl: 'views/device.html', controller: 'DeviceCtrl', controllerAs: 'deviceCtrl'})
                .when('/account', {templateUrl: 'views/account.html', controller: 'AccountCtrl', controllerAs: 'accountCtrl'})
                .when('/accountPurchase', {templateUrl: 'views/accountPurchase.html', controller: 'AccountPurchaseCtrl', controllerAs: 'accountPurchaseCtrl'})
                .when('/accountStock', {templateUrl: 'views/accountStock.html', controller: 'AccountStockCtrl', controllerAs: 'accountStockCtrl'})
                .when('/deviceDetail', {templateUrl: 'views/section/deviceDetail.html', controller: 'DeviceDetailCtrl', controllerAs: 'deviceDetailCtrl'})
                .when('/fortune', {templateUrl: 'views/fortune.html', controller: 'FortuneCtrl', controllerAs: 'fortuneCtrl'})
                .when('/income', {templateUrl: 'views/income.html', controller: 'IncomeCtrl', controllerAs: 'incomeCtrl'})
                .when('/balance', {templateUrl: 'views/balance.html', controller: 'BalanceCtrl', controllerAs: 'balanceCtrl'})
                // .when('/recharge', {templateUrl: 'views/section/fortuneRecharge.html', controller: 'FortuneRechargeCtrl', controllerAs: 'fortuneRechargeCtrl'})
                // .when('/withdrawn', {templateUrl: 'views/section/fortuneWithdrawn.html', controller: 'FortuneWithdrawnCtrl', controllerAs: 'fortuneWithdrawnCtrl'})
                .when('/fortuneDetail', {templateUrl: 'views/section/fortuneDetail.html', controller: 'FortuneDetailCtrl', controllerAs: 'fortuneDetailCtrl'})
                // .when('/history', {templateUrl: 'views/history.html', controller:'HistoryCtrl', controllerAs:'historyCtrl'})
                .when('/deviceBind', {templateUrl: 'views/section/deviceBind.html', controller:'DeviceBindCtrl', controllerAs:'deviceBindCtrl'})
                .when('/myaccount', {templateUrl: 'views/myaccount.html', controller:'MyaccountCtrl', controllerAs:'myaccountCtrl'})
                .otherwise({templateUrl: 'views/404.html', controller: 'MainCtrl', controllerAs: 'mainCtrl'});
        }]);
    angular
        .module('center')
        .run(['$rootScope', function ($rootScope) {           
            $rootScope.CONFIG = {
                baseUrl:"http://192.168.1.219:8080"
            };

        }]);
    
})();
