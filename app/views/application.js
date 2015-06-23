import Ember from 'ember';

export default Ember.View.extend({

  didInsertElement: function(){
    Ember.run.schedule('afterRender', this, function(){

      Ember.$('#search').autocomplete({ appendTo: '#autoComplete' });
      Ember.$('#search').autocomplete({
        minLength: 3,
        source: function (request, response) {
          Ember.$.ajax({
            type: 'GET',
            dataType:'json',
            url: '/autocomplete?name=' + Ember.$('#search').val(),
            data: request.value,
            error: function (xhr) {
              alert('Error: ' + xhr.responseText);
            },
            success: function (data) {
              response(Ember.$.map(data, function (item) {
                return item;
              }));
            }
          });
        }
      });

    });
  }
});
