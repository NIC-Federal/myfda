import DS from 'ember-data';

export default DS.Model.extend({
    name: DS.attr("string"),
    links: DS.attr("string")
});
