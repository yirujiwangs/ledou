/**
 * Created by admin on 2017/4/21.
 */

(function () {
    'use strict';

    angular
        .module('center')
        .factory('DevicePurchaseDao', DevicePurchaseDao);

    DevicePurchaseDao.$inject = ['baseHttp'];

    function DevicePurchaseDao(baseHttp) {
        return {
            listTotal: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: '/account/index.do',
                    data: param
                });
            },
            buyUrl: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: '/device/order/buy.do',
                    data: param
                });
            },
            bindWxAccount: function (param) { //设备中的支付
                return baseHttp({
                    method: 'POST',
                    url: "/pay/state?no="+param.no,
                })
            },
            devicePrice:function(param){
                return baseHttp({
                    method: 'POST',
                    url: "/device/devicePrice.do",
                    data: param
                })
            }
        }
        }

})();

