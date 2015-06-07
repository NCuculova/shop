'use strict';

/**
 *  Performs the navigation of the application
 */

Shop.config(['$routeProvider',function($routeProvider){
      $routeProvider.when('/', {
          templateUrl: '/app/views/category_products.html',
          controller: 'HomeCtrl'
        }).when('/category/:id/product', {
          templateUrl: '/app/views/category_products.html',
          controller: 'HomeCtrl'
        }).when('/products', {
          templateUrl: '/app/views/products.html',
          controller: 'ProductCtrl'
        }).when('/categories', {
          templateUrl: '/app/views/categories.html',
          controller: 'CategoryCtrl'
        });
      }
]);