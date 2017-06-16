phonebookApp.factory('CRUDService', function($http, $q, $state) {
	return {
		deleteContact : function(contact) {
			var deferred = $q.defer();
			$http.post('/mvc/data/deleteContact', contact).success(
					function(data) {
						deferred.resolve({});
					}).error(function(msg, code) {
				deferred.reject(msg);
			});
			return deferred.promise;
		},

		addContact : function(contact) {
			var deferred = $q.defer();
			$http.post('/mvc/data/saveContact', contact).success(
					function(data) {
						deferred.resolve(data);
					}).error(function(msg, code) {
				deferred.reject(msg);
				$('#addNew').modal('toggle');
				$(".modal-backdrop").remove();
			});
			return deferred.promise;
		},

		getContact : function() {
			var deferred = $q.defer();
			$http.get('/mvc/data/getContact').success(function(data) {
				deferred.resolve(data);
			}).error(function(data, headers) {
				deferred.reject();
			});
			return deferred.promise;

		}
	}
});