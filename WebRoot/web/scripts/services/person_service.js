(function () {
    'use strict';

    angular
        .module('center')
        .factory('personService', personService);

    personService.$inject = ['$q','personDao'];

    function personService($q,personDao) {
        return {
            accountInfo: function(){
            	return personDao.accountInfo();
            },
            updateAccountInfo: function(newValue){
            	return personDao.updateAccountInfo(newValue);
            },
            qrcode: function(){
                return personDao.qrcode();
            }
        };
    }
})();
