(function(){
	'use strict';

    angular
    .module('center')
    .controller('ManageCtrl', ManageCtrl);

    ManageCtrl.$inject = [ '$scope', '$rootScope','$location','AlertService','manageService'];

    function ManageCtrl($scope,$rootScope,$location,AlertService,manageService){
    	var vm = this;
      vm.keyNum = [];
      vm.interData = [];
      manageService.listTotal()
      .then(function(res){
              var data = res.data;
              if(!data.deviceCnt)data.deviceCnt=0;
              if(!data.activeDeviceCnt)data.activeDeviceCnt=0;
              if(!data.monthDeviceActiveCnt)data.monthDeviceActiveCnt=0;
              if(!data.wxUserCnt)data.wxUserCnt=0;
              if(!data.shakeYestUv)data.shakeYestUv=0;
              if(!data.shakeTotalUv)data.shakeTotalUv=0;
              if(!data.cityAdminCnt)data.cityAdminCnt=0;
              if(!data.distAdminCnt)data.distAdminCnt=0;
              if(!data.monthCityAdminCnt)data.monthCityAdminCnt=0;
              if(!data.monthDistAdminCnt)data.monthDistAdminCnt=0;
               vm.deviceDate=[{
                   name: "总设备数",
                   number: data.deviceCnt
               },{
                   name: "累计激活设备数",
                   number: data.activeDeviceCnt
               },{
                   name: "本月平均活跃设备数",
                   number: data.monthDeviceActiveCnt
               }];
              vm.userDate=[{
                  name: "平台总用户",
                  number: data.wxUserCnt
              },{
                  name: "昨日活跃总用户",
                  number: data.shakeYestUv
              },{
                  name: "累计活跃用户",
                  number: data.shakeTotalUv
              }];
              vm.providerDate=[{
                  name:"代理商总数/上月新增代理商",
                  number:(data.cityAdminCnt+data.distAdminCnt)+"/"+(data.monthCityAdminCnt+data.monthDistAdminCnt)
              },{
                  name:"市级代理商总数/上月新增市级代理商",
                  number:data.cityAdminCnt+"/"+data.monthCityAdminCnt
              },{
                  name:"区级代理商总数/上月新增区级代理商",
                  number:data.distAdminCnt+"/"+data.monthDistAdminCnt
              }];
          });
    }
})()