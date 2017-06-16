phonebookApp.config([
		'$stateProvider',
		'$urlRouterProvider',
		'$locationProvider',
		'$httpProvider',
		function($stateProvider, $urlRouterProvider, $locationProvider,
				$httpProvider) {

			$urlRouterProvider.otherwise('/');
			$stateProvider.

			state('home', {
				url : '/',
				templateUrl : 'static/templates/login.html',
				controller : 'mainController'
			}).

			state('register', {
				url : '/register',
				templateUrl : 'static/templates/register.html',
				controller : 'mainController'
			}).

			state('data', {
				url : '/data',
				templateUrl : 'static/templates/landing.html',
				controller : 'landingController'

			}).state('error', {
				url : '/error',
				templateUrl : 'static/templates/error.html'

			});

			$httpProvider.interceptors.push('AuthInterceptor');

		} ]);

phonebookApp.run(['$rootScope','$localStorage','AuthService',
		function($rootScope, $localStorage, AuthService) {
			$rootScope.$on('$stateChangeStart', function(event, toState,
					toParams, fromState, fromParams) {
				if (toState.name == 'data' && fromState.name == '') {
					$rootScope.authenticated = $localStorage.authenticated;
				}
				if (toState.name == 'home' && fromState.name == 'data') {
					$localStorage.authenticated = false;
					$rootScope.authenticated = $localStorage.authenticated;
					AuthService.deleteToken();
				}
			})
		} ]);