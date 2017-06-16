phonebookApp.factory('AuthService', [ '$window', function($window) {

	var storage = $window.localStorage;

	return {
		storeJWTToken : function(jwtToken) {
			storage.setItem('jwtToken', jwtToken);
		},
		getJWTToken : function() {
			return storage.getItem('jwtToken');
		},
		IsValidUserToken : function() {
			return !!this.getJWTToken();
		},
		deleteToken : function() {
			storage.removeItem('jwtToken');
		}
	}
} ]);