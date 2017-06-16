phonebookApp.controller('landingController', function ($scope, $http, $q,
        $rootScope, $state, AuthService, CRUDService, $localStorage, $window,
        LoginService) {

    var config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
        }
    };

    var temp = [];
    $scope.added = false;
    $scope.init = function () {

        var defer = $q.defer;
        CRUDService.getContact().then(function (data, headers) {
            $scope.contacts = data;
            $rootScope._.uniq($scope.contacts);
            if ($rootScope._.findWhere($scope.contacts, temp) == null) {
                $scope.contacts = temp;
            }

        }, function () {
            $state.go("home");

        });

    };

    $scope.saveContact = function () {
        var dataObj = {
            name: $scope.newcontact.name,
            phone: $scope.newcontact.phone,
            email: $scope.newcontact.email
        };
        if ($scope.newcontact.id !== null) {
            dataObj.id = $scope.newcontact.id;
        }
        CRUDService.addContact(dataObj).then(function (newContact) {
            $scope.added = true;
            $scope.init();
        }, function () {
            $state.go("home");
        });

        $scope.newcontact = {};
        $('#addNew').modal('hide');

    };
    $scope.deleted = function (contact) {
        $scope.added = false;
        CRUDService.deleteContact(contact).then(function (data) {

        }, function () {
            $scope.error = 'unable to delete contact';
            $state.go("home");
        });
        for (i in $scope.contacts) {
            if ($scope.contacts[i].email == contact.email) {
                $scope.contacts.splice(i, 1);
                $scope.newcontact = {};
            }
        }
    };

    $scope.logout = function () {
        $scope.added = false;
        AuthService.deleteToken();
        $localStorage.authenticated = false;
        $state.go("home");
    };

    $scope.home = function () {
        $scope.contacts = [];
        $state.go("home");
    };

    $scope.edit = function (phone) {
        $scope.added = false;
        for (i in $scope.contacts) {
            if ($scope.contacts[i].phone == phone) {
                $scope.newcontact = angular.copy($scope.contacts[i]);
            }
        }
    };

    $scope.clean = function () {
        $scope.newcontact = {};
    };

});
