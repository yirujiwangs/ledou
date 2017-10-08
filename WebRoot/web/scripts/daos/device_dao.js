
(function () {
    'use strict';

    angular
        .module('center')
        .factory('DeviceDao', DeviceDao);
    
    DeviceDao.$inject = ['baseHttp'];

    function DeviceDao(baseHttp) {
        return{
            settingInfo:function(param){
                var code = param.aliveCode;
                return baseHttp({
                    method: 'GET',
                    url:'/device/devicesRebateInfo?code='+code,
                });
            },
            listDevices:function(page){
              return baseHttp({
                    method: 'POST',
                    url:'/device/devicesList.do',
                    data: page
                });
            },
            deviceIndex:function(){
                return baseHttp({
                    method: 'POST',
                    url: '/device/deviceIndex.do'
                })
            },
            settingSubmit:function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/device/deviceRebateSetting',
                    data:param
                })
            },
            addDevice:function(form){   
              return baseHttp({
                    method: 'POST',
                    url:'/device/binding.do',
                    data:form
                });
            },
            deviceUpdate:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/account/update.do',
                    data:form
                });
            },
            getDeviceDetail:function(form){
            	
              return baseHttp({
                    method: 'POST',
                    url:'/device/accountDeviceList.do',
                    data:form
                });
            },
            deviceEdit:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/device/update.do',
                    data:form
                });
            },
            delDevice:function(form){
              return baseHttp({
                    method: 'POST',
                    url: '/device/delete.do',
                    data:form
                });
            },
            deviceRev:function(info){
                return baseHttp({
                    method: 'POST',
                    url: '/device/revcomment.do',
                    data: info
                });
            },
            searchById:function(form){
              return baseHttp({
                    method: 'POST',
                    url: '/device/search.do',
                    data:form
                });
            },
            import:function(){
              return baseHttp({
                    method: 'GET',
                    url:'/device/excel.do'
                });
            },
            undistributed:function(page){
              return baseHttp({
                    method: 'POST',
                    url:'/device/showunbind.do',
                    data: page
                });
            },
            activate:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/device/activate.do',
                    data:form
                });
            },
            addToWeixin:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/device/add.do',
                    data:form
                });
            },
            preAdd:function(){
              return baseHttp({
                    method: 'POST',
                    url:'/device/preAdd.do'
                });
            },
            searchUnbind:function(keyword){
                return baseHttp({
                    method: 'POST',
                    url: '/device/searchUnbind.do',
                    data: keyword
                })
            },
            deviceAccountSearch:function(form){
              return baseHttp({
                    method: 'POST',
                    url:'/device/deviceSearch.do',
                    data:form
                });
            },
            detailSearch:function(form){
                return baseHttp({
                    method: 'POST',
                    url:'/device/search.do',
                    data: form
                });
            },
            removeBind:function(serialNum){
                return baseHttp({
                    method: 'POST',
                    url:'/device/unbinding.do',
                    data:serialNum
                });
            },
            storeDS:function(phone){
                return baseHttp({
                    method: 'POST',
                    url:'/device/storeDS.do',
                    data: phone
                })
            },
            /*************/
            deviceInfo: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/device/proxyDeviceInfoList.do',
                    data: param
                })
            },
            deviveGroups: function(){
                return baseHttp({
                    method: 'POST',
                    url: '/device/proxyDeviceGroups.do'
                })
            },
            stateSearch: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/device/proxyDeviceStatus.do',
                    data: param
                })
            },
            search: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/device/proxyDeviceSearch.do',
                    //url: '/device/proxyCreateGroup.do',
                    data: param
                })
            },
            createGroup: function(groupName){
                return baseHttp({
                    method: 'POST',
                    url: '/device/proxyCreateGroup.do',
                    data: groupName
                })
            },
            delGroup: function(groupId){
                return baseHttp({
                    method: 'POST',
                    url: '/device/proxyDeleteGroup.do',
                    data: groupId
                })
            },
            updateGroup: function(newGroup){
                return baseHttp({
                    method: 'POST',
                    url: '/device/deviceChooseGroup.do',
                    data: newGroup
                })
            },
            allDeviceRev: function(form){
                return baseHttp({
                    method: 'POST',
                    url: '/device/deviceRevRemark.do',
                    data: form
                })
            },
            accountDeviceIndex: function(phone){
                return baseHttp({
                    method: 'POST',
                    url: '/device/accountDeviceIndex.do',
                    data: phone
                })
            },
            detailStateSearch: function(param){
                return baseHttp({
                    method: 'POST',
                    url: '/device/accountDeviceStatus.do',
                    data: param
                })
            }
        } 
    }
})();
