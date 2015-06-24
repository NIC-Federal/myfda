import Ember from 'ember';

export default Ember.Route.extend({
    model: function() {
        //return this.store.find("drug/recalls");
        return $.getJSON("drug/recalls");
    },
    setupController: function(controller, model){
        this._super(controller, model);

       Ember.run.schedule('afterRender', this, function () {

         let duration = 1500;
         // Animations
         //$("#dashboard-search").velocity("transition.slideRightIn", {duration: duration, display: "table"});
         $(".recall").velocity("transition.slideRightIn", {duration: duration, stagger: 150});
       });
   }

});
