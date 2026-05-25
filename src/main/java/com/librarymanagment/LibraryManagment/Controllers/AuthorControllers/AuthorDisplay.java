package com.librarymanagment.LibraryManagment.Controllers.AuthorControllers;

import com.librarymanagment.LibraryManagment.Repostries.AuthorRepository;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorDisplay {


    private final AuthorService authorService;

    public AuthorDisplay(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/display")
    public String displayPage(Model model){
        model.addAttribute("authorList", authorService.getAllAuthors());
        return "AuthorView/displayAuthor";
    }
}
