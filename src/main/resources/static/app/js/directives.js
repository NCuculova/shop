var ShopDirectives = angular.module('shop.directives', []);

ShopDirectives.directive('productItem',[
 function(){
   return {
     restrict: 'E',
     scope: {
       product: '='
     },
     templateUrl: '/app/directives/product.html'
}}]
);