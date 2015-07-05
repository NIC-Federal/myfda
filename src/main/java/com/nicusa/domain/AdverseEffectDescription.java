package com.nicusa.domain;

import javax.persistence.*;

@Entity
public class AdverseEffectDescription
{
    public static final String SEQUENCE_NAME = "ADVERSE_EVENT_DESC_SEQ";

    private Long id;
    private String fdaName;
    private String description;

    public AdverseEffectDescription()
    {
    }

    @Id
    @GeneratedValue
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    public Long getId ()
    {
        return id;
    }

    public void setId (Long id)
    {
        this.id = id;
    }

    @Column(name="FDA_NAME", length=50)
    public String getFdaName ()
    {
        return fdaName;
    }

    public void setFdaName (String fdaName)
    {
        this.fdaName = fdaName;
    }

    @Column(name="DESCRIPTION", length=512)
    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }


}
