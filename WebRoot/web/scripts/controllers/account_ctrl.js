(function(){
	'use strict';

    angular
    .module('center')
    .controller('AccountCtrl', AccountCtrl);

    AccountCtrl.$inject = [ '$scope', '$rootScope','dataService','accountService','$modal'];

    function AccountCtrl($scope,$rootScope,dataService,accountService,$modal){
        var vm = this;
        vm.currentPage = 1;
        vm.totalPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;
        // console.log(page);   
        // 获取列表
        accountService.listTotal(page)  //前台给后天传参page
        .then(function(res){           //后台给前台传递过来的数据
            console.log(res);
            vm.generalNum=res.data.amount;
            vm.allUser=res.data.allUser;
            vm.totalPage = res.data.pages;
            vm.currentPage = page.startPage;
        });
        
        vm.conversion=dataService.conversion;
        vm.titles=dataService.accounttitles;
        
        vm.pre = function(){
            if(page.startPage > 1){
                page.startPage--;
            }
            else{
                page.startPage = 1;
            }
            accountService.listTotal(page)
            .then(function(res){
                vm.generalNum=res.data.amount;
                vm.allUser=res.data.allUser;
                vm.currentPage = page.startPage;
            });
        }
        vm.next = function(){
            if(page.startPage < vm.totalPage){
                page.startPage++;
            }
            else{
                page.startPage = vm.totalPage;
            }
            accountService.listTotal(page)
            .then(function(res){
                vm.generalNum=res.data.amount;
                vm.allUser=res.data.allUser;
                vm.currentPage = page.startPage;
            });
        }
        //修改
        vm.update=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_edit.html',
                controller: 'AccountEditCtrl',
                controllerAs: 'accountEditCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                   shopData: function () {
                       return vm.allUser[index];
                   }
               }
            });
        }
        vm.delete=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_del.html',
                controller: 'AccountDeleteCtrl',
                controllerAs: 'accountDeleteCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    shopData: function () {
                        return vm.allUser[index];
                    }
                }
            });
        }
        //添加
        vm.add=function(){
            var editSqlModalInstance = $modal.open({ //$modal是一个可以迅速创建模态窗口的服务，创建部分页，控制器，并关联他们,$modal仅有一个方法open(options)
                templateUrl: 'views/modals/account_add.html',//模态窗口的地址
                controller: 'AccountAddCtrl',//为$modal指定的控制器，初始化$scope，该控制器可用$modalInstance注入
                controllerAs: 'accountAddCtrl',
                backdrop: 'true',//控制背景，允许的值：true（默认），false（无背景），“static” - 背景是存在的，但点击模态窗口之外时，模态窗口不关闭
                windowClass: 'overflow-y-auto chart-modal',//指定一个class并被添加到模态窗口中,把属性添加给整个模态框窗口
                keyboard:true,
            });
        }
        //禁用
        vm.status=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_status.html',
                controller: 'AccountStatusCtrl',
                controllerAs: 'accountStatusCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    statusData: function () {
                        var sta = {};
                        sta.id = vm.allUser[index].id;
                        sta.status = vm.allUser[index].status;
                        return sta;
                    }
                }
            });
        }

        //function checkQRbind(){
        //    var editSqlModalInstance = $modal.open({
        //        templateUrl: 'views/modals/qrcodeBind.html',
        //        controller: 'QrcodeBindCtrl',
        //        controllerAs: 'qrcodeBindCtrl',
        //        size: 'sm',
        //        keyboard: false,
        //        backdrop: 'static',
        //        windowClass: 'overflow-y-auto chart-modal',
        //        resolve: {
        //            qrcode: function () {
        //                return sessionStorage.getItem("qrcodeImg");
        //            }
        //        }
        //    });
        //}

        vm.qrcodeStoreBind=function(index){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/qrcodeStoreBind.html',
                controller: 'QrcodeStoreBindCtrl',
                controllerAs: 'qrcodeStoreBindCtrl',
                size: 'sm',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    account: function () {
                        return {
                            account: vm.allUser[index].account
                        }
                    }
                }
            });
        }
    }
})();