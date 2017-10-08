(function(){
    'use strict';

    angular
        .module('center')
        .controller('AppendPageListAdCtrl', AppendPageListAdCtrl);

    AppendPageListAdCtrl.$inject = [ '$scope', '$rootScope','AdService','AdDao','dataService','$modal','utilService','$location','AlertService','$http','$sce'];

    function AppendPageListAdCtrl($scope,$rootScope,AdService,AdDao,dataService,$modal,utilService,$location,AlertService,$http,$sce){
        var vm=this;
        $scope.advUrl="https://";
        $scope.mediaType="P";
        $scope.method="device";
        //默认加载完毕
        $scope.loading=false;
        //启动页广告所以为screen
        $scope.adType="screen";
        //检查是否是图片或者是图片音频类型
        $scope.checkPM=function(){
            if($scope.mediaType=="P"|| $scope.mediaType=="M")return true
            else if($scope.mediaType=="V") return false
        }
        //初始化数量校验正则
        $scope.pattern=/\d{3,}/;
        //初始化最小数量
        $scope.minNum=100;
        $scope.formData='';

        //input type=hidden提交，ng-if已经判断mediaType为什么类型提交什么东西
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
                        return 'screen';
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
                    items:function(){
                        return $scope.area
                    },
                    type:function(){
                        return 'screen';
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
        $scope.picUrl = '';
        $scope.audioUrl =$sce.trustAsResourceUrl('') ;
        $scope.videoUrl =$sce.trustAsResourceUrl('');
        $scope.m_url='';
        $scope.v_url='';
        $scope.$watch("m_url", function(n, o){
            $scope.audioUrl =$sce.trustAsResourceUrl( n);
        });
        $scope.$watch("v_url", function(n, o){
            $scope.videoUrl =$sce.trustAsResourceUrl( n);
        });
        // 七牛云上传
        function upload(result,target,x,y,cb,maxSize,reg){
            if(!maxSize)maxSize=100;
            AdDao.uploadInit($scope, result, target,function(up, file){
                //$scope属性改变，但DOM不刷新，可能是和JQ或者七牛云SDK冲突，所以手动触发
                $scope.$apply(function () {
                   if(target=='uploadVideo')$scope.loading=true;
                });
            },
            function (up, file, info) {
                var res = JSON.parse(info);///2/w/530/h/300/interlace/0/q/100
                if(target=='uploadLogo')var link = res.domain + res.key + '?imageView2/2/w/'+x+'/h/'+y+'/interlace/0/q/100';
                else var link = res.domain + res.key;
                $scope.$apply(function () {
                    cb($scope,link);
                    $scope.loading=false;
                    if(reg&&!reg.test(link)){
                        //格式不对的清空上传
                        if($scope.mediaType=='M'){
                            $scope.m_url='';
                            $scope.audioUrl=''
                        }
                        else if($scope.mediaType=='V'){
                            $scope.v_url='';
                            $scope.videoUrl='';
                        }
                        AlertService.alert({success:false,msg:"上传文件格式错误"});
                        return;
                    }
                    AlertService.alert({success:true,msg:"上传成功"});
                });
            },
             function(up, err, errTip){
                 $scope.$apply(function () {
                     $scope.loading = false;
                     if(err.code==-600)AlertService.alert({success: false, msg: "上传资源大小不能超过" + maxSize + " MB"});
                     else if(err.code==-200)AlertService.alert({success: false, msg: "文件名已存在"});
                     else AlertService.alert({success: false, msg: "上传资源错误"});
                 });
             },maxSize);
        }

        AdDao.getUploadToken().then(function (result) {
            var mPattern=/.*mp3$/;
            var vPattern=/.*mp4$/;
            upload(result,'uploadPic',1000,750,function($scope,link){
                $scope.picUrl = link;
            });
            upload(result,'uploadAudio',1000,750,function($scope,link){
                $scope.m_url=link;
            },1,mPattern);
            upload(result,'uploadVideo',1000,750,function($scope,link){
                $scope.v_url=link;
            },5,vPattern);
        });
        AdService.getWithdrawls()
            .then(function(res){
                $scope.Withdrawlsdata=angular.fromJson(res.data.object);
                $scope.touNum = "";
                $scope.preFee = 0;
                //$scope.selected=  $scope.Withdrawlsdata[1];
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
        $scope.preFeeChange = function(){
            $scope.withdrawalData = angular.fromJson($scope.withdrawal);
        };

        vm.submit = function(){
            //初始化默认不提交这个是防止重复提交不走下面逻辑的问题
            var Form = angular.element('#fromId');
            Form.attr('onsubmit','return false');

            if(!$scope.picUrl){
                AlertService.alert({success:false,msg:"请上传启动页图片"});
                return;
            }
            if(!$scope.m_url&&$scope.mediaType=='M'){
                AlertService.alert({success:false,msg:"请上传启动页音频"});
                return;
            }
            if($scope.mediaType=='M'&&!/.*mp3$/.test($scope.m_url)){
                AlertService.alert({success:false,msg:"音频只支持mp3格式"});
                return;
            }
            if(!$scope.v_url&&$scope.mediaType=='V'){
                AlertService.alert({success:false,msg:"请上传启动页视频"});
                return;
            }
            if($scope.mediaType=='V'&&!/.*mp4$/.test($scope.v_url)){
                AlertService.alert({success:false,msg:"视频只支持mp4格式"});
                return;
            }
            if(!$scope.withdrawalData.id){
                AlertService.alert({success:false,msg:"请选择计费方式"});
                return;
            }
            if(!$scope.begin||!$scope.end){
                AlertService.alert({success:false,msg:"起始时间和结束时间不能为空"});
                return;
            }
            if($scope.begin>$scope.end){
                AlertService.alert({success:false,msg:"起始时间不能大于结束时间"});
                return;
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

            param.media_type=$scope.mediaType;
            param.pic = $scope.picUrl;
            if($scope.mediaType=='P'){
                delete param.m_url;
                delete param.v_url;
            }
            if($scope.m_url&&$scope.mediaType=='M'){
                param.m_url = $scope.m_url;
                delete param.v_url;
            }
            if($scope.v_url&&$scope.mediaType=='V'){
                param.v_url = $scope.v_url;
                delete param.m_url;
            }
            param.withdrawalId = $scope.withdrawalData.id;
            param.touNum = $scope.touNum;
            param.type=$scope.adType;

            if($scope.advUrl&&$scope.advUrl!='https://')param.advUrl = $scope.advUrl;
            param.beginTime = $scope.begin;
            param.endTime = $scope.end;
            param.preFee = ($scope.touNum *$scope.preFee).toFixed(2);

            $scope.formData=angular.toJson(param, true);

            console.log(param);

            //通过这个设置为表单提交
            Form.attr('onsubmit','return true');

            var editSqlModalInstance = $modal.open({
                templateUrl: 'views/modals/appendAd_pay.html',
                controller: 'AppendAdPayCtrl',
                controllerAs: 'AppendAdPayCtrl',
                backdrop: 'static',
                windowClass: 'overflow-y-auto chart-modal'
            });
        };

        //动态设置预览部分宽高，保持等比例
        angular.element(".adPreview").width(angular.element(".panel-preview").height()*0.7);
        window.onresize = function(){
            console.log(angular.element(".panel-preview").height());
            angular.element(".adPreview").width(angular.element(".panel-preview").height()*0.7);
        }

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
    }
})();