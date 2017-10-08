(function () {
    'use strict';

    angular
        .module('center')
        .factory('JuniorProviderDao', JuniorProviderDao);

    JuniorProviderDao.$inject = ['baseHttp'];

    function JuniorProviderDao(baseHttp) {
        //工厂模式必须有返回值
        return {
            statistics:function(){   //关键指标里面的数字
                return baseHttp({
                    method: 'POST',
                    url:'/juniorProvider/proxyKPI.do',
                })
            },
            juniorProvidertList:function(page){   //列表里面的数值
                return baseHttp({
                    method: 'POST',
                    url:'/juniorProvider/proxyList.do',
                    data: page
                });
            },
            juniorProvidertDetailList:function(page){
                return baseHttp({
                    method: 'POST',
                    url:'/juniorProvider/proxyDetailList.do',
                    data: page
                });
            }
        }
    }
})();
