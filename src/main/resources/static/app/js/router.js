'use strict';

/**
 *  Performs the navigation of the application
 */

Shop.config(['$routeProvider',function($routeProvider){
      $routeProvider.when('/', {
          templateUrl: '/app/views/home.html'
        }).when('/products', {
          templateUrl: '/app/views/products.html',
          controller: 'ProductCtrl'
        }).when('/categories', {
          templateUrl: '/app/views/categories.html',
          controller: 'CategoryCtrl'
        });
      }
]);