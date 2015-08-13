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
        }).when('/admin/products', {
          templateUrl: '/app/views/admin/products.html',
          controller: 'ProductCtrl',
          secure: true
        }).when('/admin/categories', {
          templateUrl: '/app/views/admin/categories.html',
          controller: 'CategoryCtrl',
          secure: true
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
          templateUrl: '/app/views/confirmation.html',
          controller: "CartInvoiceCtrl"
        }).when('/pay_pal', {
          templateUrl: '/app/views/pay_pal.html',
          controller: "PayPalCtrl"
        }).when('/search', {
          templateUrl: '/app/views/search_results.html',
          controller: "SearchCtrl"
        }).when('/login', {
          templateUrl: '/app/views/login.html',
          controller: "LoginCtrl"
        }).when('/sign', {
          templateUrl: '/app/views/signin_up.html',
          controller: "SignInUpCtrl"
        }).when('/admin', {
          templateUrl: '/app/views/admin/admin.html',
          secure: true
        });
}]);