(function(){
	'use strict';

    angular
        .module('center')
        .controller('DeviceAddCtrl', DeviceAddCtrl);

    DeviceAddCtrl.$inject = [ '$scope', '$rootScope','$route','$modalInstance','AlertService','shopData','DeviceService','dataService'];

    function DeviceAddCtrl($scope,$rootScope,$route,$modalInstance,AlertService,shopData,DeviceService,dataService){
        var vm = this;
        // 已选择设备数
        vm.choiceNum = 0;
        // 商家名称和商家账号
        vm.storeName = shopData[0].storeName;
        vm.account = shopData[0].account;

        DeviceService.preAdd()
        .then(function(res){
            // console.log(res);
            vm.allData = res.data.generalResults;
        });

        var data = {
            serialNums:[],
            accountPhone: vm.account
        };
        // 全选/反选
        vm.allCheck = function(){
            // 首先判断当前点击的是否为选中状态
            if(vm.allChoice){
                vm.allChoice = false;
            }
            else{
                vm.allChoice = true;
            }
            // 需要包装为jq对象
            var choices = $('.choice');
            data = {
                serialNums:[],
                accountForm: vm.account
            };
            for(var i = 0;i < choices.length;i++){
                if(vm.allChoice){
                    $(choices[i]).prop('checked',true);
                    vm.choiceNum = choices.length;
                    data.serialNums.push(vm.allData[i].serialNum);
                }
                else{
                    $(choices[i]).prop('checked',false);
                    vm.choiceNum = 0;
                }
            }
            // console.log(data);     
        }
        // 单个选择
        vm.check = function(){
            vm.choiceNum = 0;
            if($(this).prop('checked')){
                $(this).prop('checked',false);
            }
            else{
                $(this).prop('checked',true);
            }
            // 需要包装为jq对象
            var choices = $('.choice');
            data = {
                serialNums:[],
                accountPhone: vm.account
            };
            for(var i = 0;i < choices.length;i++){ 
                if($(choices[i]).prop('checked')){
                    vm.choiceNum++;
                    data.serialNums.push(vm.allData[i].serialNum);  
                }
            }
            // console.log(data);
            if(vm.choiceNum < choices.length){
                vm.allChoice = false;
            }
            else if(vm.choiceNum === choices.length){
                vm.allChoice = true;
            }
        }
        vm.close = function(){
            $modalInstance.dismiss('cancel');
        }
        vm.add = function(){
            if(data.serialNums[0]){
                DeviceService.addDevice(data)
                .then(function(res){
                    // console.log(res);
                    if(res.data.flag){
                        AlertService.alert({success:true,msg:"添加成功"});
                    }
                    else{
                        AlertService.alert({success:false,msg:"操作失败，请重新添加"})
                    }
                    $modalInstance.dismiss('cancel');
                    $route.reload();
                })
                .catch(function(err){
                    AlertService.alert({success:false,msg:err})
                    $modalInstance.dismiss('cancel');
                })
            }   
            else{
                AlertService.alert({success:false,msg:"请选择设备序列号"})
            }        
        } 

        vm.search = function(){
            var keyword = {
                keyword: $scope.form.keyword
            };
            DeviceService.searchUnbind(keyword)
            .then(function(res){
                console.log(res);
                vm.allData = res.data.general;
            })
        }   
    }
})()