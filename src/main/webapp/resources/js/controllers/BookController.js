'use strict';

/**
 * BookController
 * @constructor
 */
var BookController = function($scope, $http) {
    $scope.book = {};
    $scope.editMode = false;

    $scope.fetchBooksList = function() {
        $http.get('books/bookslist.json').success(function(bookList){
            $scope.books = bookList;
        });
    };

    $scope.addNewBook = function(book) {
        $scope.resetError();
                
        book.captchaResponse = $('#recaptcha_response_field').val();
        book.captchaChallenge = $('#recaptcha_challenge_field').val();
        
        $http.post('books/addBook', book).success(function() {
            $scope.fetchBooksList();
            $scope.book.name = '';
            $scope.book.author = '';
            $scope.book.quantity = '';
            $scope.book.popular = false;
            
            $('#myModal').modal('hide');
        }).error(function() {
            $scope.setError('Could not add a new book. Invalid security code');
            showCaptcha();
        });
    };

    $scope.updateBook = function(book) {
        $scope.resetError();

        $http.put('books/updateBook', book).success(function() {
            $scope.fetchBooksList();
            $scope.book.name = '';
            $scope.book.author = '';
            $scope.book.quantity = '';
            $scope.book.popular = false;
            $scope.editMode = false;            
            
            $('#myModal').modal('hide');
        }).error(function() {
            $scope.setError('Could not update the book');
        });
        
    };

    $scope.editBook = function(book) {
        $scope.resetError();
        $scope.book = book;
        $scope.editMode = true;
           
        $('#myModal').modal('show');
    };

    $scope.removeBook = function(id) {
        $scope.resetError();

        $http.delete('books/removeBook/' + id).success(function() {
            $scope.fetchBooksList();
        }).error(function() {
            $scope.setError('Could not remove book');
        });
        $scope.book.name = '';
        $scope.book.author = '';
        $scope.book.quantity = '';
        $scope.book.popular = false
    };

    $scope.resetBookForm = function() {
        $scope.resetError();
        $scope.book = {};
        $scope.editMode = false;
    };

    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchBooksList();

    $scope.predicate = 'id';
    
    $scope.openModal = function() {
        $scope.resetError();
        $scope.book.name = '';
        $scope.book.author = '';
        $scope.book.quantity = '';
        $scope.book.popular = false;
        $scope.editMode = false;
    };    
    
};