(function(){
    'use strict';

    angular
        .module('center')
        .controller('EditAdCtrl', EditAdCtrl);

    EditAdCtrl.$inject = [ '$scope', '$rootScope','base64Service','AdService','AdDao','dataService','$modal','utilService','$location','AlertService','$http','$filter','$timeout'];

    function EditAdCtrl($scope,$rootScope,base64Service,AdService,AdDao,dataService,$modal,utilService,$location,AlertService,$http,$filter,$timeout){
        if(sessionStorage.proxyType) $scope.proxyType = base64Service.base64decode(sessionStorage.proxyType);
        else if(localStorage.proxyType) $scope.proxyType = base64Service.base64decode(localStorage.proxyType);
        var param = {};
        var adData = angular.fromJson(sessionStorage.getItem("adInfo"));
            console.log(adData);
            if(adData != undefined){
            $scope.storeName = adData.storeName;
            $scope.picUrl= adData.pic;
            $scope.logoUrl= adData.logo;
            $scope.advUrl= adData.url;
            $scope.begin=1000*adData.beginTime;
            $scope.end=1000*adData.endTime;
            $scope.beginTime= $filter('date')(1000*adData.beginTime, 'yyyy-MM-dd HH:mm');
            $scope.endTime= $filter('date')(1000*adData.endTime, 'yyyy-MM-dd HH:mm');
            $scope.withdrawalName= adData.withdrawal.name;
            $scope.touNum = adData.touNum;
            $scope.advUUID=adData.advUUID;
            $scope.subsidy=adData.subsidy;
            $scope.withdrawalId=adData.withdrawal.id;
            $scope.preFee= adData.withdrawal.once_money;
            AdDao.advStrategyDetail({advUUID:adData.advUUID,type:'ad'})
                .then(function(res){
                    if(res.data.errcode==1){
                        var data=angular.fromJson(res.data.object);
                        console.log(data);
                        if(data.grantType=='1'){
                            $scope.method="device";
                            if(data.selectedStrategyList)$scope.device=data.selectedStrategyList;
                            else $scope.device=[];
                        }else if(data.grantType=='0'){
                            $scope.method="area";
                            $scope.area=[]
                            if(data.areasArr){
                                var len=data.areasArr.length;
                                for(var i=0;i<len;++i){
                                    $scope.area.push({area_rid:data.areasArr[i]});
                                }
                            }
                        }
                    }
                })
                //iframe加载也需要时间
            setTimeout(function(){
                angular.element("iframe").contents().find("#head-name").html($scope.storeName + "的现金红包");
                angular.element("iframe").contents().find("#head-img").attr("src",$scope.picUrl);
                angular.element("iframe").contents().find("#logo").attr("src",$scope.logoUrl);
            },1000)
        }
        $scope.previewName = function(){
            if($scope.storeName) {
                angular.element("iframe").contents().find("#head-name").html($scope.storeName + "的现金红包");
            }else{
                angular.element("iframe").contents().find("#head-name").html("乐豆呀的现金红包");
            }
        }
        // 七牛云上传
        function upload(result,target,x,y,cb,maxSize){
            if(!maxSize)maxSize=100;
            AdDao.uploadInit($scope, result, target,function(up, file) {
                },
                function (up, file, info) {
                    var res = JSON.parse(info);///2/w/530/h/300/interlace/0/q/100
                    if(target=='uploadLogo')var link = res.domain + res.key + '?imageView2/2/w/'+x+'/h/'+y+'/interlace/0/q/100';
                    else var link = res.domain + res.key;
                    $scope.$apply(function () {
                        cb($scope,link);
                        AlertService.alert({success:true,msg:"上传成功"});
                    });
                },
                function(up, err, errTip){
                    if(err.code==-600)AlertService.alert({success: false, msg: "上传资源大小不能超过" + maxSize + " MB"});
                    else if(err.code==-200)AlertService.alert({success: false, msg: "文件名已存在"});
                    else AlertService.alert({success: false, msg: "上传资源错误"});
                },maxSize);
        }

        AdDao.getUploadToken().then(function (result) {
            upload(result,'uploadLogo',120,120,function($scope,link){
                $scope.logoUrl = link;
                angular.element("iframe").contents().find("#logo").attr("src",link);
            });
            upload(result,'uploadPic',640,427,function($scope,link){
                $scope.picUrl = link;
                angular.element("iframe").contents().find("#head-img").attr("src",link);
            });
        });

        // 配置设备分组
        $scope.setDevice= function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAdDevice.html',
                controller: 'AppendAdDeviceCtrl',
                controllerAs: 'appendAdDeviceCtrl',
                size : 'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    items:function(){
                        return $scope.device;
                    },
                    type:function(){
                        return 'ad';
                    }
                }
            });
            editSqlModalInstance.result.then(function(res){
                $scope.device = res;
            })
        }
        $scope.$watch("device", function(n, o){
            if(n) {
                var len = n.length;
                var arr = [];
                for (var i = 0; i < len; ++i) {
                    arr.push(n[i].groupId)
                }
                $scope.putDevice = arr;
            }
        });

        //配置区域分组
        $scope.setArea = function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAdArea.html',
                controller: 'AppendAdAreaCtrl',
                controllerAs: 'appendAdAreaCtrl',
                size : 'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    items:function(){
                        return $scope.area
                    },
                    type:function(){
                        return 'ad';
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
                    arr.push(Number(n[i].area_rid))
                }
                $scope.putArea = arr;
            }
        });

        $scope.submit = function(){

            if(!$scope.begin||!$scope.end){
                AlertService.alert({success:false,msg:"起始时间和结束时间不能为空"});
                return false;
            }
            if($scope.end<(new Date()).getTime()){
                AlertService.alert({success:false,msg:"结束时间不能小于今天"});
                return false;
            }

            param.advStrategy={};
            if($scope.method=="device"){
                param.advStrategy.grantType=1;
                if(!$scope.putDevice){
                    AlertService.alert({success:false,msg:"请配置投放设备分组"});
                    return false;
                }
                param.advStrategy.deliveryGroup= $scope.putDevice;
                param.advStrategy.areaCode=[];
            }
            else if($scope.method=="area"){
                param.advStrategy.grantType=0;
                if(!$scope.putArea){
                    AlertService.alert({success:false,msg:"请配置投放区域分组"});
                    return false;
                }
                param.advStrategy.areaCode=$scope.putArea;
                param.advStrategy.deliveryGroup=[];
            }

            param.media_type='P';
            param.withdrawalId = $scope.withdrawalId;
            param.touNum = $scope.touNum;
            param.type='ad';
            param.pic = $scope.picUrl;
            param.logo = $scope.logoUrl;
            if($scope.advUrl&&$scope.advUrl!='https://')param.advUrl = $scope.advUrl;
            param.beginTime = $scope.begin;
            param.endTime = $scope.end;
            param.storeName = $scope.storeName;


            param.advUUID = $scope.advUUID;

            $http({
                url:'/ledou/advertise/update.do',
                method: 'POST',
                data: param
            }).success(function(data){
                if(data.errcode==1){
                    AlertService.alert({success:true,msg:data.errmsg});
                    $timeout(function(){
                        $location.path("/listAd");
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
