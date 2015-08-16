Shop.controller('ProductDetailsCtrl', ['$scope', '$routeParams', 'crudService', 'ProductImage',
  function($scope, $routeParams, crudService, ProductImage) {
    var product = crudService('product');
    $scope.images = ProductImage.getImagesByProductId({
      id: $routeParams.id
    });
    $scope.product = product.get({
      id: $routeParams.id
    });
  }
]);