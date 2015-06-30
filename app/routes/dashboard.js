import Ember from 'ember';

export default Ember.Route.extend({
    model: function() {
        return $.getJSON('/user').then(function(user) {
            return $.getJSON(user.links.portfolio).then(function(portfolio) {
                return $.each(portfolio.drugResources, function(index, drug) {
                    return drug;
                });
            });
        });
    },
    setupController: function(controller, model){
        this._super(controller, model);

       Ember.run.schedule('afterRender', this, function () {

         let duration = 1500;
         // Animations
         $(".recall").velocity("transition.slideRightIn", {duration: duration, stagger: 150});
       });
   }

});
