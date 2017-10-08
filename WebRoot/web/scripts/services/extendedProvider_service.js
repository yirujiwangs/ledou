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
                return ExtendedProviderDao.statistics(); //�ؼ�ָ�����������
            },
            extendedProvidertList:function(page){
                return ExtendedProviderDao.extendedProvidertList(page); //�б��������ֵ
            },
            //listFortune:function(page){
            //    return ExtendedProviderDao.listFortune(page);   //��ҳ����
            //},
        }
    }
})();
