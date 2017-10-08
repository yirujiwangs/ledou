(function () {
    'use strict';

    angular
    .module('center')
    .factory('dataService', dataService);

    dataService.$inject = ['$q'];

    function dataService($q) {
        var vm=this;
        vm.buttons=[
        {
            name:"总览",
            click:true,
                // url:"account",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-user',
                subnav:[
                {
                    name:"管理面板 ",
                    click:false,
                    url:"manage"
                }
                ]
            },
            {
                name:"账号",
                click:false,
                // url:"device",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-hdd',
                subnav:[
                {
                    name:"账号管理",
                    click:false,
                    url:"account"
                },
                {
                    name:"账号订单",
                    click:false,
                    url:"accountPurchase"
                }
                ]
            },
            {
                name:"设备",
                click:false,
                // hasSub:true,
                isShow:false,
                isSubShow: false,
                icon:'glyphicon-cog',
                subnav:[
                {
                    name:"设备管理",
                    click:false,
                    url:"device"
                },
                {
                    name:"订单管理",
                    click:false,
                    url:"deviceStock"
                }
                ]
            },
            {
                name:"财务",
                click:false,
                // hasSub:true,
                isShow:false,
                isSubShow: false,
                icon:'glyphicon-home',
                subnav:[
                {
                    name:"财务管理",
                    click:false,
                    url:"finance"
                },
                {
                    name:"结算中心",
                    click:false,
                    url:"refund"
                }
                ]
            },
            {
                name:"报备",
                click:true,
                // url:"account",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-user',
                subnav:[
                    {
                        name:"报备管理 ",
                        click:false,
                        url:"report"
                    }
                ]
            },
            // {name:"数据统计",click:false,url:"/"},
            // {name:"操作历史",click:false,url:"history"}
            ];
            //account界面title
            vm.accounttitles=[
            {name:"运营商账号"},
            {name:"运营商名称"},
            {name:"账号状态"},
            // {name:"可用/总商户数"},
            {name:"可用/总普通商户数"},
            // {name:"可用/总平台商户数"},
            {name:"代理区域"},
            {name:"管理员openID"},
            {name:"备注"},
            {name:"创建时间"},
            {name:"操作"}
            ];
            vm.accountDetailTitles=[{
                name:"商家账号"
            },{
                name:"商户名称"
            },{
                name:"商户类型"
            },{
                name:"帐号状态"
            },{
                name:"分组个数限制"
            },{
                name:"子商户个数限制"
            },{
                name:"备注"
            },{
                name:"创建时间"
            },{
                name:"操作"
            }];
            vm.accountDetailId = {};
            vm.accountPurchaseTitles = [{
                name:"交易时间"
            },{
                name:"交易单号"
            },{
                name:"运营商账号"
            },{
                name:"运营商名称"
            },{
                name:"账号类型"
            },{
                name:"账号个数"
            },{
                name:"交易金额"
            },{
                name:"状态"
            }]
            vm.devicetitles=[
                {name:"运营商账号"},
                {name:"运营商名称"},
                {name:"代理区域"},
                {name:"累计采购/累计激活"},
                {name:"今日活跃/今日激活"},
                {name:"昨日活跃/昨日激活"},
                {name:"备注"},
            ];
            
            vm.deviceUnBindTitles =[
                {name:"设备序列号"}, 
                {name:"微信ID"},
                {name:"添加时间"},
                {name:"设备状态"},
                {name:"备注"},
                {name:"操作"}
            ];
            vm.deviceDetail={};
            vm.deviceDetailAccount={};
            vm.deviceDetailTitles=[
                {name:"商家账号"},
                {name:"商家名称"},
                {name:"设备数"},
                {name:"分组数"},
                {name:"备注"},
                {name:"操作"}
            ];
            vm.financeTitles=[
                {name:"运营商账号"},
                {name:"运营商名称"},
                {name:"代理区域"},
               // {name:"组合采购奖励"},
               // {name:"间接采购奖励"},
                {name:"广告收益"},
                {name:"充值收益"},
                {name:"营销收益"},
                {name:"操作"}
            ];
            vm.financeAccount={};
            vm.financeDetailTitles=[
                {name:"商户账号"},
                {name:"商户名称"},
                {name:"总资产/元"},
                {name:"冻结资产/元"},
                {name:"可用资产/元"},
                {name:"累计充值金额/元"},
                {name:"累计提现金额/元"},
                {name:"累计支出金额/元"},
                {name:"累计抽成金额/元"},
                {name:"操作"}        
            ];
            vm.types=[
                {name:"LD-B1"},
                {name:"LD-B2"}
            ];
            vm.statu=[
                {name:"设备状态"},
                {name:"使用中"},
                {name:"未使用"}
            ];
            vm.balanceStatus=[
                {name:"全部"},
                {name:"待结算"},
                {name:"已结算"},
                {name:"已拒绝"}
            ];
            vm.financeStoreDetailTitles=[
                {name:"申请时间"},
                {name:"交易单号"},
                {name:"充值金额/元"},
                {name:"收益金额/元"},
                {name:"备注"}
            ];
            vm.financeIncomeDetailTitles =[{
                name:"交易时间"
            },{
                name:"交易单号"
            },{
                name:"商家账号"
            },{
                name:"商家名称"
            },{
                name:"充值金额"
            },{
                name:"收益金额"
            },{
                name:"备注"
            }];
            vm.fortuneDetail={};
            vm.fortuneRechargeTitles=[
                {name:"申请时间"},
                {name:"交易单号"},
                {name:"商户账号"},
                {name:"商户名称"},
                {name:"充值金额/元"},
                {name:"备注"}
            ]
            vm.fortuneWithdrawnTitles=[
                {name:"申请时间"},
                {name:"交易单号"},
                {name:"商户账号"},
                {name:"商户名称"},
                {name:"提现金额/元"},
                {name:"申请人"},
                {name:"支付宝账号"},
                {name:"备注"},
                {name:"操作"}
            ];
            vm.fortuneDetailTitles=[
                {name:"申请时间"},
                {name:"交易单号"},
                {name:"商户账号"},
                {name:"商户名称"},
                {name:"充值金额/元"},
                {name:"收益金额/元"},
                {name:"备注"}
            ];
            vm.fortuneDetailStatus=[
                {name:"操作"},
                {name:"提现失败"},
                {name:"提现成功"},
                {name:"充值成功"},
            ];
            vm.DeviceBindTitles=[
                {name:"设备序列号"},
                {name:"微信ID"},
                // {name:"UUID"},
                // {name:"major"},
                // {name:"minor"},
                // {name:"设备型号"},
                {name:"添加时间"},
                // {name:"状态"},
                // {name:"备注"},
                {name:"操作"}
            ];
            vm.incomeTitles=[
                {name:"申请时间"},
                {name:"交易单号"},
                {name:"商户账号"},
                {name:"商户名称"},
                {name:"充值金额/元"},
                {name:"收益金额/元"},
                {name:"备注"}
            ];
            vm.BalanceTitles=[
                {name:"结算时间"},
                {name:"结算金额/元"},
                {name:"结算状态"},
                {name:"操作"}
                // {name:"结算账号"}
            ];
            vm.accountStockTitles=[
                {name:"采购时间"},
                {name:"账号类型"},
                {name:"账号个数"},
                {name:"充值金额/元"}
            ];
           
            vm.conversion=function(string){
                return moment(string).format("YYYY-MM-DD");
            }
            vm.deviceActiveDates={};
            return {
                buttons:vm.buttons,
                accounttitles:vm.accounttitles,
                accountPurchaseTitles:vm.accountPurchaseTitles,
                devicetitles:vm.devicetitles,
                deviceDetail:vm.deviceDetail,
                accountDetailId: vm.accountDetailId,
                deviceDetailTitles:vm.deviceDetailTitles,
                deviceDetailAccount:vm.deviceDetailAccount,
                deviceUnBindTitles:vm.deviceUnBindTitles,
                types:vm.types,
                statu:vm.statu,
                balanceStatus:vm.balanceStatus,
                financeTitles:vm.financeTitles,
                fortuneDetailTitles:vm.fortuneDetailTitles,
                financeAccount:vm.financeAccount,
                financeDetailTitles:vm.financeDetailTitles,
                financeStoreDetailTitles:vm.financeStoreDetailTitles,
                financeIncomeDetailTitles: vm.financeIncomeDetailTitles,
                fortuneDetail:vm.fortuneDetail,
                fortuneRechargeTitles:vm.fortuneRechargeTitles,
                fortuneWithdrawnTitles:vm.fortuneWithdrawnTitles,
                //fortuneDetailTitles:vm.fortuneDetailTitles,
                fortuneDetailStatus:vm.fortuneDetailStatus,
                conversion:vm.conversion,
                DeviceBindTitles:vm.DeviceBindTitles,
                deviceActiveDates:vm.deviceActiveDates,
                incomeTitles:vm.incomeTitles,
                BalanceTitles:vm.BalanceTitles,
                accountStockTitles:vm.accountStockTitles
         };
     };
 })();
