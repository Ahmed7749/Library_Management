package com.librarymanagment.LibraryManagment.Controllers.BookControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class bookHomePage {

    @GetMapping("display")
    public String displayHomePage(){
        return "BookView/bookHomePage";
    }

}
