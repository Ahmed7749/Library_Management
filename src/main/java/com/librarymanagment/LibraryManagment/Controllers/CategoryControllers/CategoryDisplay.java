package com.librarymanagment.LibraryManagment.Controllers.CategoryControllers;

import com.librarymanagment.LibraryManagment.Repostries.CategoryRepository;
import com.librarymanagment.LibraryManagment.Services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class CategoryDisplay {


    private final CategoryService categoryService;

    public CategoryDisplay(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("display")
    public String display(Model model){
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "CategoryView/categoryDisplay";
    }
}
