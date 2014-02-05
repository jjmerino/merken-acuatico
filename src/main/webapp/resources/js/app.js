'use strict';

/* App Module */

var merkenApp = angular.module('merkenApp', [
  'ngRoute',
  'merkenControllers',
  'merkenFilters'
]);

merkenApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'resources/partials/home.html',
            controller: 'MerkenHomeCtrl'
        }).
      otherwise({
        redirectTo: '/'
      });
  }]);
