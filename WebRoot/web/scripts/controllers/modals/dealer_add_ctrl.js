(function(){
	'use strict';

    angular
        .module('center')
        .controller('DealerAddCtrl', DealerAddCtrl);

    DealerAddCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','dealerService'];

    function DealerAddCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,dealerService){
        var vm = this;
        // 表单数据初始化
        $scope.form = {};

        vm.add = function(){
            console.log($scope.form)
            dealerService.addDealer($scope.form)
                .then(function(res){
                    // console.log(res);
                    if(res.data.errcode =="1"){
                        AlertService.alert({success:true,msg:res.data.errcode});
                    }
                    else{
                        AlertService.alert({success:false,msg:res.data.errcode});
                    }
                });
            $modalInstance.dismiss('cancel');
            $route.reload();
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }

    }
})()