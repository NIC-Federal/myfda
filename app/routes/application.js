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
            // Save Drug
            $.ajax({
                 type: "POST",
                 url: "/api/drug",
                 data: JSON.stringify({ unii: drugId, name: drugName }),
                 contentType: "application/json",
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

                                   notificationBox.removeClass("delete");
                                   notificationBox.addClass("success");

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
                 }
            });
        },
        deleteDrug: function(drugId, drugName) {
            $.ajax({
                 type: "DELETE",
                 url: "/drug/" + drugId,
                 contentType: "application/json",
                 // Get The Portfolio
                 success: function() {
                     var successIcon = "<i class='fa fa-check-circle'></i>";
                     var notificationBox = $("#notification-box");

                     // add delete class for Red color
                     notificationBox.addClass("success");
                     notificationBox.addClass("delete");

                     // Show Successful delete Box
                     notificationBox
                          .addClass("show")
                          .html(successIcon + " Succesfully Deleted " + drugName + " from My Meds");
                     // Hide Success Box
                     setTimeout(function(){
                       notificationBox
                          .removeClass("show");
                     }, 3000);
                 }
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
