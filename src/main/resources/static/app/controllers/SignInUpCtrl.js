Shop.controller('SignInUpCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$location', 'Auth', 'crudService',
  function($scope, $http, $rootScope, $routeParams, $location, Auth, crudService) {
    $scope.credentials = {};
    $scope.signIn = function() {
      console.log("sign in");
      $http.post('login', $.param($scope.credentials), {
        headers: {
          "content-type": "application/x-www-form-urlencoded"
        }
      }).success(function(data) {
        Auth.authenticate(function() {
          $location.path("/credit-card");
        });
      }).error(function(data) {
        $location.path("/login");
        $scope.error = true;
        $rootScope.authenticated = false;
      })
    };

    var user = crudService('user');
    $scope.signUp = function() {
      console.log("sign up");
      $scope.valid = "";
      $scope.error = false;
      $scope.success = false;
      $scope.taken = false;
      if ($scope.user.password == $scope.user.password2) {
        user.save($scope.user, function(data) {
          console.log(data);
          $scope.credentials.username = data.email;
          $scope.valid = "You are ready to sign in!";
          $scope.success = true;
          $scope.user = {};
          $scope.signUpForm.$setPristine();
        }, function(error) {
          console.log(error);
          $scope.valid = "Email already in use!";
          $scope.taken = true;
        });
      } else {
        $scope.valid = "Passwords do not match!";
        $scope.error = true;
      }
    };
  }
]);