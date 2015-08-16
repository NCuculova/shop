Shop.controller('CartInvoiceCtrl', ['$scope', '$routeParams', '$rootScope', 'ShoppingCartItem', 'CartInvoice',
  function($scope, $routeParams, $rootScope, ShoppingCartItem, CartInvoice) {
    ShoppingCartItem.getCount(function(data) {
      $rootScope.total = data.total;
    });

    $scope.invoice = CartInvoice.getInvoiceByTransactionId({
      id: $routeParams.transactionID
    });

  }
]);