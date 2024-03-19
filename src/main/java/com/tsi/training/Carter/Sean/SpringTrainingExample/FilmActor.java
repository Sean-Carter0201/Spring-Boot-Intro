package com.tsi.training.Carter.Sean.SpringTrainingExample;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "film_actor")
@IdClass(FilmActor.PrimaryKeyTest.class)
public class FilmActor {

    public static class PrimaryKeyTest implements Serializable {
        protected Integer film_ID;
        protected Integer actor_ID;

        public void primaryKeyTest(Integer film_ID, Integer actor_ID) {
            this.film_ID = film_ID;
            this.actor_ID = actor_ID;
        }
    }
    @Id
    @Column(name = "film_id")
    private Integer film_ID;

    @Id
    @Column(name = "actor_id")
    private Integer actor_ID;

    public Integer getFilm_ID() {
        return film_ID;
    }

    public Integer getActor_ID() {
        return actor_ID;
    }

    public void setFilm_ID(Integer film_ID) {
        this.film_ID = film_ID;
    }

    public void setActor_ID(Integer actor_ID) {
        this.actor_ID = actor_ID;
    }
}
