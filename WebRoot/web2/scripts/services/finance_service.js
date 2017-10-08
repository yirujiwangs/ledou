(function () {
    'use strict';

    angular
        .module('center')
        .factory('financeService', financeService);

    financeService.$inject = ['$q','FinanceDao','utilService'];

    function financeService($q,FinanceDao,utilService) {
        return{
            ///清算代理商上月收入
            clearIncome:function(page){
                return FinanceDao.clearIncome(page);
            },
        	listFinance:function(page){
        		return FinanceDao.listFinance(page);
        	},
            listFinanceSearch:function(param){
                return FinanceDao.listFinanceSearch(param);
            },
            getStoreDepositDetail:function(param){
                return FinanceDao.getStoreDepositDetail(param);
            },
            storeFinanceStatistics:function(){
                return FinanceDao.storeFinanceStatistics();
            },
            getRechargeTips:function(){
                return FinanceDao.getRechargeTips()
            },
            getWithdrawnItems:function(){
                return FinanceDao.getWithdrawnItems()
            },
            search:function(id){
                return FinanceDao.search(id)
            },
            getFlowingDetail:function(id){
                var form={account:id}
                return FinanceDao.getFlowingDetail(form)
            },
            rechargeConfirm:function(form){
                return FinanceDao.rechargeConfirm(form)
            },
            withdrawnConfirm:function(id){
                return FinanceDao.withdrawnConfirm(id)
            },
            FinanceReject:function(form){
                return FinanceDao.FinanceReject(form)
            },
            getFinanceDetail: function(param){
                return FinanceDao.getFinanceDetail(param);
            },
            getFinanceDetailSta: function(param){
                return FinanceDao.getFinanceDetailSta(param);
            },
            getFinanceStoreDetail: function(param){
                return FinanceDao.getFinanceStoreDetail(param);
            },
            getFinanceIncomeDetail: function(param){
                return FinanceDao.getFinanceIncomeDetail(param);
            },
            getFinanceIncomeDetailSta: function(param){
                return FinanceDao.getFinanceIncomeDetailSta(param);
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
                        FinanceDao.detailSearch(date)
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
