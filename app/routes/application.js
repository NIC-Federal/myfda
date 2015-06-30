import Ember from 'ember';

export default Ember.Route.extend({
    model: function() {
        return $.getJSON('/user');
    },
    actions: {
        performSearch: function(keyword) {
            var controller = this.get("controller");
            this.refresh();
            controller.set("searchQuery", keyword.toUpperCase().replace(/\+/g," "));
            this.transitionTo('search.results', keyword);
        },
        setCurrentDrug: function(drugName, drugId) {
            // Set the current drug on the application
            var controller = this.get("controller");
            controller.set("currentDrugName", drugName);

            //Transition to the drug detail page
            this.transitionTo('drug', drugId);
        },
        saveDrugToPortfolio: function(drugId, drugName, portfolioLink) {

            console.log(portfolioLink);
            // Save Drug
            $.ajax({
                 type: "POST",
                 url: "/api/drug",
                 data: JSON.stringify({ unii: drugId, name: drugName }),

                 // Get The Portfolio
                 success: function() {
                    $.ajax({
                        type: "GET",
                        url: portfolioLink,
                        // Update Portfolio
                        success: function(data) {
                            data.drugResources.push({name: drugName, unii: drugId});
                            $.ajax({
                               type: "PUT",
                               url: portfolioLink,
                               data: JSON.stringify(data),
                               contentType: "application/json",
                               success: function() {
                                   var successIcon = "<i class='fa fa-check-circle'></i>";
                                   var notificationBox = $("#notification-box");

                                   // Show Success Box
                                   notificationBox
                                        .addClass("show")
                                        .html(successIcon + " Succesfully Saved " + drugName + " to My Meds");
                                   // Hide Success Box
                                   setTimeout(function(){
                                     notificationBox
                                        .removeClass("show");
                                   }, 3000);
                               }
                            });
                        }
                    });
                 },
                 contentType: "application/json"
            });
        },
        error: function() {
            this.transitionTo('index');
        }
    },

    setupController: function(controller, model){
       this._super(controller, model);
       controller.set('content', model);
       // Set anonymous
       Ember.run.schedule('afterRender', this, function () {
         let duration = 1500;
         // Animations
         $(".recall").velocity("transition.slideRightIn", {duration: duration, stagger: 150});
       });
   }

});
