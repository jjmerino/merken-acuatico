'use strict';

/* Controllers */

var merkenControllers = angular.module('merkenControllers', []);

merkenControllers.controller('MerkenHomeCtrl', ['$scope', '$http',
  function($scope, $http) {
      $http.defaults.useXDomain = true;
    $http.get('profile').success(function(data) {
      console.log(data);
    });

    $scope.orderProp = 'age';
  }]);

merkenControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', '$http',
  function($scope, $routeParams, $http) {
    $http.get('phones/' + $routeParams.phoneId + '.json').success(function(data) {
      $scope.phone = data;
    });
  }]);
