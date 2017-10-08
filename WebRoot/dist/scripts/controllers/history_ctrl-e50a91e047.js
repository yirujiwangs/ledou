/**
*   @author:whx
*   @file-name:history_ctrl.js
*   @.1     history     whx     creat
*/

(function(){
	'use strict';               //使用js严格模式

    angular
        .module('center')       //创建、获取、注册angular中的模块，当传递两个或者多个参数时，创建一个新的module，当传递一个参数时，使用已经存在的module
        .controller('HistoryCtrl', HistoryCtrl);

    //控制器通过$scope对象将视图和模型胶合在一起
    HistoryCtrl.$inject = ['$scope', '$rootScope','HistoryService','AlertService','dataService'];   //注入四个依赖

    function HistoryCtrl($scope,$rootScope,HistoryService,AlertService,dataService){
        for(var i=0;i<dataService.buttons.length;i++){
            dataService.buttons[i].click=false
        }
        dataService.buttons[4].click=true;
        var vm=this;
        
        vm.types = ['选择操作类型', '账号操作', '设备操作', '财务操作'];
        vm.staffs = ['选择操作员'];
        vm.customers = ['选择商家'];
        vm.conversion=dataService.conversion

        //表单数据存储
        $scope.form={type:'选择操作类型',
                    staff:'选择操作员', 
                    customer:'选择商家', 
                    startTime:'', 
                    endTime:''};
        

        HistoryService.listHistorys()
        .then(function(res){
            vm.historys = res.data.generalData;
            res.data.admin.forEach(function(e){
                vm.staffs.push(e);
            });
            res.data.user.forEach(function(e){
                vm.customers.push(e);
            });
        }); 

        vm.historyFilterSubmit = function(){
            
            var submitForm={};
            if ($scope.form.type === '选择操作类型'){
                submitForm.type = 'all';
            }else{
                submitForm.type = $scope.form.type;
            }
            if ($scope.form.staff === '选择操作员'){
                submitForm.adminName = 'all';
            }else{
                submitForm.adminName = $scope.form.staff;
            }
            if ($scope.form.customer === '选择商家'){
                submitForm.username = 'all';
            }else{
                submitForm.username = $scope.form.customer;
            }
            if ($scope.form.startTime !== ''){
                submitForm.startTime = $scope.form.startTime;
            }
            if ($scope.form.endTime !== '')
            {
                submitForm.endTime = $scope.form.endTime;
            }

            if (($scope.form.startTime !== '') && ($scope.form.endTime !== '')){
                if(!moment(submitForm.endTime).isAfter(submitForm.startTime)){
                    AlertService.alert({success:false,msg:'截止时间大于起始时间'});
                }
            }

            HistoryService.filterHistorys(submitForm)
            .then(function(res){
                vm.historys = res.data.generalData;
            })
            .catch(function(err){
                AlertService.alert({success:false,msg:err});
            });
        }


        //dataTimepicker相关设置
        moment.locale('zh-cn');
        $scope.configFunction = function configFunction() {
            //相关设置可根据具体需求更改
            return {startView: 'month',minView: 'day'};
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.form.startTime=moment(newDate).format("YYYY-MM-DD")          
            $('#dropdown1').dropdown('toggle');
        };
        
        $scope.inputOnEndTimeSet = function (newDate) {
        $scope.form.endTime=moment(newDate).format("YYYY-MM-DD")          
        $('#dropdown2').dropdown('toggle');
        //end dataTimePicker
    };
    }

})();