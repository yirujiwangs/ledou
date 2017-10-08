(function () {
    'use strict';

    angular
        .module('center')
        .factory('DeviceService', DeviceService);

    DeviceService.$inject = ['$q','DeviceDao'];

    function DeviceService($q,DeviceDao) {
        return{
        	listDevices:function(page){
        		return DeviceDao.listDevices(page);
        	},
            addDevice:function(form){
                return DeviceDao.addDevice(form);
            },
            deviceUpdate:function(id,remark){
                var form={account:id,remark:remark};
                return DeviceDao.deviceUpdate(form);
            },
            getDeviceDetail:function(id){
                var form={phoneNum:id}
                return DeviceDao.getDeviceDetail(form);
            },
            deviceEdit:function(account,id,type,remark){
                var form={account:account,ibeaconId:id,type:type,remark:remark};
                return DeviceDao.deviceEdit(form);
            },
            delDevice:function(form){
                return DeviceDao.delDevice(form);
            },
            deviceRev:function(info){
                return DeviceDao.deviceRev(info);
            },
            searchById:function(form){
                return DeviceDao.searchById(form);
            },
            import:function(){
                return DeviceDao.import();
            	
            },
            undistributed:function(page){
                return DeviceDao.undistributed(page);
            },
            activate:function(id){
                var form={"ibeaconId":id}
                return DeviceDao.activate(form);

            },
            addToWeixin:function(form){
                return DeviceDao.addToWeixin(form);
            },
            preAdd:function(){
                return DeviceDao.preAdd();
            },
            // 未绑定设备搜索
            searchUnbind:function(keyword){
                return DeviceDao.searchUnbind(keyword);
            },
            deviceAccountSearch:function(form){
                return DeviceDao.deviceAccountSearch(form);
            },
            detailSearch:function(form){
                return DeviceDao.detailSearch(form);
            },
            removeBind: function(serialNum){
                return DeviceDao.removeBind(serialNum);
            },
            // 设备管理详情数据指标
            storeDS: function(phone){
                return DeviceDao.storeDS(phone);
            }
        }
    }
})();
