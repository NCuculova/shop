Shop.controller('CategoryCtrl', ['$scope', '$modal', 'crudService',
  function($scope, $modal, crudService) {
    var category = crudService('category');
    $scope.save = function() {
      category.save($scope.category, function(data) {
        $scope.category = {};
        $scope.categories = category.query();
        editCategoryDialog.hide();
      });
    };
    $scope.categories = category.query();

    $scope.editCategory = function(id) {
      $scope.category = category.get({
        id: id
      });
      editCategoryDialog.show();
    };

    $scope.addCategory = function() {
      $scope.category = {};
      editCategoryDialog.show();
    };

    $scope.deleteCategory = function(id) {
      category.remove({
        id: id
      }, function() {
        $scope.categories = category.query();
      });
    };

    var editCategoryDialog = $modal({
      scope: $scope,
      template: '/app/templates/modal-form.tpl.html',
      contentTemplate: '/app/forms/editCategory.html',
      show: false
    });
  }
]);