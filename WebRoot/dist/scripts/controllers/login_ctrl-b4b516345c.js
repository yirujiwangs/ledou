(function(){
	'use strict';

    angular
        .module('center')
        .controller('LoginCtrl', LoginCtrl);

    LoginCtrl.$inject = [ '$scope', '$rootScope','$location','loginDao','AlertService'];

    function LoginCtrl($scope,$rootScope,$location,loginDao,AlertService){
    	$scope.form={};
        $scope.form.username='';
        $scope.form.password='';
        $scope.form.super='no';
        $scope.rem=false;
        var vm = this;
        vm.loginCheck=function(){
            loginDao.check($scope.form)
            .then(function(res){
                // console.log(res);
                localStorage.clear();
                if(res.data.loginFlag){
                    sessionStorage.userName=$scope.form.username;
                    if($scope.rem){
                        localStorage.userName=$scope.form.username;
                        localStorage.startTime=new Date().getTime();
                    }
                    AlertService.alert({success:true,msg:"登陆成功"})
                    $location.path('/')
                }
                else{
                    AlertService.alert({success:false,msg:"用户名或密码错误,请重新登录"})
                }
            })
        }
        // 密码框按回车触发登录
        vm.enter=function(e){
            // console.log(e.keyCode);
            if(e.keyCode === 13){
                vm.loginCheck();
            }
        }
    }
})()