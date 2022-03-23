package com.example.mycookbookspring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    private String title;
    private Integer estimatedTime;
    private String instructions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredientsArray) {
        this.ingredients = ingredientsArray;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

 /*   public ArrayList<Ingredient> getIngredientsArray() {
        return ingredientsArray;
    }

    public void setIngredientsArray(ArrayList<Ingredient> ingredientsArray) {
        this.ingredientsArray = ingredientsArray;
    }*/
}
