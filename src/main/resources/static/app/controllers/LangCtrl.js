Shop.controller('LangCtrl', ['$scope', '$translate', 'crudService',
  function($scope, $translate, crudService) {
    var language = crudService('language');
    $scope.languages = language.query();

    $scope.changeLang = function(lang) {
      $translate.use(lang);
    };
  }
]);