(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountAddCtrl', AccountAddCtrl);

    AccountAddCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','accountService'];

    function AccountAddCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,accountService){
    	var vm = this;
        // 表单数据初始化
        $scope.form = {};
        // 定义商家类型
        vm.storeTypes = [{name:'普通类型'},{name:'平台类型'}];
        //storetype默认选择普通商家
        $scope.form.storeType=vm.storeTypes[0].name;
    	vm.add = function(){
            console.log($scope.form)
            accountService.adduser($scope.form)
            .then(function(res){
                // console.log(res);
                if(res.data.flag){
                    AlertService.alert({success:true,msg:"创建成功"});
                }
                else{
                    AlertService.alert({success:false,msg:"创建失败，请稍候再试"});
                }
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
            });
            $modalInstance.dismiss('cancel');
            $route.reload();
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');  //dismiss(reason)：撤销模态方法并传递一个原因a
        }
    }
})()