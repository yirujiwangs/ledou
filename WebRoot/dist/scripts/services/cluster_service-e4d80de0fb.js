(function () {
    'use strict';

    angular
        .module('center')
        .factory('clusterService', clusterService);

    clusterService.$inject = ['$q'];

    function clusterService($q) {
        return {
            /*list: function (list) {
                var deferred = $q.defer();
                clusterDao
                    .then(function (result) {
                        deferred.resolve(result);
                    })
                    .catch(function (e) {
                        deferred.reject(e);
                    });
                return deferred.promise;
            }*/
        };
    }
})();
