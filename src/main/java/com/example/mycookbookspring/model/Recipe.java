package com.example.mycookbookspring.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    private String title;
    private Integer estimatedTime;
    private String instructions;
    private String ingredients;

/*    @OneToMany
    private ArrayList<Ingredient> ingredientsArray;*/

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

   public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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
