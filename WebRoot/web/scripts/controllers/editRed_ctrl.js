(function(){
    'use strict';

    angular
        .module('center')
        .controller('EditRedCtrl', EditRedCtrl);

    EditRedCtrl.$inject = [ '$scope', '$rootScope','base64Service','AttractFansRedPacketService','AttractFansRedPacketDao','dataService','$modal','utilService','$location','AlertService','$http','$filter','$timeout'];

    function EditRedCtrl($scope,$rootScope,base64Service,AttractFansRedPacketService,AttractFansRedPacketDao,dataService,$modal,utilService,$location,AlertService,$http,$filter,$timeout){
        if(sessionStorage.proxyType) $scope.proxyType = base64Service.base64decode(sessionStorage.proxyType);
        else if(localStorage.proxyType) $scope.proxyType = base64Service.base64decode(localStorage.proxyType);
        var vm=this;
        var param = {};
        var redData = angular.fromJson(sessionStorage.getItem("redInfo"));
        console.log(redData)
        if(redData != undefined){
            $scope.begin=1000*redData.begin_time;
            $scope.end=1000*redData.end_time;
            $scope.beginTime= $filter('date')(1000*redData.begin_time, 'yyyy-MM-dd HH:mm');
            $scope.endTime= $filter('date')(1000*redData.end_time, 'yyyy-MM-dd HH:mm');
            $scope.touNum = redData.left_num+redData.numb;//剩余量不上传
            $scope.publicName=redData.red_name;//红包名称
            $scope.publicnum_type=redData.name;
            $scope.appid=redData.appid;
            $scope.serial_num=redData.serial_num;//红包id编辑必须上传
            AttractFansRedPacketService.redPacketDetail({rid:redData.id})
                .then(function(res){
                    if(res) {
                        var data=res.data;
                        if(data.grantType=='D'){
                            $scope.type='D';
                            if(data.selectedStrategyList)$scope.device=data.selectedStrategyList;
                            else $scope.device=[];
                        }else if(data.grantType=='W'){
                            $scope.type='W';
                            $scope.area=[];
                            if(data.areasArr){
                                var len=data.areasArr.length;
                                for(var i=0;i<len;++i){
                                    $scope.area.push({Id:data.areasArr[i]});
                                }
                            }
                        }
                    }
                });
            AttractFansRedPacketService.feeTemplate({type:'powderRed'})
                .then(function(res){
                    if(res.data.length!=0){
                        $scope.withdrawalData=res.data[0].withdrawal;
                        $scope.preFee=(($scope.withdrawalData.once_money)/100*$scope.touNum).toFixed(2);
                    }
                })
        }
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
                if(len>0&&n[0].id){
                    for (var i = 0; i < len; ++i) {
                        arr.push(n[i].id)
                    }
                }
                else if(len>0&&n[0].groupId){
                    for (var i = 0; i < len; ++i) {
                        arr.push(n[i].groupId)
                    }
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

        vm.submit = function(){

            if(!$scope.begin||!$scope.end){
                AlertService.alert({success:false,msg:"起始时间和结束时间不能为空"});
                return false;
            }
            if($scope.end<(new Date()).getTime()){
                AlertService.alert({success:false,msg:"结束时间不能小于今天"});
                return false;
            }
            param.type=$scope.type;
            param.with_drawl= $scope.withdrawalData.id;
            param.publicName=$scope.publicName;
            param.left_num = $scope.touNum;
            param.publicnum_type=$scope.publicnum_type;
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
            param.preFee = $scope.preFee;
            param.begin_time = $scope.begin/1000;
            param.end_time = $scope.end/1000;
            param.serial_num=$scope.serial_num;

         $http({
                url:'/ledou/red/editPowderRed.do',
                method: 'POST',
                data: param
            }).success(function(data){
                if(data.error==0){
                    AlertService.alert({success:true,msg:data.error_reason});
                    $timeout(function(){
                        $location.path("/attractFansRedPacket");
                    },2000)
                }
            }).error(function(){
                console.log("error");
            })

        }

        //日历设置
        moment.locale('zh-cn');
        $scope.form = {};
        $scope.configFunction = function configFunction() {
            return {
                startView: 'month',
                minView: 'day'
            };
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.begin=newDate.getTime();
            $scope.beginTime=moment(newDate).format("YYYY-MM-DD 00:00:00");
            $('#dropdown1').dropdown('toggle');
        };
        $scope.inputOnEndTimeSet=function(newDate) {
            $scope.end=newDate.getTime()+1000*60*60*23+1000*60*59+1000*59;
            $scope.endTime=moment(newDate).format("YYYY-MM-DD 23:59:59");
            $('#dropdown2').dropdown('toggle');
        };
        $scope.$watch("form.startTime", function(n, o){
            if(n=='')$scope.begin=undefined;
        });
        $scope.$watch("form.endTime", function(n, o){
            if(n=='')$scope.end=undefined;
        });
    }
})()
