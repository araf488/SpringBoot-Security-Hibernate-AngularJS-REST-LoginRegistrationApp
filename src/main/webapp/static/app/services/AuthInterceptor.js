phonebookApp.factory('AuthInterceptor', function(AuthService) {

	return {

		request : function(config) {
			var jwt = AuthService.getJWTToken();
			if (jwt) {
				config.headers.Authorization = jwt;
			}
			return config;
		},

		response : function(response) {
			return response;
		}

	}

});