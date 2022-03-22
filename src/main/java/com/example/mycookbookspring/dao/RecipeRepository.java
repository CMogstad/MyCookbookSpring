package com.example.mycookbookspring.dao;

import com.example.mycookbookspring.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
