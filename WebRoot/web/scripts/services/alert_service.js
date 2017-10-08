(function () {
    'use strict';

    angular
        .module('center')
        .factory('AlertService', AlertService);

    AlertService.$inject = ['$q','$modal'];

    function AlertService($q,$modal) {
        var vm=this;
        return {
            alert:function(obj){
                var alertModalInstance = $modal.open({
                    templateUrl: 'views/modals/alert.html',
                    controller: 'AlertCtrl',
                    controllerAs: 'alertCtrl',
                    backdrop: 'static',
                    windowClass: 'overflow-y-auto chart-modal',
                    resolve: {
                        msgObj:function(){
                            return obj
                        }
                    }
                });
            }
        };
    }
})();
