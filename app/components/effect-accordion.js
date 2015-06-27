import Ember from 'ember';

export default Ember.Component.extend({
  drugId: "",
  name: "",
  count: "",
  description: "",
  percent: 0,
  malePercent: 0,
  femalePercent: 0,
  ages: [],
  needs: ['application'],
  _initializeAccordion: function() {
  }.on('didInsertElement'),
  actions: {
    sendEffectData: function() {
      // var drugId = this.get("drugId");
      var name = this.get("name");
      this.sendAction('action', name);

    }
  }

});
