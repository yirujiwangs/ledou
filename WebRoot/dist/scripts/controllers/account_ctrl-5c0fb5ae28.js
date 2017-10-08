(function(){
	'use strict';

    angular
        .module('center')
        .controller('AccountCtrl', AccountCtrl);
        // .directive('numchange', numchange);

    AccountCtrl.$inject = [ '$scope', '$rootScope','dataService','accountService','$modal'];

    function AccountCtrl($scope,$rootScope,dataService,accountService,$modal){
        var vm = this;
        vm.currentPage = 1;
        var page = {};
        page.startPage = 1;
        page.pageSize = 8;
        // console.log(page);
        // 获取列表
        accountService.listTotal(page)
        .then(function(res){
            // console.log(res);
        	vm.generalNum=res.data.amount;
            vm.allUser=res.data.allUser;
            vm.totalPage = res.data.pages;
            vm.currentPage = page.startPage;
            // console.log(vm.allUser);
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
        vm.add=function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/account_add.html',
                controller: 'AccountAddCtrl',
                controllerAs: 'accountAddCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        }

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
    }

    /*function numchange(){
        function link(scope,element,attrs){
            console.log(scope);
            console.log(element);
            console.log(attrs);
            attrs.$observe('phonenum', function (value) {
                console.log(value);
                scope.num = value.length;
            });
        }
        return {
            restrict: 'EAC',
            // replace: true,
            scope: {
                phonenum: '@'
            },
            template: '<div class="col-xs-2 del-l-p">'
                      + '<span class="form-num">{{num}} / {{maxNum}}</span>'
                      + '</div>{{form.phoneNum}}',
            link: link
        };
    };*/
})()