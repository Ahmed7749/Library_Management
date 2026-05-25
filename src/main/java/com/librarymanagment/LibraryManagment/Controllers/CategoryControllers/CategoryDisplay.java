package com.librarymanagment.LibraryManagment.Controllers.CategoryControllers;

import com.librarymanagment.LibraryManagment.Repostries.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class CategoryDisplay {


    private final CategoryRepository categoryRepository;

    public CategoryDisplay(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("display")
    public String display(Model model){
        model.addAttribute("categoryList", categoryRepository.findAll());
        return "CategoryView/categoryDisplay";
    }
}
