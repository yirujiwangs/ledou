
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
              url:'/supervisor/deviceManage.do',
              data: page
            });
          },
          bancedevicelist:function(page){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/deviceManage.do',
              data: page
            });
          },
          undistributed:function(page){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/unbindDevManage.do',
              data: page
            });
          },
          search:function(param){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/deviceManageSearch.do',
              data: param
            });
          },
          updateRemark:function(param){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/updateUnbindDevRemark.do',
              data: param
            });
          },
          addDevice:function(form){
            return baseHttp({
              method: 'POST',
              url:' /supervisor/binding.do',
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
              url: '/supervisor/detail.do',
              data: form
            });
          },
          getDeviceInfoDetail:function(param){
            return baseHttp({
              method: 'POST',
              url: '/device/index.do',
              data: param
            })
          },
          deviceEdit:function(form){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/update.do',
              data:form
            });
          },
          delDevice:function(form){
            return baseHttp({
              method: 'POST',
              url: '/supervisor/delete.do',
              data:form
            });
          },
          deviceRev:function(info){
            return baseHttp({
              method: 'POST',
              url: '/supervisor/revcomment.do',
              data: info
            });
          },
          searchById:function(form){
            return baseHttp({
              method: 'POST',
              url: '/supervisor/search.do',
              data:form
            });
          },
          import:function(){
            return baseHttp({
              method: 'GET',
              url:'/supervisor/excel.do'
            });
          },
          activate:function(form){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/activate.do',
              data:form
            });
          },
          addToWeixin:function(form){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/add.do',
              data:form
            });
          },
          preAdd:function(){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/preAdd.do'
            });
          },
          getUnbind:function(param){
            return baseHttp({
              method: 'POST',
              url: '/device/showunbind.do',
              data: param
            })
          },
          searchUnbind:function(keyword){
            return baseHttp({
              method: 'POST',
              url: '/supervisor/searchUnbind.do',
              data: keyword
            })
          },
          deviceAccountSearch:function(form){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/searchAccount.do',
              data:form
            });
          },
          detailSearch:function(form){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/search.do',
              data: form
            });
          },
          removeBind:function(serialNum){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/unbinding.do',
              data:serialNum
            });
          },
          storeDS:function(phone){
            return baseHttp({
              method: 'POST',
              url:'/supervisor/storeDS.do',
              data: phone
            })
          },
          getDeviceDeposit:function(param){
            return baseHttp({
              method: 'POST',
              url: '/device/detail.do',
              data: param
            })
          },
          getDeviceStoreDS:function(param){
            return baseHttp({
              method: 'POST',
              url: '/device/storeDS.do',
              data: param
            })
          },
          updateDeviceSetting:function(param){
            return baseHttp({
              method: 'POST',
              url: '/device/devicePolicy.do',
              data: param
            })
          }
        } 
      }
    })();
