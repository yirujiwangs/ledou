(function () {
    'use strict';

    angular
        .module('center')
        .factory('DeviceService', DeviceService);

    DeviceService.$inject = ['$q','DeviceDao'];

    function  DeviceService($q,DeviceDao) {
        return{
            /**
             * 获取设备分成设置
             */
            settingInfo:function(param){
                return DeviceDao.settingInfo(param);
            },
            listDevices: function (page) {
                return DeviceDao.listDevices(page);
            },
            deviceIndex: function () {
                return DeviceDao.deviceIndex();
            },
            addDevice: function (form) {
                return DeviceDao.addDevice(form);
            },
            deviceUpdate: function (id,remark) {
                var form={account:id,remark:remark};
                return DeviceDao.deviceUpdate(form);
            },
            //自定义设置提交
            settingSubmit: function (param) {
                return DeviceDao.settingSubmit(param);
            },
            getDeviceDetail: function (phone) {
                return DeviceDao.getDeviceDetail(phone);
            },
            deviceEdit: function (account,id,type,remark) {
                var form={account:account,ibeaconId:id,type:type,remark:remark};
                return DeviceDao.deviceEdit(form);
            },
            delDevice: function (form) {
                return DeviceDao.delDevice(form);
            },
            deviceRev: function (info) {
                return DeviceDao.deviceRev(info);
            },
            searchById: function (form) {
                return DeviceDao.searchById(form);
            },
            import: function () {
                return DeviceDao.import();
            },
            undistributed: function (page) {
                return DeviceDao.undistributed(page);
            },
            activate: function (id) {
                var form={ibeaconId:id};
                return DeviceDao.activate(form);

            },
            addToWeixin: function (form) {
                return DeviceDao.addToWeixin(form);
            },
            preAdd: function () {
                return DeviceDao.preAdd();
            },
            // 未绑定设备搜索
            searchUnbind: function (keyword) {
                return DeviceDao.searchUnbind(keyword);
            },
            deviceAccountSearch: function (form) {
                return DeviceDao.deviceAccountSearch(form);
            },
            detailSearch: function (form) {
                return DeviceDao.detailSearch(form);
            },
            removeBind: function (serialNum) {
                return DeviceDao.removeBind(serialNum);
            },
            // 设备管理详情数据指标
            storeDS: function (phone) {
                return DeviceDao.storeDS(phone);
            },
            /***********/
            deviceInfo: function (param) {
                return DeviceDao.deviceInfo(param);
            },
            deviveGroups: function () {
                return DeviceDao.deviveGroups();
            },
            stateSearch: function (param) {
                return DeviceDao.stateSearch(param);
            },
            search: function (param) {

                return DeviceDao.search(param);
            },
            createGroup: function (groupName) {
                return DeviceDao.createGroup(groupName);
            },
            delGroup: function (groupId) {
                return DeviceDao.delGroup(groupId);
            },
            updateGroup: function (newGroup){
                return DeviceDao.updateGroup(newGroup);
            },
            allDeviceRev: function(form){
                return DeviceDao.allDeviceRev(form);
            },
            accountDeviceIndex: function(phone){
                return DeviceDao.accountDeviceIndex(phone);
            },
            detailStateSearch: function(param){
                return DeviceDao.detailStateSearch(param);
            }
        };
    }
})();
