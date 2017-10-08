(function(){
    'use strict';

    angular
        .module('center')
        .controller('QrcodeStoreBindCtrl', QrcodeStoreBindCtrl);

    QrcodeStoreBindCtrl.$inject = [ '$scope', '$rootScope','accountService','dataService','$location','$modalInstance','utilService','$modal','AlertService','account'];

    function QrcodeStoreBindCtrl($scope,$rootScope,accountService,dataService,$location,$modalInstance,utilService,$modal,AlertService,account){
        var vm=this;
        accountService.bindWxAccount(account)
        .then(function(res){
        	console.log(res);
            var qrcodeUrl = res.data.object;
            $scope.qrcodeUrl = qrcodeUrl;
        })
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()