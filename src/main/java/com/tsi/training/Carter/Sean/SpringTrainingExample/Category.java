package com.tsi.training.Carter.Sean.SpringTrainingExample;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_ID;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Film> films;

    public Integer getCategory_ID() {
        return category_ID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
