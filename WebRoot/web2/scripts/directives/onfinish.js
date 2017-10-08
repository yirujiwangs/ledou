(function () {
    'use strict';
    angular
    .module('center')
    .directive('onFinishRenderFilters', function ($timeout) {
            return {
                restrict: 'A',
                link: function(scope, element, attr) {
                    // if (scope.$last === true) {
                        $timeout(function() {
                            scope.$emit('ngRepeatFinished');
                        });
                    // }
                }
            };
     });
    
})()