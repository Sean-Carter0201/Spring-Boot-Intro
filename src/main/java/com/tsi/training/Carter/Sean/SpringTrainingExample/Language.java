package com.tsi.training.Carter.Sean.SpringTrainingExample;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short language_ID;

    @Column(name = "name")
    private String language_name;

    @OneToMany(mappedBy = "language_ID")
    private List<Film> films;

    public Short getLanguage_ID() {
        return language_ID;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_ID(Short language_ID) {
        this.language_ID = language_ID;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}
