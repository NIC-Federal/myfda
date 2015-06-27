import Ember from 'ember';

export default Ember.Route.extend({

  model: function(params) {
    return Ember.RSVP.hash({
      /////////////////////////
      // Effects
      ////////////////////////
      effects: $.getJSON("http://localhost:8080/event?unii=" + params.drug_id),

      /////////////////////////
      // Recalls
      ////////////////////////
      recalls: $.getJSON("http://localhost:8080/drug/enforcements?unii=" + params.drug_id)
    });
  },
  setupController: function(controller, model){
    this._super(controller, model);

    Ember.run.schedule('afterRender', this, function () {

		let duration = 1500;

		$('a[data-toggle="tab"]').on('shown.bs.tab', function () {
			$(".effect", this).velocity("transition.slideRightIn", {duration: duration, stagger: 150});
		});

		$('.collapse').on('show.bs.collapse', function(){
			$(this).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
		}).on('hide.bs.collapse', function(){
			$(this).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
		});

		$('[data-toggle="tooltip"]').tooltip();

    });
  }
});
