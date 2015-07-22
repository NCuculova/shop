var ShopDirectives = angular.module('shop.directives', []);

ShopDirectives.directive('productItem',[
 function(){
   return {
     restrict: 'E',
     scope: {
       product: '=',
       addToCart: '='
     },
     templateUrl: '/app/directives/product.html',
     controller: function($scope, $modal){
        var imageViewDialog = $modal({
                        	scope : $scope,
                          template : '/app/templates/modal-image.tpl.html',
                          contentTemplate :'/app/forms/product_image.html',
                        	show : false
             });

        $scope.showImage = function(productId){
            $scope.productId = productId;
            imageViewDialog.show();
        };

     }
}}]
);