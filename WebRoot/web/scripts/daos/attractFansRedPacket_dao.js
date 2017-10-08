(function () {
    'use strict';

    angular
        .module('center')
        .factory('AttractFansRedPacketDao', AttractFansRedPacketDao);

    AttractFansRedPacketDao.$inject = ['baseHttp'];

    function AttractFansRedPacketDao(baseHttp) {
        //工厂模式必须有返回值
        return {
            attractFansRedPacketList:function(page){   //列表里面的数值
                return baseHttp({
                    method: 'POST',
                    url:'/red/powderRedList.do',
                    data: page
                });
            },
            redPacketDetail:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/red/powderRedDetail.do',
                    data: param
                });
            },
            updateRedPacketStatus:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/advertise/updateAdvStatus.do',
                    data: param
                });
            },
            authorization:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/red/publicnum.do',
                    data: param
                });
            },
            getAccountsInfo:function(){
                return baseHttp({
                    method: 'POST',
                    url:'/red/publicmsg.do',
                    data: {}
                });
            },
            getGroupInfo:function(){
                return baseHttp({
                    method: 'POST',
                    url:'/red/deviceGroup.do',
                    data: {}
                });
            },
            feeTemplate:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/red/getWithdrawls.do',
                    data:param
                });
            },
            authorize:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/red/authorize.do',
                    data: param
                });
            },
            userlist:function(param){
                return baseHttp({
                    method: 'POST',
                    url:'/red/userlist.do',
                    data: param
                });
            }
        }
    }
})();
