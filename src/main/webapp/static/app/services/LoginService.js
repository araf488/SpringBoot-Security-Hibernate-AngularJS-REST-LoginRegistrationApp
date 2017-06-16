phonebookApp.factory('LoginService', function($http, $q) {
	return {
		login : function(data) {
			var deferred = $q.defer();// Creates a deferred object, when
			// service is called
			$http.post('/mvc/login', data).success(function(data) {
				deferred.resolve({
					jwt : data.jwt,
					valid : data.valid
				});
			}).error(function(msg, code) {
				deferred.reject(msg);
			});
			return deferred.promise; // returns a promise back to the client,
			// prevents blocking execution while
			// work is being performed
		},
		register : function(data) {
			var deferred = $q.defer();
			$http.post('/mvc/register', data).success(function(data) {
				deferred.resolve({
					valid : data.valid
				});
			}).error(function(msg, code) {
				deferred.reject(msg);
			});
			return deferred.promise;
		}
	}
});