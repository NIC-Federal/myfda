import Ember from 'ember';

export default Ember.Route.extend({
    actions: {
        performSearch: function(keyword) {
            this.transitionTo('search.results', keyword);
        }
    }
});
