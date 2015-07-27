'use strict';

/* Services */

var ShopServices = angular.module('shop.services', []);

/*
 * Generic CRUD resource REST service
 */

ShopServices.factory('crudService', ['$resource', function($resource) {
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
}]);

ShopServices.factory('ProductImage', [ '$resource', function($resource) {
	return $resource('/api/product_images/:id', {},{
		'getImagesByProductId':{
			method : 'GET',
			isArray: true,
			url : '/api/product_images/product/:id'
		}
	});
}
]);

ShopServices.factory('Product', [ '$resource', function($resource) {
	return $resource('/api/product/:id', {},{
		'getProductsByCategoryId':{
			method : 'GET',
			isArray: true,
			url : '/api/product/category/:id'
		}
	});
}
]);

ShopServices.factory('ShoppingCartItem', [ '$resource', function($resource) {
	return $resource('/api/cart/:id', {},{
		'addToCart':{
			method : 'POST',
			url : '/api/cart/items',
			params: {
                id: '@id',
                quantity: '@quantity'
              }
		},
		'getCart':{
			method: 'GET',
			url: '/api/cart/get_cart',
			isArray: true
		},
		'clearCart':{
		 method: 'POST',
		 url: '/api/cart/clear'
		}
	});
}
]);