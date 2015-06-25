import Ember from 'ember';

export default Ember.Component.extend({
	
	_initializeMaps: function() {
		this.$().usmap({
			showLabels: false,
			stateStyles: {
				fill: '#eee',
				stroke: '#fff'
			},
			stateHoverStyles: {fill: '#cccccc'},
			stateSpecificStyles: {
				'TX': {fill: '#3366cc'}
			}
		});
	}.on('didInsertElement')
	
});
