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
        }).when('/cart', {
          templateUrl: '/app/views/cart_details.html',
          controller: 'CartCtrl'
        }).when('/checkout', {
          templateUrl: '/app/views/checkout.html',
          controller: "CheckoutCtrl"
        }).when('/credit-card', {
          templateUrl: '/app/views/bill_card_details.html',
          controller: "BillingCtrl"
        }).when('/invoice/:transactionID', {
          templateUrl: '/app/views/cart_invoice.html',
          controller: "CartInvoiceCtrl"
        }).when('/pay_pal', {
          templateUrl: '/app/views/pay_pal.html',
          controller: "PayPalCtrl"
        });
      }
]);