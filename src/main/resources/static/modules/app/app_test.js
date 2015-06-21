describe('Application Module', function(){
    it('should exist', function(){
        try{
            angular.module('UnikittyApp');
        } catch(ex){
            fail('Cannot load UnikittyApp  module');
        }
    });
});
