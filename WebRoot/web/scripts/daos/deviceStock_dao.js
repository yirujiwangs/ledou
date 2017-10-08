/**
 * Created by admin on 2017/4/17.
 */

(function () {
    'use strict';

    angular
        .module('center')
        .factory('DeviceStockDao', DeviceStockDao);

    DeviceStockDao.$inject = ['baseHttp'];

    function DeviceStockDao(baseHttp) {
        return{
             listTotal:function(page){
                return baseHttp({
                    method: 'POST',
                    url:'/device/order/record.do',
                    data: page
                });
            },
            logistics:function(status){
                return baseHttp({
                    method: 'GET',
                    url:'/device/order/delivery.do?dNo='+status.dNo,
                    data: status
                });
            },


        }
    }
})();
