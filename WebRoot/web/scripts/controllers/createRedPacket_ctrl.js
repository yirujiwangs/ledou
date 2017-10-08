(function(){
    'use strict';
    angular
        .module('center')
        .controller('CreateRedPacketCtrl',CreateRedPacketCtrl);
    CreateRedPacketCtrl.$inject = [ '$scope', '$rootScope','$route','AttractFansRedPacketService','dataService','AlertService','$modal','$location'];
    function CreateRedPacketCtrl($scope,$rootScope,$route,AttractFansRedPacketService,dataService,AlertService ,$modal,$location) {
        var vm = this;
        $scope.adv_url="https://";
        $scope.appid='';
        $scope.publicName='';
        //默认选择方式为设备投放
        $scope.type='D';
        //默认广告补贴为0
        $scope.adAllowance=0;
        //初始化下拉列表
        vm.allAccountsInfo=[];

        //初始化数量校验正则
        $scope.pattern=/\d{3,}/;
        //初始化最小数量
        $scope.minNum=100;

        $scope.accountInfo=null;

        $scope.appid='';

        $scope.$watch("accountInfo", function(n, o){
            if(!n)$scope.appid='';
            else if(n)$scope.appid= n.appid;
        });

        //公众号下拉列表展示
        AttractFansRedPacketService.getAccountsInfo().then(function(res){
            //console.log(res)
            if(res){
                vm.allAccountsInfo= res.data.object;
            }
        })
        //设置timePicker
        moment.locale('zh-cn');
        $scope.form = {};
        $scope.configFunction = function configFunction() {
            return {startView: 'month',minView: 'day'};
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.begin=newDate.getTime();
            $scope.form.startTime=moment(newDate).format("YYYY-MM-DD 00:00:00");
            $('#dropdown1').dropdown('toggle');
        };
        $scope.inputOnEndTimeSet=function(newDate) {
            $scope.end=newDate.getTime()+1000*60*60*23+1000*60*59+1000*59;
            $scope.form.endTime=moment(newDate).format("YYYY-MM-DD 23:59:59");
            $('#dropdown2').dropdown('toggle');
        };
        $scope.$watch("form.startTime", function(n, o){
            if(n=='')$scope.begin=undefined;
        });
        $scope.$watch("form.endTime", function(n, o){
            if(n=='')$scope.end=undefined;
        });
        //默认为设备分组
        $scope.type='D';
        //吸粉红包（投放设备分组）
        vm.setDevice= function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/AfDevice.html',
                controller: 'AfDeviceCtrl',
                controllerAs: 'afDeviceCtrl',
                size : 'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    items:function(){
                        return $scope.device;
                    }
                }
            });
            editSqlModalInstance.result.then(function(res){
                $scope.device = res;
            })
        }
        $scope.$watch("device", function(n, o){
            if(n){
                var len = n.length;
                var arr = [];
                for (var i = 0; i < len; ++i) {
                    arr.push(n[i].id)
                }
                    $scope.putDevice=arr;
            }
        });

        //吸粉红包（投放区域分组）
        vm.setArea = function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/AfArea.html',
                controller: 'AfAreaCtrl',
                controllerAs: 'afAreaCtrl',
                size : 'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    items:function(){
                        return $scope.area
                    }
                }
            });
            editSqlModalInstance.result.then(function(res){
                $scope.area = res;
            })
        }
        //加工一下传给后端
        $scope.$watch("area", function(n, o){
            if(n) {
                var len = n.length;
                var arr = [];
                for (var i = 0; i < len; ++i) {
                   arr.push(Number(n[i].Id))
                }
                $scope.putArea = arr;
            }
        });
        AttractFansRedPacketService.feeTemplate({type:'powderRed'})
            .then(function(res){
                $scope.touNum = "";
                $scope.preFee = 0;
                if(res)$scope.Withdrawlsdata=res.data;
        })

        $scope.preFeeChange = function(){
            $scope.withdrawalData =angular.fromJson( $scope.withdrawal);
        };

        $scope.$watch("withdrawalData", function(n){
            if(!n)return;
            var Num = angular.element('#Num');
            if(n.type=="O"){
                Num.attr({
                    min:100,
                    placeholder:"投放次数需在100次以上"
                });
                $scope.pattern=/\d{3,}/;
                $scope.minNum=100;
                $scope.preFee= n.once_money/100;
            }
            else if(n.type=="Q"){
                Num.attr({
                    min:1000,
                    placeholder:"投放次数需在1000次以上"
                });
                $scope.pattern=/\d{4,}/;
                $scope.minNum=1000;
                $scope.preFee= n.once_money/100000;
            }
            else if(n.type=="powderRed"){
                Num.attr({
                    min:100,
                    placeholder:"吸粉人数需在100次以上"
                });
                $scope.pattern=/\d{3,}/;
                $scope.minNum=100;
                $scope.preFee= n.once_money/100;
            }
        });

        var param={};

        vm.submit = function(){
            //初始化默认不提交这个是防止重复提交不走下面逻辑的问题
            var Form = angular.element('#fromId');
            Form.attr('onsubmit','return false');
            if(!$scope.withdrawalData.id){
                AlertService.alert({success:false,msg:"请选择计费方式"});
                return false;
            }
            if(!$scope.begin||!$scope.end){
                AlertService.alert({success:false,msg:"起始时间和结束时间不能为空"});
                return false;
            }
            if($scope.begin>$scope.end){
                AlertService.alert({success:false,msg:"起始时间不能大于结束时间"});
                return false;
            }
            if($scope.end<(new Date()).getTime()){
                AlertService.alert({success:false,msg:"结束时间不能小于今天"});
                return false;
            }

            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAd_pay.html',
                controller: 'AppendAdPayCtrl',
                controllerAs: 'AppendAdPayCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });

            param.with_drawl= $scope.withdrawalData.id;
            param.type=$scope.type;
            param.publicName=$scope.publicName;
            param.left_num = $scope.touNum;
            param.publicnum_type=$scope.accountInfo.nickName;
            param.appid=$scope.appid;
            param.advStrategy={};
            if($scope.type=='D'){
                param.advStrategy.grantType=1;
                if(!$scope.putDevice){
                    AlertService.alert({success:false,msg:"请配置投放设备分组"});
                    return false;
                }
                param.advStrategy.deliveryGroup= $scope.putDevice;
                param.advStrategy.areaCode=[];
            }
            else if($scope.type=='W'){
                param.advStrategy.grantType=0;
                if(!$scope.putArea){
                    AlertService.alert({success:false,msg:"请配置投放区域分组"});
                    return false;
                }
                param.advStrategy.areaCode=$scope.putArea;
                param.advStrategy.deliveryGroup=[];
            }
            param.subsidy=0;
            param.preFee = ($scope.touNum *$scope.preFee).toFixed(2);
            param.begin_time = $scope.begin/1000;
            param.end_time = $scope.end/1000;
            $scope.formData=angular.toJson(param, true);

            //通过这个设置为表单提交不需要写延时器因为双向绑定优先级高
            Form.attr('onsubmit','return true');
        };
    }
})()