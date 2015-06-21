angular.module('UnikittyApp', [])

.controller('home', function($scope, $http) {
    var self = this;
    $http.get('/resource/').success(function(data) {
        self.greeting = data;
    })
    return self;
});
