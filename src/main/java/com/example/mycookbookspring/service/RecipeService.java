package com.example.mycookbookspring.service;

import com.example.mycookbookspring.dao.IngredientRepository;
import com.example.mycookbookspring.dao.RecipeRepository;
import com.example.mycookbookspring.model.Ingredient;
import com.example.mycookbookspring.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void deleteIngredientFromRecipe(Integer ingredientId){
        List<Recipe> recipes = getAllRecipes();

        for(Recipe recipe : recipes){
            for(Ingredient ingredient: recipe.getIngredients()){
                if(ingredient.getId() == ingredientId){
                    recipe.getIngredients().remove(ingredient);
                    break;
                }
            }
        }
    }

    public void addIngredientToRecipe(Recipe recipe, Ingredient ingredient){
        recipe.getIngredients().add(ingredient);
        recipeRepository.save(recipe);
    }

}
