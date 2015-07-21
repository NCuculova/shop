'use strict';

// Declare app level module which depends on views, and components
var Shop = angular.module('shop', ['shop.dependencies', 'shop.services', 'shop.directives']);

Shop.config([ '$routeProvider', '$httpProvider', '$locationProvider', '$translateProvider',
		function($routeProvider, $httpProvider, $locationProvider, $translateProvider) {

			 // Initialize angular-translate
          $translateProvider.useStaticFilesLoader({
            prefix: 'app/i18n/locale-',
            suffix: '.json'
          });

          $translateProvider.preferredLanguage('en');
          // Enable escaping of HTML
          $translateProvider.useSanitizeValueStrategy('escaped');
		} ]);

