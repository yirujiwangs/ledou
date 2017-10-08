(function () {
    'use strict';

    angular
        .module('center')
        .factory('dataService', dataService);

    dataService.$inject = ['$q'];

    function dataService($q) {
        var vm=this;
        vm.buttons=[
            //{
            //    name:"账号",
            //    click:true,
            //    // url:"account",
            //    // hasSub:false,
            //    isShow:false,
            //    icon:'glyphicon-user',
            //    subnav:[
            //        {
            //            name:"账号管理",
            //            click:false,
            //            url:"account"
            //        },
            //        {
            //            name:"账号采购",
            //            click:false,
            //            url:"accountPurchase"
            //        },
            //        {
            //            name:"账号库存",
            //            click:false,
            //            url:"accountStock"
            //        }
            //    ]
            //},
          /*  {
                name:"经销商",
                click:false,
                isShow:false,
                icon:'glyphicon-cog',
                subnav:[
                    {
                        name:"经销商管理",
                        click:false,
                        url:"dealer"
                    }
                ]
            },*/

            {
                name:"设备",
                click:false,
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
                        name:"订单管理",
                        click:false,
                        url:"deviceStock"
                    }
                ]
            },
            //新增商户板块
            {
                name:"商户",
                click:false,
                isShow:false,
                icon:'glyphicon-briefcase',
                subnav:[
                    {
                        name:"商户管理",
                        click:false,
                        url:"manageMerchant"
                    }
                ]
            },
            {
                name:"拓展服务商",
                click:false,
                // url:"device",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-cog',
                subnav:[
                    {
                        name:"拓展业绩",
                        click:false,
                        url:"extendedProvider"
                    },
                    {
                        name:"下级服务商",
                        click:false,
                        url:"juniorProvider"
                    }
                ]
            },
            {
                name:"报备",
                click:false,
                isShow:false,
                icon:'glyphicon-hdd',
                subnav:[
                    {
                        name:"报备列表",
                        click:false,
                        url:"reported"
                    },
                    {
                        name:"我要报备",
                        click:false,
                        url:"myreported"
                    }
                ]
            },
            {
                name:"营销",
                click:false,
                // url:"account",
                // hasSub:false,
                isShow:false,
                icon:'glyphicon-credit-card',
                subnav:[
                    {
                        name:"图片广告",
                        click:false,
                        url:"listAd"
                    },
                    {
                        name:"惊喜红包广告",
                        click:false,
                        url:"listRedAd"
                    },
                    {
                        name:"启动页广告",
                        click:false,
                        url:"startPageListAd"
                    },
                    {
                        name:"吸粉红包",
                        click:false,
                        url:"attractFansRedPacket"
                    }/*,{
                        name:"卡劵",
                        click:false,
                        url:"listCoupon"
                    }*/
                ]
            },
            {
                name:"资产管理",
                click:false,
                // hasSub:true,
                isShow:false,
                isSubShow: false,
                icon:'glyphicon-usd',
                subnav:[
                    //{
                    //    name:"商户明细",
                    //    click:false,
                    //    url:"fortune",
                    //},
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
        vm.reportedtitles=[
            {name:"报备区域"},
            {name:"企业名称"},
            {name:"报备时间"},
            {name:"联系人"},
            {name:"联系电话"},
            {name:"签约状态"},
            {name:"备注"}
        ];
        vm.dealertitles=[
            {name:"经销商账号"},
            {name:"经销商名称"},
            {name:"账号状态"},
            {name:"已用/库存商户数"},
            {name:"代理商/经销商返利"},
            {name:"设备数"},
            {name:"备注"},
            {name:"创建时间"},
            {name:"操作"}
        ];
        vm.listAdTitles=[
            // {name:"广告编号"},
            {name:"广告图片"},
            {name:"商户logo"},
            {name:"商户名称"},
            {name:"投放策略"},
            {name:"投放时间"},
            {name:"展示量/投放量"},
            {name:"计费模版"},
            {name:"投放预算"},
            {name:"状态"},
            {name:"创建时间"},
            {name:"操作"}        
        ];
        vm.listRedAdTitles=[
            {name:"广告图片"},
            {name:"商户logo"},
            {name:"商户名称"},
            {name:"投放策略"},
            {name:"投放时间"},
            {name:"领取量/投放量"},
            {name:"计费模版"},
            {name:"红包概率"},
            {name:"红包金额"},
            {name:"状态"},
            {name:"创建时间"},
            {name:"操作"}
        ];
        vm.startPageListAdTitles=[       //新加
            // {name:"广告编号"},
            {name:"媒体展示"},
            {name:"投放策略"},
            {name:"投放时间"},
            {name:"展示量/投放量"},
            {name:"计费模版"},
            {name:"投放预算"},
            {name:"状态"},
            {name:"创建时间"},
            {name:"操作"}
        ];
        //红包列表
        vm.attractFansRedPacketTitles=[
            {name:"公众号logo"},
            {name:"	公众号名称"},
            {name:"	红包名称"},
            {name:"投放策略"},
            {name:"留存量"},
            {name:"投放量"},
            {name:"剩余库存量"},
            {name:"投放预算"},
            {name:"状态"},
            {name:"投放时间"},
            {name:"操作"}
        ];
        vm.authorizationTitles=[
            {name:"公众号logo"},
            {name:"	公众号名称"},
            {name:"公众号类型"},
            {name:"公众号运营属性"},
            {name:"当前状态"},
            {name:"授权时间"},
            {name:"操作"}
        ];
        //卡劵列表
        vm.listCouponTitles=[
            {name:"名称"},
            {name:"卡劵"},
            {name:"卡劵"},
            {name:"卡劵"},
            {name:"卡劵"},
            {name:"卡劵"},
            {name:"卡劵"},
        ];
        vm.allDeviceTitles=[
            {name:"设备序列号"},
            {name:"设备类型"},
            {name:"激活状态"},
            {name:"所在分组"},
            {name:"自定义设置"},
            {name:"门店名称"},
            {name:"门店地址"},
            //{name:"备注"},
            //{name:"时间"},
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
            {name:"激活时间"},
            {name:"备注"},
            // {name:"操作"}
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
        //vm.deliverycompany=[
        //    {name:"全部"},
        //    {name:"申通快递"},
        //    {name:"圆通快递"},
        //    {name:"韵达快递"},
        //    {name:"中通快递"},
        //    {name:"顺丰快递"},
        //    {name:"宅急送快运"},
        //    {name:"汇通快递"},
        //    {name:"鑫飞鸿速递"},
        //    {name:"天天快递"},
        //    {name:"星晨急便"},
        //];
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
        //manageMerchant门店列表
        vm.manageMerchantTitles=[
            {name:"	门店名称"},
            {name:"	门店地址"},
            {name:"	联系电话"},
            {name:"行业类目"},
            {name:"所属商户（昵称）"},
            {name:"	创建时间"},
            {name:"	状态"},
            {name:"	操作"}
        ];
        //income自有设备品牌红包title
        vm.incomeAdfirstTitles=[
            {name:"日期"},
            {name:"活跃设备数"},
            {name:"累计激活数"},
            {name:"品牌红包金额"},
            {name:"服务商平台自定义红包分成"},
            {name:"操作"}
        ];
        //income设备激活记录title
        vm.activerecordTitles=[
            {name:"日期"},
            {name:"激活数"},
            {name:"服务商平台激活补贴收益"},
            {name:"操作"}
        ];
        //income直接推荐设备品牌红包title
        vm.incomeAdscend=[
            {name:"日期"},
            {name:"活跃设备数"},
            {name:"累计激活数"},
            {name:"品牌红包发放金额"},
           /* {name:"40%分成金额"}*/
        ];
        //income组合采购设备记录title
        vm.incomeAdthird=[
            {name:"日期"},
            {name:"服务商账号"},
            {name:"服务商名称"},
            {name:"设备采购数量"}
        ];
        //income间接推荐设备采购记录title
        vm.incomeAdTitles=[
            {name:"日期"},
            {name:"服务商账号"},
            {name:"服务商类型"},
            {name:"设备数量"}
        ];
        //income商户广告金充值title
        vm.incomeTitles=[
            {name:"日期"},
            {name:"充值金额"},
            {name:"累计充值人次"},
            {name:"操作"}
        ];
        //income商户服务费分成奖励title
        vm.incomeStoreSharingTitles=[
            {name:"日期"},
            {name:"商户数"},
            {name:"服务费总提成"},
            {name:"操作"}
        ];
        //income加盟费记录title
        vm.incomeInitialFeeTitles=[
            {name:"代理时间"},
            {name:"	代理商账号"},
            {name:"区域"},
            {name:"	代理商手机号"},
            {name:"签约方式（绿豆芽公司/市级代理）"}
       ];
        //income下级采购记录title
        vm.juniorPurchaseTitles=[
            {name:"日期"},
            {name:"本月采购总数"},
            {name:"采购奖励"},
            {name:"操作"}
        ];
        //income辖区设备品牌红包title
        vm.districtRedTitles=[
            {name:"日期"},
            {name:"活跃设备数"},
            {name:"累计激活数"},
            {name:"品牌红包金额"},
            {name:"服务商平台自定义红包分成"},
            {name:"操作"}
        ];
        //income辖区商户次年服务费title
        vm.districtBenefitTitles=[
            {name:"日期"},
            {name:"商户数"},
            {name:"服务费总提成"},
            {name:"操作"}
        ];
        vm.incomeDealerTitles=[
            {name:"日期"},
            {name:"经销商激活设备数"},
            {name:"激活设备返利"},
            {name:"操作"}
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
        vm.extendedProviderTitles=[
            {name:"开户日期"},
            {name:"代理商手机号"},
            {name:"推荐类型"},
            {name:"上月/本月采购量"},
            {name:"累计采购量"}
        ];
        vm.juniorProviderTitles=[
            {name:'代理区县'},
            {name:'代理时间'},
            {name:'代理商名称'},
            {name:'代理商手机号'},
            {name:'本月/累计设备采购数'},
            {name:'本月/累计设备激活数'},
            {name:'本月/累计门店推广量'},
            {name:'详情'}
        ];
        vm.deviceStockTitles=[
            {name:"订单编号"},
            {name:"创建时间"},
            {name:"采购单价"},
            {name:"设备类型"},
            {name:"设备数量"},
            {name:"收件人信息"},
            {name:"订单状态"},
            {name:"操作"}
        ];
        vm.myreportedMessage=[
            {name:"省"},
            {name:"市"},
            {name:"区/县"},
            {name:"已报人数"},
            {name:"区域状态"},
            {name:"操作"}
        ];
        vm.conversion=function(string){
            return moment(string).format("YYYY-MM-DD");
        }
        vm.deviceActiveDates={};
        return {
            buttons:vm.buttons,
            myreportedMessage:vm.myreportedMessage, //新加  我要报备
            listCouponTitles:vm.listCouponTitles,//卡劵列表
            attractFansRedPacketTitles:vm.attractFansRedPacketTitles,//吸粉红包
            authorizationTitles:vm.authorizationTitles,//授权列表
            manageMerchantTitles:vm.manageMerchantTitles,//管理商户title
            extendedProviderTitles:vm.extendedProviderTitles, //新加  拓展业绩title
            juniorProviderTitles:vm.juniorProviderTitles,//新加 下级服务商title
            accounttitles:vm.accounttitles,
            deviceStockTitles:vm.deviceStockTitles,//订单管理  title
            reportedtitles:vm.reportedtitles, //新加  报备列表reported顶部title
            dealertitles:vm.dealertitles,
            listAdTitles:vm.listAdTitles,
            listRedAdTitles:vm.listRedAdTitles,//惊喜红包列表
            startPageListAdTitles:vm.startPageListAdTitles,     //新加
            devicetitles:vm.devicetitles,
            allDeviceTitles:vm.allDeviceTitles,
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
            incomeStoreSharingTitles:vm.incomeStoreSharingTitles,
            incomeInitialFeeTitles:vm.incomeInitialFeeTitles,
            incomeAdTitles:vm.incomeAdTitles,
            incomeDealerTitles:vm.incomeDealerTitles,
            incomeAdfirstTitles:vm.incomeAdfirstTitles,
            districtBenefitTitles:vm.districtBenefitTitles,//辖区商户次年服务费标题
            juniorPurchaseTitles:vm.juniorPurchaseTitles,
            districtRedTitles:vm.districtRedTitles,
            activerecordTitles:vm.activerecordTitles,
            incomeAdscend:vm.incomeAdscend,
            incomeAdthird:vm.incomeAdthird,
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
    }
})();
