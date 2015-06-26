import Ember from 'ember';

export default Ember.Route.extend({

  model: function(params) {
    return Ember.RSVP.hash({
      genderCount: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + '"8GTS82S83M"' + '+AND+patient.reaction.reactionmeddrapt:%22' + 'headache' + '%22&count=patient.patientsex').then(function(data) {
        // var node = Ember.Object.create({
        //   data: {
        //     term: 'test' + data.term,
        //     count: 'count' + data.count
        //   }
        // });
        // return JSON.stringify(node.get('data'));
        return data;
      }),
      ages: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + '"8GTS82S83M"' + '+AND+patient.reaction.reactionmeddrapt:%22' + 'headache' + '%22&count=patientonsetage').then(function(data) {
        return data;
      }),
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
  },

});
