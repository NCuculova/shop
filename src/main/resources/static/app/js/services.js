'use strict';

/* Services */

var ShopServices = angular.module('shop.services', []);

/*
 * Generic CRUD resource REST service
 */

ShopServices.factory('crudService', ['$resource',
  function($resource) {
    return function(type) {
      return $resource('/api/' + type + '/:id', {}, {
        import: {
          method: 'POST',
          url: "/api/" + type + "/import"
        },
        paged: {
          method: 'GET',
          url: "/api/" + type + "/paged"
        }
      });
    };
  }
]);

ShopServices.factory('ProductImage', ['$resource',
  function($resource) {
    return $resource('/api/product_images/:id', {}, {
      'getImagesByProductId': {
        method: 'GET',
        isArray: true,
        url: '/api/product_images/product/:id'
      }
    });
  }
]);

ShopServices.factory('Product', ['$resource',
  function($resource) {
    return $resource('/api/product/:id', {}, {
      'getProductsByCategoryId': {
        method: 'GET',
        isArray: true,
        url: '/api/product/category/:id'
      },
      'search': {
        method: 'GET',
        isArray: true,
        url: '/api/product/search'
      }
    });
  }
]);

ShopServices.factory('ShoppingCartItem', ['$resource',
  function($resource) {
    return $resource('/api/cart/:id', {}, {
      'addToCart': {
        method: 'POST',
        url: '/api/cart/items',
        params: {
          id: '@id',
          quantity: '@quantity'
        }
      },
      'getCart': {
        method: 'GET',
        url: '/api/cart/get_cart',
        isArray: true
      },
      'clearCart': {
        method: 'POST',
        url: '/api/cart/clear'
      },
      'getCount': {
        method: 'GET',
        url: '/api/cart/count'
      }
    });
  }
]);

ShopServices.factory('CartInvoice', ['$resource',
  function($resource) {
    return $resource('/api/cart_invoice/transaction/:id', {}, {
      'getInvoiceByTransactionId': {
        method: 'GET',
        url: '/api/cart_invoice/transaction/:id'
      }
    });
  }
]);


ShopServices.factory('Payment', ['$resource',
  function($resource) {
    return $resource('/api/payment', {}, {
      'creditCard': {
        method: 'POST',
        url: '/api/payment/credit-card'
      },
      'payPal': {
        method: 'POST',
        url: '/api/payment/paypal'
      },
      'payPalExecute': {
        method: 'POST',
        url: '/api/payment/paypal-execute',
        params: {
          payerId: '@payerId',
          paymentId: '@paymentId',
          email: '@email'
        }
      }
    });
  }
]);


ShopServices.factory('Auth', ['$http', '$rootScope', '$location',
  function($http, $rootScope, $location) {
    return {
      authenticate: function(cb, secure) {
        $http.get('authenticated').success(function(data) {
          if (data.name) {
            $rootScope.authenticated = true;
            $rootScope.user = data;
            if (secure) {
              var pass = false;
              for (var auth in data.principal.authorities) {
                if (data.principal.authorities[auth].authority == 'ADMIN') {
                  pass = true;
                }
              }
              if (!pass) {
                $location.path("/login");
              }
            }
            if (cb && typeof cb === 'function')
              cb();
          } else {
            $rootScope.authenticated = false;
            if (secure) {
              $location.path("/login");
            }
          }
        }).error(function() {
          $location.path("/login");
          $rootScope.authenticated = false;
        });
      },
      logout: function() {
        $http.post('logout', {}).success(function() {
          delete $rootScope.user;
          delete $rootScope.authenticated;
          $location.path("/");
        }).error(function(data) {
          delete $rootScope.user;
          delete $rootScope.authenticated;
        });
      }
    }
  }
]);

ShopServices.factory('User', ['$resource',
  function($resource) {
    return $resource('/api/user/', {}, {
      'getSignedUser': {
        method: 'GET',
        url: '/user'
      }
    });
  }
]);