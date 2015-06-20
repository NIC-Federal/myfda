angular.module('hello', [])
  .controller('home', function($scope, $http) {
	  var self = this;
  $http.get('/resource/').success(function(data) {
    self.greeting = data;
	  console.log(self);
    
  })
  return self;
});