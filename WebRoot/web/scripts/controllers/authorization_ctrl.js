(function(){
    'use strict';
    angular
        .module('center')
        .controller('AuthorizationCtrl',AuthorizationCtrl);
    AuthorizationCtrl.$inject = [ '$scope', '$rootScope','$route','AttractFansRedPacketService','dataService','AlertService','$modal','$location'];
    function AuthorizationCtrl($scope,$rootScope,$route,AttractFansRedPacketService,dataService,AlertService,$modal,$location) {
        var vm = this;
        //换页
        vm.allInfo=null;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        param.status='S';

        //也就是说www.baidu.com成了/www.baidu.com跳转后地址变为http://localhost:8080/ledou/web/www.baidu.com
        //因为跨域必须在前面添加协议名
       // window.location="http://shaketest.ledouya.com/master/auth/powder?powder=true";


        vm.cancelAuthorization=function(index){
            var id=vm.allInfo[index].mpinfo.id;
            var proxy_token=vm.allInfo[index].mpinfo.proxy_token;
            var obj={proxy_token:proxy_token,id:id};
            AttractFansRedPacketService.authorize(obj)
                .then(function(res){
                    if(res.data.error==0){
                        AlertService.alert({success:true,msg:res.data.error_reason});
                        load();
                    }
                    else if(res.data.error==-1)AlertService.alert({success:false,msg:res.data.error_reason});
                })
        }

        vm.reAuthorization=function(index){
            var proxy_token=vm.allInfo[index].mpinfo.proxy_token;
            window.open("https://shaketest.ledouya.com/Agents/red/authorize?proxyToken="+proxy_token,'authorizePage')
            vm.isRefresh();
        }

        vm.titles = dataService.authorizationTitles;

        function load() {
            AttractFansRedPacketService.authorization(param)
                .then(function (res) {
                    if (res) {
                        //ng-href 指令确保了链接是正常的，即使在 AngularJS 执行代码前点击链接。
                        $scope.href="https://ad.ledouya.com/Agents/red/authorize?proxyToken="+res.data.proxyToken;
                        vm.allInfo = res.data.object.items;
                        if(res.data.object.total_pages!=0)vm.totalPage = res.data.object.total_pages;
                        vm.currentPage = param.startPage;
                    }
                });
        }
        load();

        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            load();
        }
        vm.next = function(){

            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            load();
        }

        //确认刷新模态框
        vm.isRefresh = function () {
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/authorizationRefresh.html',
                controller: 'AuthorizationRefreshCtrl',
                controllerAs: 'authorizationRefreshCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }
    }
})()