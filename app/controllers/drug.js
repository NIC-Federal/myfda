import Ember from 'ember';

export default Ember.Controller.extend({
    needs: 'application',
    // Current User Data
    UserData: Ember.computed.alias('controllers.application.content'),
    // Current Drug
    drugName: Ember.computed.alias('controllers.application.currentDrugName'),
    // Search Drug Interactions
    interactionSearchQuery: null,
    //interactionSearchQueryRegEx: new RegExp(interactionSearchQuery, 'i'),
    interactionSearchResults: function() {
        // Get the search term
        var searchTerm = this.get('interactionSearchQuery');

        // return out of function if blank
        if (!searchTerm) {return;}

        // Create a RegEx that ignores case
        var regex = new RegExp(searchTerm, 'i');

        // Return the results
        return this.model.interactions.interactions.filter(function(interaction){
            return interaction.toDrug.match(regex);
        });
    }.property('interactionSearchQuery')
});
