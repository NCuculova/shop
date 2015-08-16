Shop.controller('ProductCtrl', ['$scope', '$modal', 'Upload', 'crudService', 'ProductImage',
  function($scope, $modal, Upload, crudService, ProductImage) {
    var product = crudService('product');
    var category = crudService('category');

    $scope.categories = category.query();
    $scope.products = product.query();

    $scope.save = function() {
      product.save($scope.product, function(data) {
        $scope.product = {};
        $scope.products = product.query();
        editProductDialog.hide();
      });
    };

    $scope.editProduct = function(id) {
      $scope.product = product.get({
        id: id
      });
      editProductDialog.show();
    };

    $scope.addProduct = function() {
      $scope.product = {
        quantity: 1
      };
      editProductDialog.show();
    };

    $scope.deleteProduct = function(id) {
      product.remove({
        id: id
      }, function() {
        $scope.products = product.query();
      });
    };

    // creates modal window Upload images
    var modalCreate = $modal({
      scope: $scope,
      template: '/app/templates/modal-form-notitle.tpl.html',
      contentTemplate: '/app/forms/productImages.html',
      show: false
    });

    var editProductDialog = $modal({
      scope: $scope,
      template: '/app/templates/modal-form.tpl.html',
      contentTemplate: '/app/forms/editProduct.html',
      show: false
    });

    // show modal window
    $scope.addProductImages = function(id) {
      $scope.productId = id;
      modalCreate.show();
      // get images for the flat
      $scope.images = ProductImage.getImagesByProductId({
        id: id
      }, function(data) {});

    };

    // upload flatImage
    $scope.onFileSelect = function($files) {
      function onSuccess(data, status, headers, config) {
        $scope.productImage = data;
        $scope.images = ProductImage.getImagesByProductId({
          id: data.product.id
        }, function(data) {});
      }

      function onError(data, status, headers, config) {
        console.log("error");
      }
      for (var i = 0; i < $files.length; i++) {
        var file = $files[i];
        $scope.upload = Upload.upload({
          url: '/api/product_images/upload/' + $scope.productId,
          data: {
            id: $scope.productId
          },
          file: file
        }).success(onSuccess).error(onError);
      }
    };

    $scope.deleteImage = function(id) {
      ProductImage.delete({
        id: id
      }, function() {
        $scope.images = ProductImage.getImagesByProductId({
          id: $scope.productId
        }, function(data) {});
      })
    };
  }
]);