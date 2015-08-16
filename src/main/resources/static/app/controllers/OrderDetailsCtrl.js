Shop.controller('OrderDetailsCtrl', ['$scope', '$routeParams', 'TransactionProduct',
  function($scope, $routeParams, TransactionProduct) {
    $scope.transactions = TransactionProduct.getCartProducts({
      id: $routeParams.id
    });
  }
]);