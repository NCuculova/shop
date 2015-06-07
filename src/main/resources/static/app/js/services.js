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