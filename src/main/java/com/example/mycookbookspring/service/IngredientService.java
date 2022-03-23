package com.example.mycookbookspring.service;

import com.example.mycookbookspring.dao.IngredientRepository;
import com.example.mycookbookspring.model.Ingredient;
import com.example.mycookbookspring.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public void saveIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public void saveIngredientList(List<Ingredient> ingredients){
        for(Ingredient ingredient : ingredients){
            ingredientRepository.save(ingredient);
        }
    }

    public void deleteIngredient(Integer ingredientId){
        ingredientRepository.deleteById(ingredientId);
    }

    public Ingredient findIngredientById(Integer ingredientId){
        return ingredientRepository.findById(ingredientId).get();
    }

    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return (List<Ingredient>) ingredients;
    }

}
