(function(){
	'use strict';

    angular
        .module('center')
        .controller('MyaccountCtrl', MyaccountCtrl);

    MyaccountCtrl.$inject = [ '$scope', '$rootScope', '$route','dataService','personService','$modal','AlertService'];

    function MyaccountCtrl($scope,$rootScope, $route,dataService,personService,$modal,AlertService){
        var vm=this;
        var isBindWechat = sessionStorage.getItem("isBindWechat");
        console.log(isBindWechat)
        if(isBindWechat == "0"){
            checkQRbind();
        }
        personService.accountInfo()
        .then(function(res){
        	vm.info = res.data.accountInfo;
            vm.account = res.data.account;
            if(vm.account.openid){
                $scope.isBind = "已绑定";
            }
            else {
                $scope.isBind = "未绑定";
            }
            switch ($rootScope.proxyType){
                case 'M':
                    vm.account.level="市级代理";
                    break;
                case 'P':
                    vm.account.level="区县代理";
                    break;
                default:break;
            }
                vm.account.areaName=$rootScope.areaName;
        });
        vm.alipay = true;
        vm.public = false;
        vm.changeAccount = function(type){
        	// console.log(type);
        	if(type === 'alipay'){
        		vm.alipay = true;
        		vm.public = false;
        	}
        	else if(type === 'public'){
        		vm.alipay = false;
        		vm.public = true;
        	}
        }
        vm.editPasswd = function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/myaccountEditPasswd.html',
                controller: 'MyaccountEditPasswdCtrl',
                controllerAs: 'myaccountEditPasswdCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
            });
        }
        var oldValue;
        vm.edit = function(param){
            oldValue = vm.info[param];
            // console.log(oldValue);
            if(vm[param]){
                vm[param] = false;
            }
            else{
                vm[param] = true;
            }
        }
        vm.update = function(param){
            // console.log(vm.info[param]);
            if(vm.info[param] === oldValue){
                AlertService.alert({success:true,msg:"修改成功"});
                if(vm[param]){
                    vm[param] = false;
                }
                else{
                    vm[param] = true;
                }
            }
            else{
                var newValue = {};
                newValue[param] = vm.info[param];
                newValue.id = vm.info.id;
                // console.log(newValue);
                personService.updateAccountInfo(newValue)
                .then(function(res){
                    // console.log(res);
                    if(res.data.errorcode === 0){
                        AlertService.alert({success:true,msg:"修改成功"});
                    }
                    else{
                        AlertService.alert({success:false,msg:"修改失败，请稍后再试"});
                        $route.reload();
                    }
                    if(vm[param]){
                        vm[param] = false;
                    }
                    else{
                        vm[param] = true;
                    }
                })
            }
        }

        function checkQRbind(qrcodeUrl){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/qrcodeBind.html',
                controller: 'QrcodeBindCtrl',
                controllerAs: 'qrcodeBindCtrl',
                size: 'sm',
                keyboard: false,
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    qrcode: function () {
                        //console.log(qrcodeUrl);
                        //return qrcodeUrl;
                    }
                }
            });
        }
    }
})()