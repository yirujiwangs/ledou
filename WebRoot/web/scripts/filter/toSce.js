(function(){
    'use strict';

    angular
        .module('center')
        .filter('ToSce',ToSce);

    ToSce.$inject=["$sce"]

    function ToSce($sce) {
        return function(msg) {
            return $sce.trustAsResourceUrl(msg)
        }
    }
})()