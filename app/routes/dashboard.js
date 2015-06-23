import Ember from 'ember';

export default Ember.Route.extend({
    model: function() {
        return $.getJSON("http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=10&q=http://www.fda.gov/AboutFDA/ContactFDA/StayInformed/RSSFeeds/Recalls/rss.xml");
    },
    setupController: function(controller, model){
        this._super(controller, model);

       Ember.run.schedule('afterRender', this, function () {

         let duration = 1500;

         $(".jumbotron, #dashboard-public").velocity("transition.slideUpIn", {duration: duration});
         $(".recall").velocity("transition.slideRightIn", {duration: duration, stagger: 250});
       });
     }
});
