package com.nicusa.domain;

import javax.persistence.*;

@Entity
public class UserProfile {

    public static final String SEQUENCE_NAME = "USER_PROFILE_SEQUENCE";

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
