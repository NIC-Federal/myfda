import Ember from 'ember';

export default Ember.Component.extend({


	_initializeMaps: function() {

		var states = this.get('states');
		var stateObj = {};

		if (states.length > 0) {
			$.each(states, function(index, value){
				stateObj[value] = {fill: '#3366cc'};
			});
		} else {
			// If states is blank this means highlight all states
			stateObj["CO"] = {fill: '#3366cc'};
		}

		console.log(stateObj);

		this.$().usmap({
			showLabels: false,
			stateStyles: {
				fill: '#eee',
				stroke: '#fff'
			},
			stateHoverStyles: {fill: '#cccccc'},
			stateSpecificStyles: stateObj
		});

	}.on('didInsertElement')

});
