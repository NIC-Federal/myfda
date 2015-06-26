import Ember from 'ember';

export function effectSlug(value) {
	var escaped = Ember.Handlebars.Utils.escapeExpression(value);
	return "heading-" + escaped.dasherize(); 
}

export default Ember.HTMLBars.makeBoundHelper(effectSlug);
