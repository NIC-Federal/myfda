import Ember from 'ember';

export default Ember.Route.extend({
    actions: {
        performSearch: function(keyword) {
            var controller = this.get("controller");
            this.refresh();
            controller.set("searchQuery", keyword);
            this.transitionTo('search.results', keyword);
        },
        error: function() {
            this.transitionTo('index');
        }
    },

});
