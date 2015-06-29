import Ember from 'ember';

export default Ember.Controller.extend({
    searchQuery: null,
    currentDrugName: "",
    // This deals with determining whether or not to display the navigation search bar
    hideNavSearch: false,
    showNavSearch: function() {
        var currentPath = this.get("currentPath");
        var hideSearch = currentPath ? currentPath.indexOf("dashboard") === 0 || currentPath.indexOf("loading") === 0 || currentPath.indexOf("search.loading") === 0: false;
        this.set("hideNavSearch", hideSearch);
    }.observes('currentPath').on("init"),
    setupController: function(controller, model) {
        controller.set('model', model);
    }

});
