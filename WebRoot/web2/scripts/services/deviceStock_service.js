/**
 * Created by admin on 2017/4/26.
 */
(function () {
    'use strict';

    angular
        .module('center')
        .factory('deviceStockService', deviceStockService);

    deviceStockService.$inject = ['$q','DeviceStockDao','utilService'];

    function deviceStockService($q,DeviceStockDao,utilService) {
        return{
            bancedevicelist:function(page){
                return DeviceStockDao.bancedevicelist(page);
            },
            createDeviceOrder:function(param){
                return DeviceStockDao.createDeviceOrder(param);
            },
            updateLogistic:function(page){
                 return DeviceStockDao.updateLogistic(page);
            },
            logistics : function (status) {//弹出框里面的信息
                return DeviceStockDao.logistics(status);
            },
            listRefund:function(page){
                return DeviceStockDao.listRefund(page);
            },
            process:function(param){
                return DeviceStockDao.process(param);
            },
            refundDetail:function(param){
                return DeviceStockDao.refundDetail(param);
            },
            getStoreDepositDetail:function(param){
                return DeviceStockDao.getStoreDepositDetail(param);
            },
            storeFinanceStatistics:function(){
                return DeviceStockDao.storeFinanceStatistics();
            },
            getRechargeTips:function(){
                return DeviceStockDao.getRechargeTips()
            },
            getWithdrawnItems:function(){
                return DeviceStockDao.getWithdrawnItems()
            },
            search:function(param){
                return DeviceStockDao.search(param)
            },
            getFlowingDetail:function(id){
                var form={account:id}
                return DeviceStockDao.getFlowingDetail(form)
            },
            rechargeConfirm:function(form){
                return DeviceStockDao.rechargeConfirm(form)
            },
            withdrawnConfirm:function(id){
                return DeviceStockDao.withdrawnConfirm(id)
            },
            FinanceReject:function(form){
                return DeviceStockDao.FinanceReject(form)
            },
            detailSearch:function(form){
                var deferred = $q.defer();
                var date={}
                if(form.type=='操作'){
                    date.type='all'
                }
                else{
                    date.type=form.type
                }
                if(form.startTime==''&&form.endTime!=''){
                    date.endTime=form.endTime
                }
                else if(form.startTime!=''&&form.endTime==''){
                    date.startTime=form.startTime
                }
                else if(form.startTime!=''&&form.endTime!=''){
                    date.startTime=form.startTime;
                    date.endTime=form.endTime
                    if(Date.parse(date.endTime)<Date.parse(date.startTime)){
                        deferred.reject('timeErr');
                    }
                    else{
                        DeviceStockDao.detailSearch(date)
                            .then(function (result) {
                                deferred.resolve(result);
                            })
                            .catch(function (e) {
                                deferred.reject(e);
                            });
                    }
                }
                return deferred.promise;
            }
        }
    }
})();

