package com.nicusa.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Portfolio {

  public static final String SEQUENCE_NAME = "PROTFOLIO_SEQUENCE";

  private Long id;
  private Collection<Drug> drugs;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Collection<Drug> getDrugs() {
    return drugs;
  }

  public void setDrugs(Collection<Drug> drugs) {
    this.drugs = drugs;
  }
}
