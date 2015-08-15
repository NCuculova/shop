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
    var categoryId = $routeParams.id;
    if (categoryId != null) {
      $scope.products = Product.getProductsByCategoryId({
        id: categoryId
      });
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
  }
]);

Shop.controller('CategoriesCtrl', ['$scope', 'crudService',
  function($scope, crudService) {
    var category = crudService('category');
    $scope.categories = category.query();
  }
]);

Shop.controller('LangCtrl', ['$scope', '$translate', 'crudService',
  function($scope, $translate, crudService) {
    var language = crudService('language');
    $scope.languages = language.query();

    $scope.changeLang = function(lang) {
      $translate.use(lang);
    };
  }
]);

Shop.controller('CartCtrl', ['$scope', '$rootScope', 'crudService', 'ShoppingCartItem',
  function($scope, $rootScope, crudService, ShoppingCartItem) {

    var loadItems = function() {
      $scope.items = ShoppingCartItem.getCart(sum);
    };

    var sum = function() {
      $scope.total = 0;
      $scope.items.map(function(item) {
        $scope.total += item.price;
      });
      $rootScope.total = $scope.items.length;
    };

    loadItems();

    var cart = crudService('cart');

    $scope.deleteItem = function(item) {
      cart.remove({
        id: item
      }, loadItems);
    };

    $scope.clearCart = function() {
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
      id: $routeParams.id
    });
    $scope.product = product.get({
      id: $routeParams.id
    });
  }
]);

Shop.controller('CheckoutCtrl', ['$scope', '$rootScope', '$window', '$location', 'ShoppingCartItem', 'Payment',
  function($scope, $rootScope, $window, $location, ShoppingCartItem, Payment) {

    var loadItems = function() {
      $scope.items = ShoppingCartItem.getCart(sum);
    };

    var sum = function() {
      $scope.total = 0;
      $scope.items.map(function(item) {
        $scope.total += item.quantity * item.price;
      });
      $rootScope.total = $scope.items.length;
    };

    loadItems();
    $scope.paid = false;

    $scope.payCard = function() {
      if ($rootScope.user != null) {
        $location.path('/credit-card');
      } else {
        $location.path('/sign');
      }
    };

    $scope.pay = function() {
      $scope.paid = true;
      Payment.payPal(function(payment) {
        for (var link in payment.links) {
          if (payment.links[link].rel == "approval_url") {
            $window.location.href = payment.links[link].href;
          }
        }
      });

    };
  }
]);

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

    $scope.paid = true;

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
      $scope.paid = false;
      $scope.transaction.email = $rootScope.user.name;
      Payment.creditCard($scope.transaction, function(payment) {
        if (payment.state == "approved") {
          var transactionId = payment.id;
          $location.path("/invoice/" + transactionId);
        } else {
          transactionDialog.show();
        }
      }, function(e) {
        console.log(e);
      });
    };
  }
]);

Shop.controller('CartInvoiceCtrl', ['$scope', '$routeParams', '$rootScope', 'ShoppingCartItem', 'CartInvoice',
  function($scope, $routeParams, $rootScope, ShoppingCartItem, CartInvoice) {
    ShoppingCartItem.getCount(function(data) {
      $rootScope.total = data.total;
    });

    $scope.invoice = CartInvoice.getInvoiceByTransactionId({
      id: $routeParams.transactionID
    });

  }
]);

Shop.controller('PayPalCtrl', ['$http', '$scope', '$routeParams', '$rootScope', '$location', 'Payment', 'Auth',
  function($http, $scope, $routeParams, $rootScope, $location, Payment, Auth) {
    $scope.paid = false;
    $scope.error = false;
    $scope.success = false;

    $scope.signIn = function() {
      console.log("sign in");
      $http.post('login', $.param($scope.credentials), {
        headers: {
          "content-type": "application/x-www-form-urlencoded"
        }
      }).success(function(data) {
        Auth.authenticate(function() {
          $scope.signInForm.$setPristine();
          $scope.success = true;
        });
      }).error(function(data) {
        $location.path("/login");
        $scope.error = true;
        $rootScope.authenticated = false;
      })
    };

    $scope.executePayPal = function() {
      $scope.paid = true;
      Payment.payPalExecute({
        paymentId: $routeParams.paymentId,
        payerId: $routeParams.PayerID,
        email: $rootScope.user.name
      }, function(payment) {
        var transactionId = payment.id;
        $location.path("/invoice/" + transactionId);
      });
    }
  }
]);

Shop.controller('SearchCtrl', ['$scope', '$location', '$routeParams', 'Product',
  function($scope, $location, $routeParams, Product) {
    $scope.search = function() {
      $location.path("/search").search({
        text: $scope.searchField
      });
      $scope.searchField = "";
    };
    if ($routeParams.text) {
      $scope.products = Product.search({
        text: $routeParams.text
      });
    }
  }
]);

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