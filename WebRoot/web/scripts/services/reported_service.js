/**
 * Created by admin on 2017/4/15.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('reportedService', reportedService);

    reportedService.$inject = ['$q','reportedDao'];

    function reportedService($q,reportedDao) {
        return {
            listTotal: function (page) {
                return reportedDao.listTotal(page);
            },
        };
    }
})();


