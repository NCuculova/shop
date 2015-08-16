Shop.controller('CheckoutCtrl', ['$scope', '$rootScope', '$window', '$location', 'ShoppingCartItem', 'Payment',
  function($scope, $rootScope, $window, $location, ShoppingCartItem, Payment) {

    var loadItems = function() {
      $scope.items = ShoppingCartItem.getCart(sum);
    };

    var sum = function() {
      $scope.total = 0;
      $scope.items.map(function(item) {
        $scope.total += item.quantity * item.price;
      });
      $rootScope.total = $scope.items.length;
    };

    loadItems();
    $scope.paid = false;

    $scope.payCard = function() {
      if ($rootScope.user != null) {
        $location.path('/credit-card');
      } else {
        $location.path('/sign');
      }
    };

    $scope.pay = function() {
      $scope.paid = true;
      Payment.payPal(function(payment) {
        for (var link in payment.links) {
          if (payment.links[link].rel == "approval_url") {
            $window.location.href = payment.links[link].href;
          }
        }
      });

    };
  }
]);