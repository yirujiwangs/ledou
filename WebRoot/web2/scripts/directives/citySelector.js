(function () {
    'use strict';
    angular
    .module('center')
    .directive('citySelector', function () {
      return {
        scope: {
          require: '@',
          areaCode: '=',
          proxyType:'=',
          align: '='
        },
        restrict: 'A',
        templateUrl: 'scripts/directives/citySelector.html',
        link: function(scope,elem,attrs){
                // 直辖市
                // var municipalityFlag = scope.province.id === (110000 || 120000 || 500000 || 310000);
                // 城市/地区选项默认隐藏

                scope.isCity = false;
                scope.isDistrict = false;

                // 初始化所有省份
                scope.provinces = allCity.province;

                var saveCode = function(require){   
                  scope.areaCode = undefined;
                  if(scope.district){
                    scope.areaCode = scope.district.id;
                  }
                  else if(scope.city){
                    scope.areaCode = scope.city.id;
                  }
                  else if(scope.province){
                    scope.areaCode = scope.province.id;
                  }
                  if(!require && !scope.areaCode){
                    scope.areaCode = 1;
                  }
                  // console.log(scope.areaCode);
                }
                saveCode(scope.require);

                scope.$watch("proxyType", function(n, o){
                    //级别变动清空选择
                    if(n == o)return;
                    scope.province = undefined;
                    scope.city = undefined;
                    scope.district = undefined;
                    scope.isCity = false;
                    scope.isDistrict = false;
                });

                scope.check = function(type){
                  if(type === 'province'){
                    if(scope.province){
                      scope.city = undefined;
                      scope.district = undefined;
                      // 如果是全国就不选择城市和区域
                      if(scope.province.id !== 1){
                           if(scope.proxyType=='M'||scope.proxyType=='P'||scope.proxyType==undefined) {
                               var city = allCity.city[scope.province.id];
                               scope.cities = city;
                               scope.isCity = true;
                               scope.isDistrict = false;
                             }else{
                               scope.city = undefined;
                               scope.district = undefined;
                               scope.isCity = false;
                               scope.isDistrict = false;
                           }
                      }
                    }
                    else{
                      scope.city = undefined;
                      scope.district = undefined;
                      scope.isCity = false;
                      scope.isDistrict = false;
                    }
                  }
                  else if(type === 'city'){
                    scope.district = undefined;
                    if(scope.city){
                        if(scope.proxyType=='P'||scope.proxyType==undefined){
                            var district = allCity.district[scope.city.id];
                            scope.districts = district;
                            scope.isDistrict = true;
                        }else{
                            scope.district = undefined;
                            scope.isDistrict = false;
                        }
                    }  
                    else{
                      scope.district = undefined;
                      scope.isDistrict = false;
                    }
                  }
                  saveCode(scope.require);
                }//check       
              }
      };
    });
})()