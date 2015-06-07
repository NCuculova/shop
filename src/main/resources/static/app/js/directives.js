var ShopDirectives = angular.module('shop.directives', []);

ShopDirectives.directive('productItem',[
 function(){
   return {
     restrict: 'E',
     scope: {
       product: '=',
       addToCart: '='
     },
     templateUrl: '/app/directives/product.html'
}}]
);