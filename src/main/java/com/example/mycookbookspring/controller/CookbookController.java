package com.example.mycookbookspring.controller;

import com.example.mycookbookspring.model.Ingredient;
import com.example.mycookbookspring.model.Recipe;
import com.example.mycookbookspring.service.IngredientService;
import com.example.mycookbookspring.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class CookbookController {

    static List<Ingredient> tempIngredientsList;
    static Recipe tempRecipe;
    static Ingredient tempIngredient;
    static Integer tempRecipeId;

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientService ingredientService;

    @GetMapping("")
    public String showHomePageStart(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipeList", recipes);
        return "index";
    }

    @GetMapping("/add/recipe")
    public String showAddRecipePage(Model model) {
        Recipe recipe = new Recipe();
        tempRecipe = recipe;
        tempIngredientsList = new ArrayList<>();

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", tempIngredientsList);
        return "add_recipe";
    }

    @PostMapping("/add/recipe/save/newRecipe")
    public String saveNewRecipe(Recipe recipe) {
        System.out.println(tempIngredientsList);
        recipe.setIngredients(tempIngredientsList);

        ingredientService.saveIngredientList(tempIngredientsList);
        recipeService.saveRecipe(recipe);

        tempIngredientsList.clear();
        tempRecipe = null;

        return "redirect:/";
    }

    @GetMapping("/add/recipe/add/ingredient")
    public String showAddIngredientPageInAddRecipe(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "add_recipe_add_ingredient";
    }

    @PostMapping("/add/recipe/add/ingredient/save")
    public String saveAddedIngredientInAddRecipe(Model model, Ingredient ingredient) {
        tempIngredientsList.add(ingredient);

        model.addAttribute("ingredients", tempIngredientsList);
        model.addAttribute("recipe", tempRecipe);
        return "add_recipe";
    }

    @GetMapping("/add/recipe/update/ingredient")
    public String updateIngredientInAddRecipe(Model model, String name) {
        for (Ingredient i : tempIngredientsList) {
            if (name.equals(i.getName())) {
                tempIngredient = i;
                tempIngredientsList.remove(i);
                break;
            }
        }
        model.addAttribute("ingredient", tempIngredient);
        return "add_recipe_update_ingredient";
    }

    @PostMapping("/add/recipe/update/ingredient/save")
    public String saveUpdatedIngredientInAddRecipe(Model model, Ingredient ingredient) {
        tempIngredientsList.remove(tempIngredient);
        tempIngredientsList.add(ingredient);
        model.addAttribute("ingredients", tempIngredientsList);
        model.addAttribute("recipe", tempRecipe);
        return "add_recipe";
    }

    @GetMapping("/add/recipe/delete/ingredient")
    public String deleteIngredientInAddRecipe(Model model, String name) {
        for (Ingredient i : tempIngredientsList) {
            if (i.getName().equals(name)) {
                tempIngredientsList.remove(i);
                break;
            }
        }
        model.addAttribute("recipe", tempRecipe);
        model.addAttribute("ingredients", tempIngredientsList);
        return "add_recipe";
    }

    @GetMapping("/view/recipe")
    public String showViewRecipePage(Model model, Integer recipeId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        tempRecipeId = recipeId;
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredientList", recipe.getIngredients());
        return "view_recipe";
    }

    @GetMapping("/update/recipe")
    public String showUpdateRecipePage(Model model, Integer recipeId) {
        Recipe recipe = recipeService.findRecipeById(recipeId);
        tempRecipeId = recipeId;
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", recipe.getIngredients());
        return "update_recipe";
    }

    @PostMapping("/update/recipe/save")
    public String saveUpdatedRecipe(Model model, Recipe recipe) {
        recipeService.saveRecipe(recipe);
        model.addAttribute("recipeId", recipe.getId());
        return "view_recipe";
    }

    @GetMapping("/update/recipe/add/ingredient")
    public String showAddIngredientPageInUpdateRecipe(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "update_recipe_add_ingredient";
    }

    @PostMapping("/update/recipe/add/ingredient/save")
    public String saveAddedIngredientInUpdateRecipe(Model model, Ingredient ingredient){
        Recipe recipe= recipeService.findRecipeById(tempRecipeId);

        ingredientService.saveIngredient(ingredient);
        recipeService.addIngredientToRecipe(recipe, ingredient);

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredientList", recipe.getIngredients());
        return "update_recipe";
    }

    @GetMapping("/update/recipe/update/ingredient")
    public String showUpdateIngredientPageInUpdateRecipe(Model model, Integer ingredientId){
        Ingredient ingredient = ingredientService.findIngredientById(ingredientId);
        model.addAttribute("ingredient", ingredient);
        return "update_recipe_update_ingredient";
    }

    @PostMapping("/update/recipe/update/ingredient/save")
    public String saveUpdatedIngredientInUpdateRecipe(Model model, Ingredient ingredient) {
        Recipe recipe = recipeService.findRecipeById(tempRecipeId);
        ingredientService.saveIngredient(ingredient);

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredientList", recipe.getIngredients());
        return "update_recipe";
    }

    @GetMapping("/update/recipe/delete/ingredient")
    public String deleteIngredientInUpdateRecipe(Model model, Integer ingredientId) {
        recipeService.deleteIngredientFromRecipe(ingredientId);
        ingredientService.deleteIngredient(ingredientId);

        Recipe recipe= recipeService.findRecipeById(tempRecipeId);
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredientList", recipe.getIngredients());
        return "update_recipe";
    }

    @GetMapping("/delete/recipe")
    public String deleteRecipe(Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/";
    }

}
