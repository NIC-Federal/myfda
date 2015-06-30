import Ember from 'ember';

export default Ember.Controller.extend({
    needs: 'application',
    UserData: Ember.computed.alias('controllers.application.content')
});
