var phonebookApp = angular.module('phonebookApp', [ 'ui.router', 'ngStorage' ]);

phonebookApp.run([ '$rootScope', '$log', function($rootScope, $log) {
	$rootScope._ = window._;
} ]);
