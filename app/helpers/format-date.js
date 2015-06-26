import Ember from 'ember';

export function formatDate(date) {
  return moment(date, "YYYYMMDD").format("MM/DD/YYYY");
}

export default Ember.HTMLBars.makeBoundHelper(formatDate);
