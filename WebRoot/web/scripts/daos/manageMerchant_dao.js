(function () {
    'use strict';

    angular
        .module('center')
        .factory('ManageMerchantDao', ManageMerchantDao);

    ManageMerchantDao.$inject = ['baseHttp'];

    function ManageMerchantDao(baseHttp) {
        //工厂模式必须有返回值
        return {
            statistics:function(){   //关键指标里面的数字
                return baseHttp({
                    method: 'POST',
                    url:'/manageMerchant/userKPI.do',
                })
            },
            manageMerchantList:function(page){   //列表里面的数值
                return baseHttp({
                    method: 'POST',
                    url:'/manageMerchant/userList.do',
                    data: page
                })
            }
        }
    }
})();
