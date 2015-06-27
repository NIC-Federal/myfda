import Ember from 'ember';

export default Ember.Route.extend({

  model: function(params) {
    return Ember.RSVP.hash({
      // Get Gender Information
      genderCount: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + params.drug_id + '+AND+patient.reaction.reactionmeddrapt:%22' + 'headache' + '%22&count=patient.patientsex').then(function(data) {
        var maleCount = 0;
        var femaleCount = 0;
        var total = 0;

        $.each( data.results, function( key, value ) {
          if(value.term === 1){ // Male
            maleCount = value.count;
          } else if(value.term === 2){ // Female
            femaleCount = value.count;
          }
        });

        total = maleCount + femaleCount;

        var results = {
          male: {
              count: maleCount,
              pct: Math.round((maleCount/total) * 100)
          },
          female: {
              count: femaleCount,
              pct: Math.round((femaleCount/total) * 100)
          },
          total: total
        };
        return results;
      }),
      ages: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + '"8GTS82S83M"' + '+AND+patient.reaction.reactionmeddrapt:%22' + 'headache' + '%22&count=patientonsetage').then(function(data) {
        return data;
      }),
      // Get Effects related to drug
      effects: $.getJSON("event?unii=" + params.drug_id),
      // Get Recalls related to drug
      recalls: $.getJSON("drug/enforcements?unii=" + params.drug_id)
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
  },

  actions: {
    loadEffectData: function(name) {
      console.log("Route recieved: " + name);
      $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:WK2XYI10QM+AND+patient.reaction.reactionmeddrapt:%22' + name + '%22&count=patient.patientsex').then(function(response){
        console.log(response);
        $("#" + name).text(response.results[0].count);
      });
    }
  }
});
