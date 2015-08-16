Shop.controller('BillingCtrl', ['$scope', '$rootScope', '$location', '$modal', 'Payment', 'User',
  function($scope, $rootScope, $location, $modal, Payment, User) {

    var user = User.getSignedUser(function() {
      $scope.transaction = {};
      $scope.transaction.name = user.firstName;
      $scope.transaction.surname = user.lastName;
      $scope.transaction.address = user.address;
      $scope.transaction.city = user.city;
      $scope.transaction.country = user.country;
      $scope.transaction.postalCode = user.postalCode;
    });

    $scope.paid = false;

    $scope.getMonths = function() {
      return new Array(12);
    };

    $scope.getYears = function() {
      return [2015, 2016, 2017, 2018, 2019, 2020];
    };

    var transactionDialog = $modal({
      template: '/app/templates/modal-form.tpl.html',
      contentTemplate: '/app/forms/transaction_info.html',
      show: false
    });

    $scope.payCreditCard = function() {
      $scope.paid = true;
      $scope.transaction.email = $rootScope.user.name;
      Payment.creditCard($scope.transaction, function(payment) {
        if (payment.state == "approved") {
          var transactionId = payment.id;
          $location.path("/invoice/" + transactionId);
        } else {
          transactionDialog.show();
        }
      }, function(e) {
        $scope.paid = false;
        $scope.error = true;
        console.log(e);
      });
    };
  }
]);