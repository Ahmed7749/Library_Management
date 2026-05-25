package com.librarymanagment.LibraryManagment.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class mainHomeDashboard {
    @GetMapping
    public String homePage(){
        return "mainDashboard";
    }
}
