Shop.controller('ProductsIndexCtrl', ['$scope', '$routeParams', '$rootScope', '$modal', 'crudService', 'Product', 'ProductImage', 'ShoppingCartItem',
  function($scope, $routeParams, $rootScope, $modal, crudService, Product, ProductImage, ShoppingCartItem) {

    var sum = function() {
      $scope.total = 0;
      $scope.items.map(function(item) {
        $scope.total += item.price;
      });
      $rootScope.total = $scope.items.length;
    };

    var product = crudService('product');
    var category = crudService('category');
    var categoryId = $routeParams.id;
    if (categoryId != null) {
      $scope.products = Product.getProductsByCategoryId({
        id: categoryId
      });
      $scope.category = category.get({id: categoryId});
    } else {
      $scope.products = product.query();
    }

    var shoppingCartDialog = $modal({
      scope: $scope,
      template: '/app/templates/modal-form.tpl.html',
      contentTemplate: '/app/forms/cart_items.html',
      show: false
    });

    var loadItems = function() {
      $scope.items = ShoppingCartItem.getCart(sum);
    };

    $scope.addToCart = function(p) {
      $scope.product = p;
      $scope.quantity = 1;
      loadItems();
      shoppingCartDialog.show();
    };

    $scope.addToCartItems = function() {
      console.log("add to cart...");
      ShoppingCartItem.addToCart({
        id: $scope.product.id,
        quantity: $scope.product.quantity
      }, loadItems);
    };

    $scope.save = function(){
       shoppingCartDialog.hide();
    };
  }
]);