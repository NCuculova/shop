Shop.controller('SearchCtrl', ['$scope', '$location', '$modal', '$rootScope', 'ShoppingCartItem',
  '$routeParams', 'Product',
  function($scope, $location, $modal, $rootScope, ShoppingCartItem, $routeParams, Product) {
    $scope.search = function() {
      $location.path("/search").search({
        text: $scope.searchField
      });
      $scope.searchField = "";
    };
    if ($routeParams.text) {
      $scope.products = Product.search({
        text: $routeParams.text
      });
    }

    var sum = function() {
      $scope.total = 0;
      $scope.items.map(function(item) {
        $scope.total += item.price;
      });
      $rootScope.total = $scope.items.length;
    };

    var shoppingCartDialog = $modal({
      scope: $scope,
      template: '/app/templates/modal-form.tpl.html',
      contentTemplate: '/app/forms/cart_items.html',
      show: false
    });

    var loadItems = function() {
      $scope.items = ShoppingCartItem.getCart(sum);
    };

    $scope.addToCart = function(p) {
      $scope.product = p;
      $scope.quantity = 1;
      loadItems();
      shoppingCartDialog.show();
    };

    $scope.addToCartItems = function() {
      console.log("add to cart...");
      ShoppingCartItem.addToCart({
        id: $scope.product.id,
        quantity: $scope.product.quantity
      }, loadItems);
    };

    $scope.save = function(){
      shoppingCartDialog.hide();
    };
  }
]);