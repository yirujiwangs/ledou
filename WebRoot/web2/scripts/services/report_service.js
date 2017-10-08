/**
/**
 * Created by admin on 2017/4/27.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('reportService', reportService);

    reportService.$inject = ['$q','reportDao'];

    function reportService($q,reportDao) {
        return {
            reportList: function (params) {
                return reportDao.reportList(params);
            },
            signState: function (newState) {
                return reportDao.signState(newState);
            },



            update: function (form) {
                return reportDao.update(form);
            },
            subUpdate: function (form) {
                return reportDao.subUpdate(form)
            },
            adduser: function (form) {
                return reportDao.adduser(form);
            },
            addSubUser: function (form) {
                return reportDao.addSubUser(form);
            },
            status: function (sta) {
                return reportDao.status(sta);
            },
            subStatus: function (sta) {
                return reportDao.subStatus(sta);
            },
            search: function (form) {
                return reportDao.search(form);
            },
            stateList: function (form) {
                return reportDao.stateList(form);
            },
            getAccountDetail: function (param){
                return reportDao.getAccountDetail(param);
            }
        };
    }
})();
