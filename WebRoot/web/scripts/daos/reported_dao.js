/**
 * Created by admin on 2017/4/15.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('reportedDao', reportedDao);

    reportedDao.$inject = ['baseHttp'];

    function reportedDao(baseHttp) {
        return{
            listTotal:function(page){
                return baseHttp({
                    method: 'POST',
                    url:"/proxy/area/myReports?startPage="+page.startPage+"&pageSize="+page.pageSize,
                    data: page
                });
            },

        }
    }
})();
