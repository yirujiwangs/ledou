(function () {
    'use strict';

    angular
        .module('center')
        .factory('dealerService', dealerService);

    dealerService.$inject = ['$q','dealerDao'];

    function dealerService($q,dealerDao) {
        return {
            listTotal: function (page) {
                return dealerDao.listTotal(page);
            },
           listDevice: function () {
                return dealerDao.listDevice();
            },
            addDealer: function (form) {
                return dealerDao.addDealer(form);
            },
            distributeDevice: function (form) {
                return dealerDao.distributeDevice(form);
            },
            distributeMerchant: function (form) {
                return dealerDao.distributeMerchant(form);
            },
            dealerDetail: function (form) {
                return dealerDao.dealerDetail(form);
            },
            status: function (form) {
            return dealerDao.status(form);
        }
        };
    }
})();
