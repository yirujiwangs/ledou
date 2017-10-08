(function(){
	'use strict';

    angular
        .module('center')
        .controller('FortuneDetailCtrl', FortuneDetailCtrl);

    FortuneDetailCtrl.$inject = [ '$scope', '$rootScope','dataService','FortuneService','AlertService','$modal','$location','utilService'];

    function FortuneDetailCtrl($scope,$rootScope,dataService,FortuneService,AlertService,$modal,$location,utilService){
        var vm=this;
        vm.fortuneDetail=dataService.fortuneDetail;
        var param = {};
        // 分页
        vm.currentPage = 1;
        vm.totalPage = 1;
        param.startPage = 1;
        param.pageSize = 15;
        param.phone = vm.fortuneDetail.phonenum;
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            FortuneService.getStoreDepositDetail(param)
            .then(function(res){
                // vm.generalNum=res.data.amount;
                vm.allData = res.data.depositWithTaxes;
                vm.currentPage = param.startPage;
            });
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            FortuneService.getStoreDepositDetail(param)
            .then(function(res){
                // vm.generalNum=res.data.amount;
                vm.allData = res.data.depositWithTaxes;
                vm.currentPage = param.startPage;
            });
        }

        var obj={};
        if(utilService.isObjectValueEqual(dataService.fortuneDetail,obj)){
            AlertService.alert({success:false,msg:"请返回上一个界面"})
            return
        }
        vm.titles=dataService.fortuneDetailTitles;
        vm.status=dataService.fortuneDetailStatus;
        vm.conversion=dataService.conversion;
        $scope.form={};
        $scope.searchId='';
        $scope.form.type=vm.status[0].name;

        // FortuneService.getFlowingDetail(vm.fortuneDetail.phoneNum)
        // .then(function(res){
        //     vm.originalItems=res.data.generalHistory;
        //     vm.items=res.data.generalHistory;
        // })
        // .catch(function(err){
        //     AlertService.alert({success:false,msg:err})
        // })
        
        FortuneService.getStoreDepositDetail(param)
        .then(function(res){
            // console.log(res);
            vm.allData = res.data.depositWithTaxes;
            vm.totalPage = res.data.pages;
        })

        vm.search=function(){
            if($scope.searchId==''){
                vm.items=vm.originalItems
            }
            else{
                FortuneService.search({account:vm.fortuneDetail.phoneNum,outTradeNo:$scope.searchId})
                .then(function(res){
                    vm.items=res.data.generalHistory
                })
                .catch(function(err){
                    AlertService.alert({success:false,msg:err})
                })
            }
            
        }
        
        //dataTimepicker相关设置
        moment.locale('zh-cn');
        $scope.configFunction = function configFunction() {
            return {startView: 'month',minView: 'day'};
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.form.startTime=moment(newDate).format("YYYY-MM-DD")          
            $('#dropdown1').dropdown('toggle');
        };
        $scope.inputOnEndTimeSet=function(newDate) {
            $scope.form.endTime=moment(newDate).format("YYYY-MM-DD")          
            $('#dropdown2').dropdown('toggle');
        };
        //end dataTimePicker

        vm.detailSearch=function(){
            FortuneService.detailSearch($scope.form)
            .then(function(res){
            	// console.log(res)
                vm.items=res.data.generalDeposit;
            })
            .catch(function(err){
                if(err=='timeErr'){
                    AlertService.alert({success:false,msg:"查询终止时间不能小于起始时间"})
                }
            })
        }
      

    }
})()