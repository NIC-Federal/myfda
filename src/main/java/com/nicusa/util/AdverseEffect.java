package com.nicusa.util;

public class AdverseEffect implements Comparable<AdverseEffect> {
  private String effect;
  private Long count;
  private Long total;
  private String description;

  public String getEffect() {
    return this.effect == null ? "" : this.effect;
  }
  public void setEffect( String e ) {
    this.effect = e;
  }

  public Long getCount() {
    return this.count == null ? 0L : this.count;
  }
  public void setCount ( long c ) {
    this.count = c;
  }

  public Long getTotal() {
    return this.total == null ? 0L : this.total;
  }
  public void setTotal ( long t ) {
    this.total = t;
  }

  public double getPercent() {
    if ( this.getTotal() == 0L ) {
      return 0.0;
    } else {
      return this.getCount() / (this.getTotal() / 100.0);
    }
  }

  public String getDescription() {
    return this.description;
  }
  public void setDescription ( String d ) {
    this.description = d;
  }

  public int hashCode ( ) {
    return (this.getEffect() + this.getCount()).hashCode();
  }

  public int compareTo ( AdverseEffect o ) {
    int cmp = o.getCount().compareTo( this.getCount() );
    if ( cmp == 0 ) {
      cmp = this.getEffect().compareTo( o.getEffect() );
    }
    return cmp;
  }

  public boolean equals ( Object o ) {
    if ( o instanceof AdverseEffect ) {
      AdverseEffect ae = (AdverseEffect) o;
      return this.getCount().equals( ae.getCount() ) &&
        this.getEffect().equals( ae.getEffect() );
    } else {
      return false;
    }
  }
}
