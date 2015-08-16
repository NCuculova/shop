Shop.controller('OrdersCtrl', ['$scope', '$rootScope', 'CartInvoice', 'User',
  function($scope, $rootScope, CartInvoice, User) {
    User.getSignedUser(function(user) {
      $scope.orders = CartInvoice.getInvoiceByUserId({
        id: user.id
      });
    });


  }
]);