(function () {
    'use strict';

    angular
        .module('center')
        .factory('FortuneService', FortuneService);

    FortuneService.$inject = ['$q','FortuneDao','utilService'];

    function FortuneService($q,FortuneDao,utilService) {
        return{
        	listFortune:function(page){
        		return FortuneDao.listFortune(page);
        	},
            getStoreDepositDetail:function(param){
                return FortuneDao.getStoreDepositDetail(param);
            },
            storeFinanceStatistics:function(){
                return FortuneDao.storeFinanceStatistics();
            },
            getRechargeTips:function(){
                return FortuneDao.getRechargeTips()
            },
            getWithdrawnItems:function(){
                return FortuneDao.getWithdrawnItems()
            },
            search:function(id){
                return FortuneDao.search(id)
            },
            getFlowingDetail:function(id){
                var form={account:id}
                return FortuneDao.getFlowingDetail(form)
            },
            rechargeConfirm:function(form){
                return FortuneDao.rechargeConfirm(form)
            },
            withdrawnConfirm:function(id){
                return FortuneDao.withdrawnConfirm(id)
            },
            fortuneReject:function(form){
                return FortuneDao.fortuneReject(form)
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
                        FortuneDao.detailSearch(date)
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
