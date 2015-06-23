import Ember from 'ember';

export default Ember.View.extend({

  didInsertElement: function(){
    Ember.run.schedule('afterRender', this, function(){

      Ember.$('#search').autocomplete({ appendTo: '#autoComplete' });
      Ember.$('#search').autocomplete({

        source: function (request, response) {
          Ember.$.ajax({
            type: 'POST',
            url: '/autocomplete?name=' + Ember.$('#search').val(),
            dataType: 'json',
            data: {
              term: request.termCode
            },
            error: function (xhr) {
              alert('Error: ' + xhr.responseText);
            },
            success: function (data) {
              response(Ember.$.map(data, function (item) {
                return {
                  value: item
                };
              }));
            }
          });
        }
      });

    });
  }
});
