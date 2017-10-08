(function () {
    'use strict';

    angular
        .module('center')
        .factory('AttractFansRedPacketService',AttractFansRedPacketService);

    AttractFansRedPacketService.$inject = ['$q','AttractFansRedPacketDao'];

    function AttractFansRedPacketService($q,AttractFansRedPacketServiceDao) {
        return {
            attractFansRedPacketList: function (param) {
                return AttractFansRedPacketServiceDao.attractFansRedPacketList(param)
            },
            redPacketDetail: function (param) {
                return AttractFansRedPacketServiceDao.redPacketDetail(param)
            },
            updateRedPacketStatus: function (param) {
                return AttractFansRedPacketServiceDao.updateRedPacketStatus(param)
            },
            authorization: function (param) {
                return AttractFansRedPacketServiceDao.authorization(param)
            },
            getAccountsInfo:function(){
                return AttractFansRedPacketServiceDao.getAccountsInfo();
            },
            getGroupInfo:function(){
                return AttractFansRedPacketServiceDao.getGroupInfo();
            },
            feeTemplate:function(param){
                return AttractFansRedPacketServiceDao.feeTemplate(param);
            },
            authorize:function(param){
                return AttractFansRedPacketServiceDao.authorize(param);
            },
            userlist:function(param){
                return AttractFansRedPacketServiceDao.userlist(param);
            }
        }
    }
})();
