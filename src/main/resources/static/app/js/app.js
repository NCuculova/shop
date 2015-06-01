'use strict';

// Declare app level module which depends on views, and components
var Shop = angular.module('shop', ['ngRoute']);

Shop.config(['$routeProvider',function($routeProvider){
      $routeProvider.when('/', {
          templateUrl: '/app/views/home.html'
        }).when('/test', {
          templateUrl: '/app/views/test.html'
        });
      }
]);