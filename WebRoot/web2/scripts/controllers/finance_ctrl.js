(function(){
	'use strict';

    angular
        .module('center')
        .controller('FinanceCtrl',FinanceCtrl);

    FinanceCtrl.$inject = [ '$scope', '$rootScope','financeService','dataService','AlertService','$modal','$location'];

    function FinanceCtrl($scope,$rootScope,financeService,dataService,AlertService,$modal,$location){
        var vm=this;
        vm.titles=dataService.financeTitles;

        // 分页
        vm.currentPage = 1;
        var param = {};
        param.startPage = 1;
        param.pageSize = 8;
        vm.pre = function(){
            if(param.startPage > 1){
                param.startPage--;
            }
            else{
                param.startPage = 1;
            }
            lodePage();
        }
        vm.next = function(){
            if(param.startPage < vm.totalPage){
                param.startPage++;
            }
            else{
                param.startPage = vm.totalPage;
            }
            lodePage();
        }
        vm.jump = function(){
            if(param.startPage <= $scope.jumppage ||  vm.totalPage >=$scope.jumppage){
                console.log($scope.jumppage,"跳转成功");
                param.startPage=$scope.jumppage;
                vm.currentPage=$scope.jumppage;

            }
            lodePage();
        }

        //todo
        vm.search = function(){
            param.startPage =1;
            if($scope.form.keyword)param.keyword = $scope.form.keyword;
            else delete param.keyword;
            lodePage();
        }

        function lodePage(){
            financeService.listFinanceSearch(param)
                .then(function(res){
                    // console.log(res);
                    vm.allStore=res.data.accountFinanceList;
                    vm.totalPage = res.data.pages;
                    vm.currentPage = param.startPage;
                })
        }

        vm.generalNum ={};
        financeService.listFinance(param)
        .then(function(res){
            console.log(res);
            vm.allStore=res.data.accountFinanceList;
            vm.totalPage = res.data.pages;
            vm.currentPage = param.startPage;
            vm.generalNum.combinationDeviceBenefit = res.data.combinationDeviceBenefit;
            vm.generalNum.combinationAdBenefit = res.data.combinationAdBenefit;
            vm.generalNum.storeAdDepositBenefit = res.data.storeAdDepositBenefit;
            vm.generalNum.indirectDeviceBenefit = res.data.indirectDeviceBenefit;
        })

        //dataTimepicker相关设置
        moment.locale('zh-cn');
        $scope.form = {};
        $scope.configFunction = function configFunction() {
            return {
                startView: 'month',
                minView: 'month'
            };
        };
        $scope.inputOnTimeSet = function (newDate) {
            $scope.form.startTime=moment(newDate).format("YYYY-MM")
            $('#dropdown1').dropdown('toggle');
        };
        $scope.inputOnEndTimeSet=function(newDate) {
            console.log(newDate)
            $scope.form.endTime=moment(newDate).format("YYYY-MM")
            $('#dropdown2').dropdown('toggle');
        };
        //end dataTimePicker

        function checkMonth(){
            if(!$scope.form.startTime){
                AlertService.alert({success:false,msg:"请选择起始月份"});
                return false;
            }
            else if(!$scope.form.endTime){
                AlertService.alert({success:false,msg:"请选择结束月份"});
                return false;
            }
            else{
                var startDate=new Date(Number($scope.form.startTime.slice(0,4)),Number($scope.form.startTime.slice(5))-1,1);
                var endDate=new Date(Number($scope.form.endTime.slice(0,4)),Number($scope.form.endTime.slice(5))-1,1);
                var nowDate=new Date();
                if(endDate.getTime()!=startDate.getTime()){
                    AlertService.alert({success:false,msg:"结束月份应等于起始月份"});
                    return false;
                }
                else if(endDate.getTime()>nowDate.getTime()){
                    AlertService.alert({success:false,msg:"结束月份大于当前月份"});
                    return false;
                }
                else{
                    return true
                }
            }
        }

        //清算代理商收入
        vm.submit=function(){
            var obj = {};
            obj.type='n';
            obj.startMonth=$scope.form.startTime;
            obj.endMonth=$scope.form.endTime;
            if(!checkMonth())return;
            financeService.clearIncome(obj)
                .then(function(res){
                    if(res.data.errcode==1){
                        AlertService.alert({success:true,msg:res.data.errmsg});
                    }
                    else if(res.data.errcode==-1){
                        AlertService.alert({success:false,msg:res.data.errmsg});
                    }
                    else{
                        AlertService.alert({success:false,msg:res.data.errmsg});
                    }
                })
        }

        vm.getDetail=function(index){
            $location.path('/financeDetail').search({
                corporationid: vm.allStore[index].corporationid,
                phone: vm.allStore[index].phone
            });
        }
        vm.incomeDetail =function(){
            $location.path('/financeIncomeDetail');
        }
    }
})()