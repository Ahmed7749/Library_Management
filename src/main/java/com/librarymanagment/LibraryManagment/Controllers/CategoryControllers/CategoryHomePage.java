package com.librarymanagment.LibraryManagment.Controllers.CategoryControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class CategoryHomePage {
    @GetMapping("home")
    public String categoryHome(){
        return "CategoryView/categoryHomePage";
    }
}
