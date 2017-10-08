/**
 * Created by admin on 2017/4/17.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('DeviceStockService', DeviceStockService);

    DeviceStockService.$inject = ['$q','DeviceStockDao','utilService'];

    function DeviceStockService($q,DeviceStockDao,utilService) {
        return{
            //listFortune:function(page){
            //    return DeviceStockDao.listFortune(page);   //分页请求
            //},
            listTotal: function (page) {////列表里面的数值
                return DeviceStockDao.listTotal(page);
            },
            logistics : function (status) {//弹出框里面的信息
                return DeviceStockDao.logistics(status);
            }

        }
    }
})();
