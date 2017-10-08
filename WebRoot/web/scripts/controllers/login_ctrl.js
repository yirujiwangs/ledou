(function(){
	'use strict';

    angular
        .module('center')
        .controller('LoginCtrl', LoginCtrl);

    LoginCtrl.$inject = [ '$scope', '$http', '$route', '$rootScope','base64Service','$location','$modal','loginDao','AlertService'];

    function LoginCtrl($scope,$http,$route,$rootScope,base64Service,$location,$modal,loginDao,AlertService){
    	var vm = this;
        var times=1;
        $scope.loginType = true;
        vm.loginToggle = function(type){
            if(type === 'user'){
                $scope.loginType = true;
                //console.log("user")
            }
            else if(type === 'qrcode') {
                $scope.loginType = false;
                if (times == "1"){
                    $http({
                        url: '/admin/wx/loginQrCode.do',
                        method: 'POST'
                    }).success(function (data) {

                        if (data.errcode == "1") {
                            //console.log(times);
                            $scope.loginQrcode = data.object;
                            var paramNum = data.object;
                                loginDao.qrcodeLogin(paramNum.slice(75, 85))
                                    .then(function (res) {
                                        var data = angular.fromJson(res);
                                       // console.log(data.data)
                                        if (data.data.errcode == "1") {
                                            sessionStorage.userName=data.data.object.username;
                                            sessionStorage.areaName=res.data.object.area_name;
                                            sessionStorage.areaRid=res.data.object.area_rid;
                                            sessionStorage.proxyType=base64Service.base64encode(res.data.object.proxyType);
                                            sessionStorage.policy=res.data.object.policy;
                                            AlertService.alert({success: true, msg: data.data.errmsg});
                                            $location.path('/');
                                        }
                                        else {
                                            AlertService.alert({success: false, msg: data.data.errmsg});
                                            $route.reload();
                                        }
                                    });
                        }
                        else {
                            AlertService.alert({success: false, msg: data.errmsg});

                        }
                    })
                times++;
            }

            }
        }

        $scope.form={};
        $scope.form.username='';
        $scope.form.password='';

        $scope.form.super='no';
        $scope.rem=false;



        vm.loginCheck=function(){

            loginDao.check($scope.form)
            .then(function(res){
                localStorage.clear();
                if(res.data.errcode === 0){
                    //loginDao.qrcode()
                    //    .then(function(res){
                    //        sessionStorage.setItem("qrcodeUrl",res.data.object);
                    // });
                    sessionStorage.userName=res.data.object.username;
                    sessionStorage.areaName=res.data.object.area_name;
                    sessionStorage.areaRid=res.data.object.area_rid;
                    sessionStorage.proxyType==base64Service.base64encode(res.data.object.proxyType);
                    sessionStorage.policy=res.data.object.policy;
                    sessionStorage.setItem("isBindWechat",res.data.errcode);

                    console.log(res)
                    //权限判断是否是市级代理商
                    $rootScope.permission='ledou01';
                    if($scope.rem){
                        //base64加密权限码
                        localStorage.proxyType=base64Service.base64encode(res.data.object.proxyType);
                        localStorage.userName=res.data.object.username;
                        localStorage.areaRid=res.data.object.area_rid;
                        localStorage.areaName=res.data.object.area_name;
                        localStorage.policy=res.data.object.policy;
                        localStorage.startTime=new Date().getTime();
                    }

                    $location.path('/myaccount');
                }
                else if(res.data.errcode === 1){
                    sessionStorage.userName=res.data.object.username;
                    sessionStorage.areaName=res.data.object.area_name;
                    sessionStorage.areaRid=res.data.object.area_rid;
                    sessionStorage.proxyType=base64Service.base64encode(res.data.object.proxyType);
                    sessionStorage.policy=res.data.object.policy;
                    if($scope.rem){
                        //base64加密权限码
                        localStorage.proxyType=base64Service.base64encode(res.data.object.proxyType);
                        localStorage.userName=res.data.object.username;
                        localStorage.areaRid=res.data.object.area_rid;
                        localStorage.areaName=res.data.object.area_name;
                        localStorage.policy=res.data.object.policy;
                        localStorage.startTime=new Date().getTime();
                    }
                    AlertService.alert({success:true,msg:"登陆成功"});
                    sessionStorage.setItem("isBindWechat",res.data.errcode);
                    $location.path('/');
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