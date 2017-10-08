/**
 * Created by admin on 2017/4/17.
 */

(function () {
    'use strict';

    angular
        .module('center')
        .factory('ExtendedProviderDao', ExtendedProviderDao);

    ExtendedProviderDao.$inject = ['baseHttp'];

    function ExtendedProviderDao(baseHttp) {
        return{
            statistics:function(){   //关键指标里面的数字
                return baseHttp({
                    method: 'GET',
                    url: '/dealer/device/statistics'

                })
            },
            extendedProvidertList:function(page){   //列表里面的数值
                return baseHttp({
                    method: 'POST',
                    url:'/dealer/device/list',
                    data: page
                });
            },
            //listFortune:function(page){        //分页请求
            //    return baseHttp({
            //        method: 'POST',
            //        url:'/finance/storeDeposits.do',
            //        data: page
            //    });
            //},
            //listTotal:function(page){
            //    return baseHttp({
            //        method: 'POST',
            //        url:'/account/index.do',
            //        data: page
            //    });
            //},


        }
    }
})();
