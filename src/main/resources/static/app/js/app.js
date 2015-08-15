'use strict';

// Declare app level module which depends on views, and components
var Shop = angular.module('shop', ['shop.dependencies', 'shop.services', 'shop.directives', 'shop.filters']);

Shop.config(['$routeProvider', '$httpProvider', '$locationProvider', '$translateProvider',
  function($routeProvider, $httpProvider, $locationProvider, $translateProvider) {

    // Initialize angular-translate
    $translateProvider.useStaticFilesLoader({
      prefix: 'dist/locale-',
      suffix: '.json'
    });

    $translateProvider.preferredLanguage('en');
    // Enable escaping of HTML
    $translateProvider.useSanitizeValueStrategy('escaped');
  }
]).run(['$rootScope', 'ShoppingCartItem', 'Auth',
  function($rootScope, ShoppingCartItem, Auth) {
    var items = ShoppingCartItem.getCart(function() {
      $rootScope.total = items.length;
    });
    $rootScope.$on('$routeChangeStart', function(event, next, current) {
      if (next) {
        var secure = next.secure || false;
        Auth.authenticate(null, secure);
      }
    });
  }
]);