package com.tsi.training.Carter.Sean.SpringTrainingExample;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column(name = "film_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer film_ID;

    @Column(name = "title")
    private String filmTitle;

    @Column(name = "description")
    private String filmDescription;

    @Column(name = "rating")
    private String filmRating;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "language_id", insertable = false, updatable = false)
    private Language language_ID;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Integer getFilm_ID() {
        return film_ID;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public Language getFilmLanguage_ID() {
        return language_ID;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    public void setFilmLanguage_ID(Language language_ID) {
        this.language_ID = language_ID;
    }
}
