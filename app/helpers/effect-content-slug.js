import Ember from 'ember';

export function effectContentSlug(value) {
	var escaped = Ember.Handlebars.Utils.escapeExpression(value);
	var str = "content-" + escaped.dasherize(); 
	return str;
}

export default Ember.HTMLBars.makeBoundHelper(effectContentSlug);
