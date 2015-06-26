import Ember from 'ember';

export default Ember.Route.extend({
    model: function(params) {
        return Ember.RSVP.hash({
            effects: $.getJSON("event?unii=" + params.drug_id),
            recalls: $.getJSON("drug/enforcements?unii=" + params.drug_id)
        });
   },
    setupController: function(controller, model){
        this._super(controller, model);

       Ember.run.schedule('afterRender', this, function () {

		let duration = 1500;
		// Animations
		$(".effect").velocity("transition.slideRightIn", {duration: duration, stagger: 150});
         
		$('.panel-title a').on('click', function(){
			$(".toggle-icon").toggleClass('fa-plus fa-times');
		});         
         
       });
    } 
});
