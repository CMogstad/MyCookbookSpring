package com.example.mycookbookspring.service;

import com.example.mycookbookspring.dao.RecipeRepository;
import com.example.mycookbookspring.model.Ingredient;
import com.example.mycookbookspring.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public void saveRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(Integer recipeId){
        recipeRepository.deleteById(recipeId);
    }

    public Recipe findRecipeById(Integer recipeId){
        return recipeRepository.findById(recipeId).get();
    }
    public List<Recipe> getAllRecipes(){
        Iterable<Recipe> recipes = recipeRepository.findAll();
        return (List<Recipe>) recipes;
    }
}
