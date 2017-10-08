(function () {
    'use strict';
    angular
        .module('center')
        .directive('showmeta', showmeta);
    showmeta.$inject = ['$rootScope'];
    function showmeta($rootScope) {
        return {
            restrict : 'E',
	        replace : true,
            template: '<div></div>',
		    link: function ($scope, iElm, iAttrs, controller) {
                $rootScope.$on('DataChanged',function(event,result){
                    var html=''
                    for (var i in result) {
                        html+=i+":"+result[i]+"\n";
                    };
                   iElm.html(html)
                })
		    }
        }
    }
})()