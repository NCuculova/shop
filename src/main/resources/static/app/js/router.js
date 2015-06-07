'use strict';

/**
 *  Performs the navigation of the application
 */

Shop.config(['$routeProvider',function($routeProvider){
      $routeProvider.when('/', {
          templateUrl: '/app/views/category_products.html',
          controller: 'ProductsIndexCtrl'
        }).when('/category/:id/product', {
          templateUrl: '/app/views/category_products.html',
          controller: 'ProductsIndexCtrl'
        }).when('/products/:id', {
          templateUrl: '/app/views/product_details.html',
          controller: 'ProductDetailsCtrl'
        }).when('/products', {
          templateUrl: '/app/views/products.html',
          controller: 'ProductCtrl'
        }).when('/categories', {
          templateUrl: '/app/views/categories.html',
          controller: 'CategoryCtrl'
        });
      }
]);