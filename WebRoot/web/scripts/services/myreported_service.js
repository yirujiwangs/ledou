/**
 * Created by admin on 2017/4/18.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('myreportedService', myreportedService);

    myreportedService.$inject = ['$q','myreportedDao'];

    function myreportedService($q,myreportedDao) {
        return {
            listTotal: function (page) {      //表单
                return myreportedDao.listTotal(page);
            },
            myReport: function (param) { //报备弹出框中的提交
                return myreportedDao.myReport(param);
            },
            //listfirst: function (rid) {
            //    return myreportedDao.listfirst(rid);   //查询
            //},
            //jump: function (page) {      //表单
            //    return myreportedDao.jump(page);
            //},
        };
    }
})();

