(function(){
    'use strict';

    angular
        .module('center')
        .controller('CheckStoreCtrl', CheckStoreCtrl);

    CheckStoreCtrl.$inject = [ '$scope', '$rootScope','CheckStoreService','CheckStoreDao','dataService','$modal','utilService','$location','AlertService','$http'];

    function CheckStoreCtrl($scope,$rootScope,CheckStoreService,CheckStoreDao,dataService,$modal,utilService,$location,AlertService,$http){
        var vm=this;
        var param = {};
        var canshu=[];
        var url = $location.absUrl();
        if(url.indexOf("?")!=-1)
        {
            var str=url.substr(url.indexOf("?")+1);
            var strs=str.split("&");
            for(var i=0;i<strs.length;i++)
            {
                canshu.push(strs[i].split("=")[1]);
            }
        }
        $scope.cardFrontUrl= 'images/card.png';
        $scope.cardBackUrl='images/card.png';
        $scope.counterUrl= 'images/logo3.png';
        $scope.permitUrl='images/logo3.png';
        $scope.groupUrl= 'images/logo3.png';

        param.check_status =parseInt(canshu[0]);
        param.store_id =parseInt(canshu[1]);
        //七牛云上传
        function upload(result,target,x,y,cb){
            CheckStoreDao.uploadInit($scope, result, target, function (up, file, info) {
                var res = JSON.parse(info);
                var link = res.domain + res.key + '?imageView2/2/w/'+x+'/h/'+y+'/interlace/0/q/100';
                $scope.$apply(function () {
                    cb($scope,link);
                    AlertService.alert({success:true,msg:"上传成功"});
                });
            },
            function(up, err, errTip){
                AlertService.alert({success:false,msg:"上传资源大小不能超过5 MB"});
            });
        }
        CheckStoreDao.getUploadToken().then(function (result) {
            upload(result,'cardFront',640,480,function($scope,link){
                $scope.cardFrontUrl = link;
            });
            upload(result,'cardBack',640,480,function($scope,link){
                $scope.cardBackUrl = link;
            });
            upload(result,'counter',640,480,function($scope,link){
                $scope.counterUrl = link;
            });
            upload(result,'permit',640,480,function($scope,link){
                $scope.permitUrl = link;
            });
            upload(result,'group',640,480,function($scope,link){
                $scope.groupUrl = link;
            });
        });

        vm.submit=function(){
            if( $scope.cardFrontUrl=='images/card.png'|| $scope.cardBackUrl=='images/card.png'|| $scope.counterUrl== 'images/logo3.png'|| $scope.permitUrl=='images/logo3.png'){
                AlertService.alert({success:false,msg:"请正确上传所需照片，确保无遗漏项"});
                return;
            }
            else{
                if($scope.groupUrl!='images/logo3.png'){
                    param.group_pic=$scope.groupUrl;
                }
                param.permit_pic=$scope.permitUrl;
                param.counter_pic=$scope.counterUrl;
                param.id_pic_other=$scope.cardBackUrl;
                param.id_pic=$scope.cardFrontUrl;
            }


            $http({
                method:'POST',
                url:'/ledou/manageMerchant/userCerCheck.do',
                data:param
            }).success(function(res) {
                if(res){
                    if (res.errcode == -1) {
                        AlertService.alert({success:false,msg:"验证失败，您的信息有误"});
                    }
                    else if(res.errcode==1){
                        AlertService.alert({success:true,msg:"验证成功，请耐心等待审核结果"});
                        setTimeout(function(){
                            $location.path('/manageMerchant')
                        },1000)
                    }
                }
            }).error(function(res){
                AlertService.alert({success:false,msg:"网络错误"});
            });
        }
    }
})();