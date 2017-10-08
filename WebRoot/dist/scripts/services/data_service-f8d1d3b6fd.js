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
                name:"账号",
                click:true,
                // url:"account",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-user',
                subnav:[
                    {
                        name:"账号管理",
                        click:false,
                        url:"account"
                    },
                    {
                        name:"账号采购",
                        click:false,
                        url:"accountPurchase"
                    },
                    {
                        name:"账号库存",
                        click:false,
                        url:"accountStock"
                    }
                ]
            },
            {
                name:"设备",
                click:false,
                // url:"device",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-hdd',
                subnav:[
                    {
                        name:"设备管理",
                        click:false,
                        url:"device"
                    },
                    {
                        name:"设备采购",
                        click:false,
                        url:"devicePurchase"
                    },
                    {
                        name:"设备库存",
                        click:false,
                        url:"deviceStock"
                    }
                ]
            },
            {
                name:"资产管理",
                click:false,
                // hasSub:true,
                isShow:false,
                isSubShow: false,
                icon:'glyphicon-cog',
                subnav:[
                    {
                        name:"商户明细",
                        click:false,
                        url:"fortune",
                    },
                    {
                        name:"收益明细",
                        click:false,
                        url:"income",
                    },
                    {
                        name:"结算中心",
                        click:false,
                        url:"balance",
                    }
                ]
            },
            {
                name:"个人中心",
                click:false,
                // hasSub:true,
                isShow:false,
                isSubShow: false,
                icon:'glyphicon-home',
                subnav:[
                    {
                        name:"我的账号",
                        click:false,
                        url:"myaccount"
                    }
                ]
            }
            // {name:"数据统计",click:false,url:"/"},
            // {name:"操作历史",click:false,url:"history"}
        ];
        //account界面title
        vm.accounttitles=[
            {name:"商户账号"},
            {name:"商户名称"},
            {name:"商户类型"},
            {name:"账号状态"},
            {name:"分组个数限制"},
            {name:"子商户个数限制"},
            {name:"备注"},
            {name:"创建时间"},
            {name:"操作"}
        ];
        vm.devicetitles=[
            {name:"商户账号"},
            {name:"商户名称"},
            {name:"设备数"},
            {name:"分组数"}
        ];
        vm.deviceDetail={};
        vm.deviceDetailTitles=[
            {name:"设备序列号"},
            {name:"微信ID"},
            // {name:"UUID"},
            // {name:"major"},
            // {name:"minor"},
            // {name:"设备型号"},
            {name:"设备状态"},
            {name:"添加时间"},
            {name:"备注"},
            {name:"操作"}
        ];
        vm.fortuneTitles=[
            {name:"商户账号"},
            {name:"商户名称"},
            {name:"总资产/元"},
            {name:"冻结资产/元"},
            {name:"可用资产/元"},
            {name:"累计充值金额/元"},
            {name:"累计提现金额/元"},
            {name:"累计支出金额/元"},
            {name:"累计抽成金额/元"}
            
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
            devicetitles:vm.devicetitles,
            deviceDetail:vm.deviceDetail,
            deviceDetailTitles:vm.deviceDetailTitles,
            types:vm.types,
            statu:vm.statu,
            balanceStatus:vm.balanceStatus,
            fortuneTitles:vm.fortuneTitles,
            fortuneDetail:vm.fortuneDetail,
            fortuneRechargeTitles:vm.fortuneRechargeTitles,
            fortuneWithdrawnTitles:vm.fortuneWithdrawnTitles,
            fortuneDetailTitles:vm.fortuneDetailTitles,
            fortuneDetailStatus:vm.fortuneDetailStatus,
            conversion:vm.conversion,
            DeviceBindTitles:vm.DeviceBindTitles,
            deviceActiveDates:vm.deviceActiveDates,
            incomeTitles:vm.incomeTitles,
            BalanceTitles:vm.BalanceTitles,
            accountStockTitles:vm.accountStockTitles
            /*list: function () {
                 var deferred = $q.defer();
                 clusterDao.list()
                 .then(function (result) {
                      deferred.resolve(result.data);
                 })
                 .catch(function (e) {
                     deferred.reject(e);
                 });
                 return deferred.promise;
            }*/
        };
    };
})();
