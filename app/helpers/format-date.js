import Ember from 'ember';

export function formatDate(date) {
  return moment(date, "YYYYMMDD").format("DD/MM/YYYY");
}

export default Ember.HTMLBars.makeBoundHelper(formatDate);
