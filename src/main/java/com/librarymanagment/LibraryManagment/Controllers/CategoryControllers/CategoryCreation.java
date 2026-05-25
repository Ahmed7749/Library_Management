package com.librarymanagment.LibraryManagment.Controllers.CategoryControllers;


import com.librarymanagment.LibraryManagment.Entities.Category;
import com.librarymanagment.LibraryManagment.Repostries.CategoryRepository;
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
public class CategoryCreation {


    private final CategoryRepository categoryRepository;

    public CategoryCreation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("create")
    public String categoryGET(Model model){
        model.addAttribute("category", new Category());
        return "CategoryView/categoryCreation";
    }

    @PostMapping("create")
    public String categoryPOST(@ModelAttribute("category") @Valid Category category, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/categories/create";
        }
        categoryRepository.save(category);
        return "redirect:/categories/create";
    }
}
