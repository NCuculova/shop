var ShopFilters = angular.module('shop.filters', []);

ShopFilters.filter('price', ['$filter',
  function($filter) {
    return function(p) {
      return $filter('number')(p, 2) + " MKD";
    }
  }
]);