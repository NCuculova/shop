Shop.controller('CartCtrl', ['$scope', '$rootScope', 'crudService', 'ShoppingCartItem',
  function($scope, $rootScope, crudService, ShoppingCartItem) {

    var loadItems = function() {
      $scope.items = ShoppingCartItem.getCart(sum);
    };

    var sum = function() {
      $scope.total = 0;
      $scope.items.map(function(item) {
        $scope.total += item.price;
      });
      $rootScope.total = $scope.items.length;
    };

    loadItems();

    var cart = crudService('cart');

    $scope.deleteItem = function(item) {
      cart.remove({
        id: item
      }, loadItems);
    };

    $scope.clearCart = function() {
      ShoppingCartItem.clearCart(loadItems);
    };
  }
]);