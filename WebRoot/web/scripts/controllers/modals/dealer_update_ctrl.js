(function(){
	'use strict';

    angular
        .module('center')
        .controller('DealerUpdateCtrl', DealerUpdateCtrl);

    DealerUpdateCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','dealerService','dealerData','$http'];

    function DealerUpdateCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,dealerService,dealerData,$http){
        var vm = this;
        var id = dealerData;
    //  ��̨���ݶԽ����ȡ���˲���ע��
    /*  dealerService.dealerDetail({"id":id})
            .then(function(res){
                console.log(res);
                vm.dealerDetail=res;
            });*/

     //  mock ģ�����ݣ�  ��̨���ɾ���˲���
        $http({
            url: 'http://rap.taobao.org/mockjsdata/13526/ledou/dealer/8',
            method: 'GET'
        }).success(function (res) {
            console.log(res)
            vm.dealerDetail=res;
        });

        vm.update = function(){
            console.log($scope.comment)
            dealerService.addDealer({"id":id,"comment":$scope.comment})
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