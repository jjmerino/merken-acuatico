<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en" ng-app="merkenApp">
<head>
  <meta charset="utf-8">
  <title>Google Phone Gallery</title>
  <script src="resources/lib/angular/angular.js"></script>
  <script src="resources/lib/angular/angular-route.js"></script>
  <script src="resources/js/app.js"></script>
  <script src="resources/js/controllers.js"></script>
  <script src="resources/js/filters.js"></script>
</head>
<body>
  <div ng-view></div>

</body>
</html>