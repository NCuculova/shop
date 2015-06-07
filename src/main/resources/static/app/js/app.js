'use strict';

// Declare app level module which depends on views, and components
var Shop = angular.module('shop', ['shop.dependencies', 'shop.services', 'shop.directives']);

Shop.config([ '$routeProvider', '$httpProvider', '$locationProvider',
		function($routeProvider, $httpProvider, $locationProvider) {
		} ]);

