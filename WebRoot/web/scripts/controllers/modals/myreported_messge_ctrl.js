(function(){
    'use strict';

    angular
        .module('center')
        .controller('MyreportedMessgeCtrl', MyreportedMessgeCtrl);

    MyreportedMessgeCtrl.$inject = [ '$scope', '$rootScope','dataService','$location','$route','$modalInstance','utilService','$modal','AlertService','myreportedService','statusData'];

    function MyreportedMessgeCtrl($scope,$rootScope,dataService,$location,$route,$modalInstance,utilService,$modal,AlertService,myreportedService,statusData){
        var vm = this;
        // 表单数据初始化
        $scope.area=statusData.index.fullname;

        var param = {};
        $scope.form=statusData.index;//获取页面列表中的已报人数

        vm.add = function(record) {

            param.rid = statusData.rid;//区域的id
            param.company = $scope.company;//企业名称
            param.contact_name = $scope.contactName;//联系人
            param.contact_way = $scope.contactWay;//联系电话
            param.comment = $scope.comment;//报备（二期改版用）
            param.areaCode = statusData.areaCode;//搜索内容

            var company = document.getElementById("company").value;
            var contactName = document.getElementById("contactName").value;
            var contactWay = document.getElementById("contactWay").value;
            var comment = document.getElementById("comment").value;
            //if (company == "") {
            //    AlertService.alert({success: false, msg: "请输入企业名称"});
            //    return;
            //}
            if (contactName == "") {
                AlertService.alert({success: false, msg: "请输入联系人"});
                return;
            }
            if (contactWay == "") {
                AlertService.alert({success: false, msg: "请输入联系电话"});
                return;
            }
            if(contactName != ""&&contactWay != ""){

                myreportedService.myReport(param)
                    .then(function (res) {
                        if (res) {
                            if (res.data.errcode == 1) {

                                if (res.data.errmsg != '') {
                                    AlertService.alert({success: true, msg: "提交成功"});
                                    $scope.form.report_num +=1;//点击模态框中的提交的时候，页面列表中的已报人数加一
                                }
                                else {
                                    AlertService.alert({success: false, msg: "提交失败，请稍候再试"});
                                }
                            }
                        }
                    })
                    .catch(function (err) {
                        AlertService.alert({success: false, msg: err});
                    });
                $modalInstance.dismiss('cancel');

                //name();
                //$route.reload();
                //$scope.allUser.splice(myreportedCtrl.allUser.indexOf(user.report_num), 1);
                 name.search();

            }
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
    }
})()