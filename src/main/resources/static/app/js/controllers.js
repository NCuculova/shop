Shop.controller('ProductCtrl', ['$scope', '$modal', 'Upload', 'crudService','ProductImage',
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

		$scope.editProduct = function(id){
			$scope.product = product.get({
				id : id
			});
			editProductDialog.show();
		};

		$scope.addProduct = function(){
			$scope.product = {
				quantity : 1
			};
			editProductDialog.show();
		};

		$scope.deleteProduct = function(id){
			product.remove({id: id },function () {
        $scope.products = product.query();
       });
		};

		// creates modal window Upload images
    var modalCreate = $modal({
    	scope : $scope,
      template : '/app/templates/modal-form-notitle.tpl.html',
      contentTemplate : '/app/forms/productImages.html',
    	show : false
    });

     var editProductDialog = $modal({
        	scope : $scope,
          template : '/app/templates/modal-form.tpl.html',
          contentTemplate : '/app/forms/editProduct.html',
        	show : false
     });

    // show modal window
    $scope.addProductImages = function(id) {
    $scope.productId = id;
     modalCreate.show();
    // get images for the flat
     $scope.images = ProductImage.getImagesByProductId({id : id}, function(data) {});

    };

    // upload flatImage
    $scope.onFileSelect = function($files) {
    		function onSuccess(data, status, headers, config) {
    				$scope.productImage = data;
    				$scope.images = ProductImage.getImagesByProductId({id : data.product.id}, function(data) {});
    		}
    		function onError(data, status, headers, config) {
    			console.log("error");
    		}
    		for (var i = 0; i < $files.length; i++) {
    				var file = $files[i];
    				$scope.upload = Upload.upload({
    						url : '/api/product_images/upload/' + $scope.productId,
    						data : {
    								id : $scope.productId
    						},
    						file : file
    				}).success(onSuccess).error(onError);
    		}
    	};

    	$scope.deleteImage = function(id){
    		ProductImage.delete({id : id}, function(){
    			$scope.images = ProductImage.getImagesByProductId({id : $scope.productId}, function(data) {});
    		})
    	};


  }
]);

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

		$scope.editCategory = function(id){
    			$scope.category = category.get({
    				id : id
    			});
    			editCategoryDialog.show();
    		};

    		$scope.addCategory = function(){
    			$scope.category = {};
    			editCategoryDialog.show();
    		};

    		$scope.deleteCategory = function(id){
    			category.remove({id: id },function () {
            $scope.categories = category.query();
           });
    		};

         var editCategoryDialog = $modal({
            	scope : $scope,
              template : '/app/templates/modal-form.tpl.html',
              contentTemplate : '/app/forms/editCategory.html',
            	show : false
         });
  }
]);

Shop.controller('ProductsIndexCtrl', ['$scope', '$routeParams', '$rootScope', '$modal', 'crudService', 'Product', 'ProductImage', 'ShoppingCartItem',
  function($scope, $routeParams, $rootScope, $modal, crudService, Product, ProductImage, ShoppingCartItem) {

		var sum = function(){
      $scope.total=0;
      $scope.items.map(function(item){
      	$scope.total += item.price;
      });
      $rootScope.total = $scope.items.length;
    };

  	var product = crudService('product');
		var categoryId = $routeParams.id;
		if(categoryId != null){
			$scope.products  = Product.getProductsByCategoryId({
				id : categoryId
		});
		}else{
			$scope.products = product.query();
		}

		var shoppingCartDialog = $modal({
            	scope : $scope,
              template : '/app/templates/modal-form.tpl.html',
              contentTemplate : '/app/forms/cart_items.html',
            	show : false
         });

		var loadItems =  function(){
     $scope.items = ShoppingCartItem.getCart(sum);
    };

		$scope.addToCart = function(p){
				$scope.product = p;
				$scope.quantity = 1;
				loadItems();
				shoppingCartDialog.show();
		};

		$scope.addToCartItems = function(){
		 console.log("add to cart...");
			ShoppingCartItem.addToCart({
				id : $scope.product.id,
				quantity: $scope.product.quantity },loadItems);
    };
  }
]);

Shop.controller('CategoriesCtrl', ['$scope', 'crudService',
  function($scope, crudService) {
  	var category = crudService('category');
		$scope.categories = category.query();
  }
]);

Shop.controller('LangCtrl', ['$scope','$translate', 'crudService',
  function($scope, $translate, crudService) {
  	var language = crudService('language');
    $scope.languages = language.query();

    $scope.changeLang = function(lang){
    	$translate.use(lang);
    };
  }
]);

Shop.controller('CartCtrl', ['$scope', '$rootScope', 'crudService', 'ShoppingCartItem',
  function($scope, $rootScope, crudService, ShoppingCartItem) {

		var loadItems =  function(){
         $scope.items = ShoppingCartItem.getCart(sum);
        };

    var sum = function(){
         $scope.total=0;
         $scope.items.map(function(item){
           $scope.total += item.price;
         });
         $rootScope.total = $scope.items.length;
    };

		loadItems();

		var cart = crudService('cart');

		$scope.deleteItem = function(item){
			cart.remove({ id : item }, loadItems);
		};

		 $scope.clearCart = function(){
     ShoppingCartItem.clearCart(loadItems);
    };
  }
]);

Shop.controller('ItemsCtrl', ['$scope', '$rootScope',
  function($scope, $rootScope) {

  }
]);

Shop.controller('ProductDetailsCtrl', ['$scope', '$routeParams', 'crudService', 'ProductImage',
  function($scope, $routeParams, crudService, ProductImage) {
  	var product = crudService('product');
  	$scope.images = ProductImage.getImagesByProductId({
  		id : $routeParams.id
  	});
		$scope.product = product.get({
			id : $routeParams.id
		});
  }
]);

Shop.controller('CheckoutCtrl', ['$scope', '$rootScope', 'ShoppingCartItem', 'TransactionDetails',
  function($scope, $rootScope, ShoppingCartItem, TransactionDetails) {

  var loadItems =  function(){
      $scope.items = ShoppingCartItem.getCart(sum);
  };

  var sum = function(){
      $scope.total=0;
      $scope.items.map(function(item){
        $scope.total += item.quantity * item.price;
      });
      $rootScope.total = $scope.items.length;
   };

  		loadItems();

	$scope.panels = {};
  $scope.panels.activePanel = -1;

	$scope.goNextStep = function(index){
		$scope.panels.activePanel = index;
	};

	$scope.proceedOrder = function(){
		TransactionDetails.payment($scope.transaction);
	};

	$scope.getMonths = function(){
		return new Array(12);
	};

	$scope.getYears = function(){
  		return [2015, 2016, 2017, 2018, 2019, 2020];
  };

  }
]);
