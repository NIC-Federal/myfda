import Ember from 'ember';

export default Ember.View.extend({

  didInsertElement: function(){
    Ember.run.schedule('afterRender', this, function(){

      $('#search').autocomplete({ appendTo: '#autoComplete' });
      $('#search').autocomplete({
        minLength: 3,
        source: function (request, response) {
          $.ajax({
            type: 'GET',
            dataType:'json',
            url: '/autocomplete?name=' + $('#search').val(),
            data: request.value,
            error: function (xhr) {
              alert('Error: ' + xhr.responseText);
            },
            success: function (data) {
              response($.map(data, function (item) {
                return item;
              }));
            }
          });
        }
      });

    });
  }
});
