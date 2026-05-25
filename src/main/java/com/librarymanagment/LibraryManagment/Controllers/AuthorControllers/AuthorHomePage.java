package com.librarymanagment.LibraryManagment.Controllers.AuthorControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authors")
public class AuthorHomePage {
    @GetMapping("home")
    public String homePage(){
        return "AuthorView/authorHomePage";
    }
}
