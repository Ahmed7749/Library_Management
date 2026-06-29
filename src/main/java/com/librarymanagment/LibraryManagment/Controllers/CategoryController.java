package com.librarymanagment.LibraryManagment.Controllers;

import com.librarymanagment.LibraryManagment.Entities.Category;
import com.librarymanagment.LibraryManagment.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("create")
    public String categoryGET(Model model){
        model.addAttribute("category", new Category());
        return "CategoryView/categoryCreation";
    }


    @GetMapping("display")
    public String display(Model model){
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "CategoryView/categoryDisplay";
    }


    @GetMapping("home")
    public String categoryHome(){
        return "CategoryView/categoryHomePage";
    }

    @PostMapping("create")
    public String categoryPOST(@ModelAttribute("category") @Valid Category category, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/categories/create";
        }
        categoryService.saveCategory(category);
        return "redirect:/categories/create";
    }
}
