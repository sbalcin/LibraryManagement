'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/books', {
        templateUrl: 'books/layout',
        controller: BookController
    });
    
   
    $routeProvider.otherwise({redirectTo: '/books'});
}]);