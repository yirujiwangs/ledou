(function () {
    'use strict';

    angular
        .module('center')
        .factory('personDao', personDao);
    
    personDao.$inject = ['baseHttp'];

    function personDao(baseHttp) {
        return{
        	accountInfo: function(){
        		return baseHttp({
        			method: 'POST',
        			url: '/admin/accountInfo.do'
        		});
        	},
        	updateAccountInfo: function(newValue){
        		return baseHttp({
        			method: 'POST',
        			url: '/admin/updateProxyAccountInfo.do',
                    data: newValue
        		});
        	},
			qrcode: function(){
				return baseHttp({
					method: 'POST',
					url: '/wx/qrCode.do'
				})
			}
		};
    }
})();
