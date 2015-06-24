import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('dashboard', {path: "/"});

  this.route('drugs', function() {
    this.route('drug');
  });

  this.route('search', function() {
    this.route('results');
  });
  this.route('my-meds', function() {
    this.route('notifications');
  });
});

export default Router;
