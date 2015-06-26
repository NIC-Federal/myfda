import Ember from 'ember';

export default Ember.Component.extend({

    classNames: ['autocomplete-search'],
    large: false,
    didInsertElement: function(){
      $('.drug-search').autocomplete({
        minLength: 2,
        source: function (request, response) {
          $.ajax({
            type: 'GET',
            dataType:'json',
            url: '/autocomplete?name=' + $('.drug-search').val(),
            data: request.value,
            error: function (xhr) {
              console.log('Error: ' + xhr.responseText);
            },
            success: function (data) {
              response($.map(data, function (item) {
                return item;
              }));
            }
          });
        }
      });
    },
    actions: {
        sendSearch: function() {
            var keyword = this.get('searchQuery').replace(" ", "+AND+");
            // var keyword = encodeURIComponent(this.get('keyword'));
            console.log("sending search. keyword=" + keyword);
            this.sendAction('action', keyword);
        }
    }
});
