import Ember from 'ember';

export function formatDate(date) {
  return moment(date, "YYYYDDMM").format("DD/MM/YYYY");
}

export default Ember.HTMLBars.makeBoundHelper(formatDate);
