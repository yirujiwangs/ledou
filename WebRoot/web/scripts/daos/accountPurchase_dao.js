/**
 * Created by admin on 2017/5/8.
 */
/**
 * Created by admin on 2017/4/21.
 */

(function () {
    'use strict';

    angular
        .module('center')
        .factory('AccountPurchaseDao', AccountPurchaseDao);

    AccountPurchaseDao.$inject = ['baseHttp'];

    function AccountPurchaseDao(baseHttp) {
        return {
            buyUrl: function (param) {
                return baseHttp({
                    method: 'POST',
                    url: '/device/order/buy.do',
                    data: param
                });
            }
        }
    }

})();

