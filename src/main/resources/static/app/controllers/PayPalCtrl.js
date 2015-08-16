Shop.controller('PayPalCtrl', ['$http', '$scope', '$routeParams', '$rootScope', '$location', 'Payment', 'Auth',
  function($http, $scope, $routeParams, $rootScope, $location, Payment, Auth) {
    $scope.paid = false;
    $scope.error = false;
    $scope.success = false;

    $scope.signIn = function() {
      console.log("sign in");
      $http.post('login', $.param($scope.credentials), {
        headers: {
          "content-type": "application/x-www-form-urlencoded"
        }
      }).success(function(data) {
        Auth.authenticate(function() {
          $scope.signInForm.$setPristine();
          $scope.success = true;
        });
      }).error(function(data) {
        $location.path("/login");
        $scope.error = true;
        $rootScope.authenticated = false;
      })
    };

    $scope.executePayPal = function() {
      $scope.paid = true;
      Payment.payPalExecute({
        paymentId: $routeParams.paymentId,
        payerId: $routeParams.PayerID,
        email: $rootScope.user.name
      }, function(payment) {
        var transactionId = payment.id;
        $location.path("/invoice/" + transactionId);
      });
    }
  }
]);