(function () {
    'use strict';

    angular
    .module('center')
    .factory('deviceService', deviceService);

    deviceService.$inject = ['$q','DeviceDao'];

    function deviceService($q,DeviceDao) {
        return{
        	listDevices:function(page){
        		return DeviceDao.listDevices(page);
        	},
            bancedevicelist:function(page){
                return DeviceDao.bancedevicelist(page);
            },
            addDevice:function(form){
                return DeviceDao.addDevice(form);
            },
            undistributed:function(page){
                return DeviceDao.undistributed(page);
            },
            updateRemark:function(param){
                return DeviceDao.updateRemark(param);
            },
            deviceUpdate:function(id,remark){
                var form={account:id,remark:remark};
                return DeviceDao.deviceUpdate(form);
            },
            search:function(param){
                return DeviceDao.search(param);
            },
            getDeviceDetail:function(id){
                var form={phoneNum:id}
                return DeviceDao.getDeviceDetail(form);
            },
            getDeviceInfoDetail:function(param){
                return DeviceDao.getDeviceInfoDetail(param);
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
            getUnbind:function(param){
                return DeviceDao.getUnbind(param);
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
            },
            getDeviceDeposit: function(param){
                return DeviceDao.getDeviceDeposit(param);
            },
            getDeviceStoreDS: function(param){
                return DeviceDao.getDeviceStoreDS(param);
            }
            //设备政策设置
            ,updateDeviceSetting:function(param){
                return DeviceDao.updateDeviceSetting(param);
            }
        }
    }
})();
