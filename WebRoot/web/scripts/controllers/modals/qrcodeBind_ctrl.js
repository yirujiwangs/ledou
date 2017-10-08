(function(){
    'use strict';

    angular
        .module('center')
        .controller('QrcodeBindCtrl', QrcodeBindCtrl);

    QrcodeBindCtrl.$inject = [ '$scope','$route','$timeout','$interval','$http', '$rootScope','accountService','dataService','$location','$modalInstance','qrcode','utilService','$modal','AlertService'];

    function QrcodeBindCtrl($scope,$route,$timeout,$interval,$http,$rootScope,accountService,dataService,$location,$modalInstance,qrcode,utilService,$modal,AlertService){
        var vm=this;
        $scope.qrcodeUrl = qrcode;
        $http({
            url:'/ledou/wx/qrCode.do',
            method: 'POST'
        }).success(function(data){
            console.log(data)
            $scope.qrcodeUrl = data.object;
        });
        //$scope.qrcodeUrl = qrcode;
            $http({
            url:'/ledou/wx/requestState.do',
            method: 'POST'
        }).success(function(data){
            console.log(data);
            if(data.errcode == "1"){
                sessionStorage.setItem("isBindWechat",1);
                $modalInstance.dismiss('cancel');
                AlertService.alert({success:true,msg:data.errmsg});
                $route.reload();
            }
            else{
                AlertService.alert({success:false,msg:data.errmsg});

                setTimeout(function(){
                    $modalInstance.dismiss('cancel');
                    $route.reload();
                },5000);

              //  window.location.reload();
            }
        })
    }
  })()