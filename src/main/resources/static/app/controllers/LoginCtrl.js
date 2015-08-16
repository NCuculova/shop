Shop.controller('LoginCtrl', ['$scope', '$http', '$rootScope', '$routeParams', '$location', 'Auth',
  function($scope, $http, $rootScope, $routeParams, $location, Auth) {
    $scope.credentials = {};
    $scope.login = function() {
      $http.post('login', $.param($scope.credentials), {
        headers: {
          "content-type": "application/x-www-form-urlencoded"
        }
      }).success(function(data) {
        Auth.authenticate(function() {
          $location.path("/");
        });
      }).error(function(data) {
        $location.path("/login");
        $scope.error = true;
        $rootScope.authenticated = false;
      })
    };

    $scope.logout = Auth.logout;
  }
]);