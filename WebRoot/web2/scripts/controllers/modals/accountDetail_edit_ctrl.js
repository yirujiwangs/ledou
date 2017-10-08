(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountDetailEditCtrl', AccountDetailEditCtrl);

    AccountDetailEditCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$modalInstance','shopData','utilService','$modal','AlertService','accountService'];

    function AccountDetailEditCtrl($scope,$rootScope,dataService,$location,$modalInstance,shopData,utilService,$modal,AlertService,accountService){
    	var vm=this;
    	vm.storeTypes=[{name:'普通类型'},{name:'平台类型'}];
    	//账号状态需求以后可能会变
        /*vm.statusData=[
    		{name:'未激活'},
    		{name:'审核中'},
    		{name:'正常'},
    		{name:'关闭'}
    		]*/
    	var OriginalData={};
        vm.conversion=dataService.conversion;
    	OriginalData=utilService.copy(shopData,OriginalData);

    	$scope.form=shopData;
        console.log($scope.form);
    	//select storeType选择默认值
    	var storeIndex=utilService.arrIndexOf(vm.storeTypes,{name:$scope.form.storeType});
    	//测试阶段storeTypes不一定正确
    	if(storeIndex==-1){
    		storeIndex=0;
        }
    	$scope.form.storeType=vm.storeTypes[storeIndex].name;


        //账号状态需求以后可能会变
    	//select status选择默认值
    	/*var statusIndex=utilService.arrIndexOf(vm.statusData,{name:$scope.form.status});*/
    	//测试阶段storeTypes不一定正确
    	/*if(statusIndex==-1)
    		statusIndex=0
    	$scope.form.status=vm.statusData[statusIndex].name*/

    	vm.update=function(){
    		var equal=utilService.equal($scope.form,OriginalData);
    		if(equal){
                AlertService.alert({success:true,msg:"操作成功"});
    			$modalInstance.dismiss('cancel');
    		}
    		else{
                console.log($scope.form);
    			accountService.subUpdate($scope.form)
    			.then(function(res){
                    console.log(res);
    				if(res.data.flag){
                        AlertService.alert({success:true,msg:"操作成功"})
                    }
    			})
    			.catch(function(err){
    				AlertService.alert({success:false,msg:err});
    			})
    			$modalInstance.dismiss('cancel');
    		}
    	}
        vm.close=function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()