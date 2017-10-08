(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountDeleteCtrl', AccountDeleteCtrl);

    AccountDeleteCtrl.$inject = [ '$scope', '$rootScope','$modalInstance','AlertService','accountDao','shopData'];

    function AccountDeleteCtrl($scope,$rootScope,$modalInstance,AlertService,accountDao,shopData){
    	var vm=this;
    	var account=shopData.account;
    	var obj={id:account}
    	this.ok=function(){
    		accountDao.del(obj)
    		.then(function(res){
    			if(res.data.flag){
    				AlertService.alert({success:true,msg:"操作成功"})
    				$modalInstance.dismiss('cancel');
                    setTimeout(function(){
                        location.reload(true);
                    },300);
    			}
    			else{
    				AlertService.alert({success:false,msg:"操作失败，请重新操作"})
    				$modalInstance.dismiss('cancel');
    			}
    		})
    		.catch(function(err){
    			AlertService.alert({success:false,msg:err})
    			$modalInstance.dismiss('cancel');
    		})
    	}
    	this.close=function(){
    		$modalInstance.dismiss('cancel');
    	}
    }
})()