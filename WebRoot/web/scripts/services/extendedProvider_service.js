/**
 * Created by admin on 2017/4/17.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('ExtendedProviderService', ExtendedProviderService);

    ExtendedProviderService.$inject = ['$q','ExtendedProviderDao','utilService'];

    function ExtendedProviderService($q,ExtendedProviderDao,utilService) {
        return{
            statistics:function(){
                return ExtendedProviderDao.statistics(); //关键指标里面的数字
            },
            extendedProvidertList:function(page){
                return ExtendedProviderDao.extendedProvidertList(page); //列表里面的数值
            },
            //listFortune:function(page){
            //    return ExtendedProviderDao.listFortune(page);   //分页请求
            //},
        }
    }
})();
