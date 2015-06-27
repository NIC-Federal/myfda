import Ember from 'ember';

export default Ember.Route.extend({
    model: function(params, transition) {
        // Get the UNII from the parent route
        var drug_id = transition.params.drug.drug_id;

        // Fetch the effect model data
        return Ember.RSVP.hash({
            // count
            effectCount: this.modelFor('drug'),
            // Gender
            genderCount: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + drug_id + '+AND+patient.reaction.reactionmeddrapt:%22' + params.effect_name + '%22&count=patient.patientsex').then(function (data) {
                var result = {
                    male: {
                        count: 0,
                        pct: 0
                    },
                    female: {
                        count: 0,
                        pct: 0
                    },
                    total: 0
                };

                $.each(data.results, function (key, value) {
                    if (value.term === 1) { // Male
                        result.male.count = value.count;
                    } else if (value.term === 2) { // Female
                        result.female.count = value.count;
                    }
                });

                result.total = result.male.count + result.female.count;
                result.male.pct = Math.round((result.male.count / result.total) * 100);
                result.female.pct = Math.round((result.female.count / result.total) * 100);
                console.log(result);
                return result;
            }),

            //Age
            ageCount: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + drug_id + '+AND+patient.reaction.reactionmeddrapt:%22' + params.effect_name + '%22&count=patient.patientonsetage').then(function (data) {

                var result = [];
                for (var i = 0; i < 111; i++) {
                    result.push({ age: i, count: 0 });
                }

                var tmpAge;
                $.each(data.results, function (key, value) {
                    tmpAge = Math.round(value.term);
                    if(tmpAge > -1 && tmpAge < 111)
                    {
                        result[tmpAge].count += value.count;
                    }
                });

                console.log(result);

                return result;

              }),

            // Severity/Outcome
            outcomeCount: $.getJSON('https://api.fda.gov/drug/event.json?search=patient.drug.openfda.unii:' + drug_id + '+AND+patient.reaction.reactionmeddrapt:%22' + params.effect_name + '%22&count=patient.reaction.reactionoutcome').then(function (data) {

                var result = {
                    Outcome1: { count: 0, pct: 0 },
                    Outcome2: { count: 0, pct: 0 },
                    Outcome3: { count: 0, pct: 0 },
                    Outcome4: { count: 0, pct: 0 },
                    Outcome5: { count: 0, pct: 0 }
                };

                var total = 0;
                $.each(data.results, function (key, value) {
                    switch(value.term) {
                        case 1:
                            result.Outcome1.count = value.count;
                            break;
                        case 2:
                            result.Outcome2.count = value.count;
                            break;
                        case 3:
                            result.Outcome3.count = value.count;
                            break;
                        case 4:
                            result.Outcome4.count = value.count;
                            break;
                        case 5:
                            result.Outcome5.count = value.count;
                            break;
                        default:
                    }
                    if(value.term > 0 && value.term < 5)
                    {
                        total += value.count;
                    }

                });

                result.Outcome1.pct = Math.round((result.Outcome1.count / total)*100);
                result.Outcome2.pct = Math.round((result.Outcome2.count / total)*100);
                result.Outcome3.pct = Math.round((result.Outcome3.count / total)*100);
                result.Outcome4.pct = Math.round((result.Outcome4.count / total)*100);
                result.Outcome5.pct = Math.round((result.Outcome5.count / total)*100);

                return result;
              })
        });
    }
});
