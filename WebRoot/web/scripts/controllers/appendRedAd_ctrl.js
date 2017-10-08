(function(){
    'use strict';
    angular
        .module('center')
        .controller('AppendRedAdCtrl',AppendRedAdCtrl);
    AppendRedAdCtrl.$inject = [ '$scope', '$rootScope','AdService','AdDao','base64Service','dataService','$modal','utilService','$location','AlertService','$http'];

    function AppendRedAdCtrl($scope,$rootScope,AdService,AdDao,base64Service,dataService,$modal,utilService,$location,AlertService,$http){
        var vm=this;
        if(sessionStorage.proxyType) $scope.proxyType = base64Service.base64decode(sessionStorage.proxyType);
        else if(localStorage.proxyType) $scope.proxyType = base64Service.base64decode(localStorage.proxyType);
        $scope.advUrl="https://";

        //默认选中设备分组
        $scope.mediaType='P';
        $scope.method="device";
        $scope.redPacketMoney=0.66;

        //初始化数量校验正则
        $scope.pattern=/\d{3,}/;
        //初始化最小数量
        $scope.minNum=100;
        //初始化表单数据
        $scope.formData='';

        var param = {};
        //var withdrawalData = {};

        // 配置设备分组
        vm.setDevice= function(){
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
                        return 'surprise';
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
        vm.setArea = function(){
            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAdArea.html',
                controller: 'AppendAdAreaCtrl',
                controllerAs: 'appendAdAreaCtrl',
                size : 'lg',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal',
                resolve: {
                    items: function () {
                        return $scope.area
                    },
                    type:function(){
                        return 'surprise';
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

        $scope.logoUrl = 'images/logo2.png';
        $scope.picUrl = 'images/logo3.png';
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

        //绑定按钮实现上传七牛云 AdDao.getUploadToken返回的json里头包含token和domain
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

        AdService.getWithdrawls()
            .then(function(res){
                $scope.Withdrawlsdata=angular.fromJson(res.data.object);
                $scope.touNum = "";
                $scope.preFee = 0;
            });

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
        });

        vm.setRedPacketMoney=function(){
            if(! $scope.redPacketMoney)$scope.redPacketMoney=0.66;
        }

        vm.setProbability=function(){
            if($scope.probability>10000)$scope.probability=10000;
        }

        $scope.preFeeChange = function(){
            $scope.withdrawalData = angular.fromJson($scope.withdrawal);
        };

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
            if($scope.logoUrl == 'images/logo2.png'){
                AlertService.alert({success:false,msg:"请上传logo图片"});
                return false;
            }
            if($scope.picUrl == 'images/logo3.png'){
                AlertService.alert({success:false,msg:"请上传广告图片"});
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

            param.media_type=$scope.mediaType;
            param.withdrawalId = $scope.withdrawalData.id;
            param.touNum = $scope.touNum;
            param.type='surprise';
            param.pic = $scope.picUrl;
            param.logo = $scope.logoUrl;
            if($scope.advUrl&&$scope.advUrl!='https://')param.advUrl = $scope.advUrl;
            param.beginTime = $scope.begin;
            param.endTime = $scope.end;
            param.storeName = $scope.proxyName;
            //因为都是必填的所以这里不用再判断
            param.preFee = ($scope.touNum *$scope.preFee+$scope.redPacketMoney * $scope.touNum).toFixed(2);
            param.probability=$scope.probability;
            param.red_price=$scope.redPacketMoney*100;
            $scope.formData=angular.toJson(param, true);

            //通过这个设置为表单提交不需要写延时器因为双向绑定优先级高
            Form.attr('onsubmit','return true');

        };

        vm.previewName = function(){
            if($scope.proxyName) {
                angular.element("iframe").contents().find("#head-name").html($scope.proxyName + "的现金红包");
            }else{
                angular.element("iframe").contents().find("#head-name").html("乐豆呀的现金红包");
            }
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

        //动态设置预览部分宽高，保持等比例
        previewSize();
        window.onresize = function(){
            previewSize();
        }
        function previewSize(){
            angular.element(".adPreview").width(angular.element(".panel-preview").height()*0.7);
            if(angular.element(".panel-preview").height()>400){
                angular.element(".panel-preview").height(400);
                angular.element(".adPreview").width(angular.element(".panel-preview").height()*0.7);
            }
        }
    }
})();