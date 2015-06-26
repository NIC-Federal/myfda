import Ember from 'ember';

export default Ember.Controller.extend({
    searchQuery: "",
    // This deals with determining whether or not to display the navigation search bar
    isDashboard: false,
    showNavSearch: function() {
        var currentPath = this.get("currentPath");
        var isDashboard = currentPath ? currentPath.indexOf("dashboard") === 0 : false;
        this.set("isDashboard", isDashboard);
    }.observes('currentPath').on("init")

});
