(function () {
    'use strict';

    angular
    .module('center')
    .factory('accountService', accountService);

    accountService.$inject = ['$q','accountDao'];

    function accountService($q,accountDao) {
        return {
            operatorAccounts: function (params) {
                return accountDao.operatorAccounts(params);
            },
            update: function (form) {
                return accountDao.update(form);
            },
            subUpdate: function (form) {
                return accountDao.subUpdate(form)
            },
            adduser: function (form) {
                return accountDao.adduser(form);
            },
            addSubUser: function (form) {
                return accountDao.addSubUser(form);
            },
            status: function (sta) {
                return accountDao.status(sta);
            },
            subStatus: function (sta) {
                return accountDao.subStatus(sta);
            },
            search: function (form) {
                return accountDao.search(form);
            },
            stateList: function (form) {
                return accountDao.stateList(form);
            },
            getAccountDetail: function (param){
                return accountDao.getAccountDetail(param);
            }
        };
    }
})();
