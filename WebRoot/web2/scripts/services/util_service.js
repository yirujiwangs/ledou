(function () {
    'use strict';

    angular
        .module('center')
        .factory('utilService', utilService);

    utilService.$inject = ['$q'];

    function utilService($q) {
        var vm=this;
        function isObjectValueEqual(a, b) {
            // Of course, we can do it use for in 
            // Create arrays of property names
            var aProps = Object.getOwnPropertyNames(a);
            var bProps = Object.getOwnPropertyNames(b);
         
            // If number of properties is different,
            // objects are not equivalent
            if (aProps.length != bProps.length) {
                return false;
            }
         
            for (var i = 0; i < aProps.length; i++) {
                var propName = aProps[i];
         
                // If values of same property are not equal,
                // objects are not equivalent
                if (a[propName] !== b[propName]) {
                    return false;
                }
            }
         
            // If we made it this far, objects
            // are considered equivalent
            return true;
        }
        return {
            equal: function (obj1,obj2) {
                var res=true;
                for(var i in obj1){
                    if(obj1[i]!=obj2[i]){
                        res=false;
                    }
                }
                return res;
            },
            copy: function (obj1,obj2) {
                for(var i in obj1){
                    obj2[i]=obj1[i];

                }
                return obj2;
            },
            arrIndexOf:function(arr,item){
                for(var i=0;i<arr.length;i++){
                    if(isObjectValueEqual(arr[i],item)){

                        return i;
                    }
                }
                return -1;
            },
            //数组转化成对象数组
            copyArr:function(arr1,arr2,name){
                for(var i=0;i<arr1.length;i++){
                    arr2[i]={};

                    arr2[i][name]=arr1[i][name];
                }
                return arr2;
            },
            isObjectValueEqual:isObjectValueEqual
        };
    }
})();
