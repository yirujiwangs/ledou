/**
 * Created by Administrator on 2017/8/2 0002.
 */
(function(){
    'use strict';

    function getAreaMap(){
        //整合所有地区码以键值对的形式存入一个对象中
        var arr = [];
        var provinces = [];
        var citys = [];
        var districts = [];
        var pLen = allCity.province.length;
        for (var i = 0; i < pLen; ++i) {
            provinces.push(allCity.province[i])
            if (allCity.city[allCity.province[i].id] == undefined)continue
            var cLen = allCity.city[allCity.province[i].id].length;
            for (var j = 0; j < cLen; ++j) {
                citys.push(allCity.city[allCity.province[i].id][j]);
                if (allCity.district[allCity.city[allCity.province[i].id][j].id] == undefined)continue
                var dLen = allCity.district[allCity.city[allCity.province[i].id][j].id].length;
                for (var k = 0; k < dLen; ++k) {
                    districts.push(allCity.district[allCity.city[allCity.province[i].id][j].id][k]);
                }
            }
        }
        var all = arr.concat(provinces, citys, districts);
        var allAreaCode = {};
        var allLen = all.length;
        for (var i = 0; i < allLen; ++i) {
            allAreaCode[all[i].id] = all[i].name
        }
        allAreaCode["99999"] = "全国";
        return allAreaCode;
    }

    angular
        .module('center')
        .filter('CodeToArea',CodeToArea);

    function CodeToArea() {
        return function (msg) {
            return getAreaMap()[msg]
        }
    }
    angular
        .module('center')
        .filter('CodeToAllArea',CodeToAllArea);

    function CodeToAllArea() {
        return function(msg) {
            if(msg.slice(-4)=="0000" || msg=="99999"){
                return getAreaMap()[msg];
            }
            else if(msg.slice(-2)=="00" && msg.slice(-4)!="0000"){
                var province=msg.slice(0,-4)+"0000";
                return getAreaMap()[province]+getAreaMap()[msg];
            }
            else{
                var province=msg.slice(0,-4)+"0000";
                var city=msg.slice(0,-2)+"00";
                var cityName=getAreaMap()[city];
                if(!cityName)cityName=getAreaMap()[msg.slice(0,-3)+"100"];
                return getAreaMap()[province]+cityName+getAreaMap()[msg];
            }
        }
    }

})()