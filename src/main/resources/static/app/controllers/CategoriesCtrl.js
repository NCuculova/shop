Shop.controller('CategoriesCtrl', ['$scope', 'crudService',
  function($scope, crudService) {
    var category = crudService('category');
    $scope.categories = category.query();
  }
]);