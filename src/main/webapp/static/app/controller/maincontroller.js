phonebookApp.controller('mainController', function($scope, $http, $location,
		$rootScope, $state, $localStorage, _, AuthService, LoginService) {

	$scope.login = function() {
		var data = {
			email : $scope.name,
			password : $scope.password
		};

		LoginService.login(data).then(function(data) {
			$scope.PostDataResponse = data;

			if ($scope.PostDataResponse.valid == 'true') {
				AuthService.storeJWTToken($scope.PostDataResponse.jwt);
				$localStorage.authenticated=true;
				$rootScope.authenticated = $localStorage.authenticated;
				$scope.invalid = false;

			}
			if ($rootScope.authenticated) {
				$state.go("data");
			} else {
				$scope.invalid = true;
				$state.go("home");
			}

		}, function() {
			$scope.error = 'unable to get data';
		});

	},
	$scope.confirmPassword = function(){
		$scope.message="";
		if ($scope.password !==  $scope.cpassword){
			$scope.message="Passwords not same!";
			$scope.register = false;
		}
		else{
			$scope.register = true;
		}
		
	}
	,
	$scope.registeration = function() {
		var data = {
				email : $scope.name,
				password : $scope.password
			};
		
		LoginService.register(data).then(function(data) {//once the work completed the callback is called appropriately
			$scope.registerResponse = data;
			if ($scope.registerResponse.valid == 'true') {
				$state.go("home");
			}
	
		}, function() {
			$scope.error = 'unable to get data';
			$state.go("error");
		});
	
	}

});