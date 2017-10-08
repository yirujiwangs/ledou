
(function () {
    'use strict';

    angular
        .module('center')
        .factory('DeviceDao', DeviceDao);
    
    DeviceDao.$inject = ['baseHttp'];

    function DeviceDao(baseHttp) {
        return{
            listDevices:function(page){
              return baseHttp({
                    method: 'POST',
                    url:'/device/index.do',
                    data: page
                });
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
                    url:'/device/detail.do',
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
                    url:'/device/searchAccount.do',
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
            }
        } 
    }
})();
