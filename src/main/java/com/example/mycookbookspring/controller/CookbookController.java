package com.example.mycookbookspring.controller;

import com.example.mycookbookspring.model.Recipe;
import com.example.mycookbookspring.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CookbookController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("")
    public String showHomePageStart(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipeList", recipes);
        return "index";
    }

    @GetMapping("/home")
        public String showHomePage(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipeList", recipes);
        return "index";
    }

    @GetMapping("/add")
    public String showAddRecipePage(Model model){
        model.addAttribute("recipe",new Recipe());
        return "add_recipe";
    }

    @PostMapping("/save")
    public String saveRecipe(Recipe recipe){
        recipeService.saveRecipe(recipe);
        return "redirect:/home";
    }

    @GetMapping("/view")
    public String showViewRecipePage(Model model, Integer id){
        Recipe recipe = recipeService.findRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "view_recipe";
    }

    @GetMapping("/edit")
    public String editRecipe(Model model, Integer id){
        Recipe recipe = recipeService.findRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "edit_recipe";
    }

}
