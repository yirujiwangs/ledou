(function () {
    'use strict';

    angular
    .module('center')
    .factory('refundService', refundService);

    refundService.$inject = ['$q','RefundDao','utilService'];

    function refundService($q,RefundDao,utilService) {
        return{
        	listRefund:function(page){
        		return RefundDao.listRefund(page);
        	},
            process:function(param){
                return RefundDao.process(param);
            },
            refundDetail:function(param){
                return RefundDao.refundDetail(param);
            },
            getStoreDepositDetail:function(param){
                return RefundDao.getStoreDepositDetail(param);
            },
            storeFinanceStatistics:function(){
                return RefundDao.storeFinanceStatistics();
            },
            getRechargeTips:function(){
                return RefundDao.getRechargeTips()
            },
            getWithdrawnItems:function(){
                return RefundDao.getWithdrawnItems()
            },
            search:function(param){
                return RefundDao.search(param)
            },
            getFlowingDetail:function(id){
                var form={account:id}
                return RefundDao.getFlowingDetail(form)
            },
            rechargeConfirm:function(form){
                return RefundDao.rechargeConfirm(form)
            },
            withdrawnConfirm:function(id){
                return RefundDao.withdrawnConfirm(id)
            },
            FinanceReject:function(form){
                return RefundDao.FinanceReject(form)
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
                        RefundDao.detailSearch(date)
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
