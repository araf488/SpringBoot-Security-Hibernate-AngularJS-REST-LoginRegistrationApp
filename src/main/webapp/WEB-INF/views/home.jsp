<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>

<!-- <link href="static/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="static/css/main.css" rel="stylesheet">
<link href="static/css/animate.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Rye|Oleo+Script+Swash+Caps' rel='stylesheet' type='text/css'>
<link href="static/css/bootstrap-custom.css" rel="stylesheet">


</head>
<body  ng-app="phonebookApp">
<h1 class="ribbon">
   <strong class="ribbon-content font">PhoneBook</strong>
</h1>

<div ui-view></div>

<script src="static/js/jquery-1.10.2.js" /></script>
<script src="static/js/bootstrap.min.js"/></script>
<script type="text/javascript" src="static/js/angular.js"></script>
<script type="text/javascript" src="static/js/angular-resource.js"></script>
<script type="text/javascript" src="static/js/angular-route.js"></script>
<script type="text/javascript" src="static/js/angular-ui-router.min.js"></script>
<script type="text/javascript" src="static/js/ngStorage.min.js"></script>
<script type="text/javascript" src="static/js/underscore-min.js"></script>
<script src="static/app/app.js"></script>
<script src="static/app/constant/constants.js"></script>
<script src="static/app/module/underscoreModule.js"></script>
<script src="static/app/config/phonebookApp.js"></script>
<script src="static/app/controller/maincontroller.js"></script>
<script src="static/app/controller/landingcontroller.js"></script>
<script type="text/javascript" src="static/app/services/AuthService.js">
</script><script type="text/javascript" src="static/app/services/AuthInterceptor.js"></script>
<script type="text/javascript" src="static/app/services/LoginService.js"></script>
<script type="text/javascript" src="static/app/services/CRUDService.js"></script>
</body>
</html>