import Ember from 'ember';

export default Ember.Route.extend({
    model: function(params) {
        return $.getJSON("event?unii=" + params.drug_id);
    }
});
