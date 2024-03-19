package com.tsi.training.Carter.Sean.SpringTrainingExample;

import jakarta.persistence.*;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @Column(name = "actor_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actor_ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public Integer getActorID() {
        return actor_ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
