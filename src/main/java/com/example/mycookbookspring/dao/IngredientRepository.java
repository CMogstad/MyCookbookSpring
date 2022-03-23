package com.example.mycookbookspring.dao;

import com.example.mycookbookspring.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
