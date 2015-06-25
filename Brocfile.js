/* global require, module */

var EmberApp = require('ember-cli/lib/broccoli/ember-app');

var less = require('broccoli-less');

var app = new EmberApp({
	lessOptions: {
		paths: [
			'bower_components/bootstrap/less'
		]
	}
});

app.import('bower_components/bootstrap/less/bootstrap.less');
app.import('bower_components/bootstrap/dist/js/bootstrap.js');
app.import("bower_components/velocity/velocity.js");
app.import("bower_components/velocity/velocity.ui.js");
app.import('bower_components/us-map/lib/raphael.js');
app.import('bower_components/us-map/jquery.usmap.js');
app.import('bower_components/moment/moment.js');

// Use `app.import` to add additional libraries to the generated
// output files.
//
// If you need to use different assets in different
// environments, specify an object as the first parameter. That
// object's keys should be the environment name and the values
// should be the asset to use in that environment.
//
// If the library that you are including contains AMD or ES6
// modules that you would like to import into your application
// please specify an object with the list of modules as keys
// along with the exports of each module as its value.

module.exports = app.toTree();
