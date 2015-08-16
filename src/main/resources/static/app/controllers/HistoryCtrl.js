Shop.controller('HistoryCtrl', ['$scope', '$routeParams', 'crudService', 'DTOptionsBuilder',
  function($scope, $routeParams, crudService, DTOptionsBuilder) {
    $scope.dtOptions = DTOptionsBuilder.newOptions().withBootstrap();
    var transactions = crudService('cart_invoice');
    $scope.orders = transactions.query();
  }
]);