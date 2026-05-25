package com.librarymanagment.LibraryManagment.Controllers.AuthorControllers;

import com.librarymanagment.LibraryManagment.Repostries.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorDisplay {
    private AuthorRepository authorRepository;
    public AuthorDisplay(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @GetMapping("/display")
    public String displayPage(Model model){
        model.addAttribute("authorList", authorRepository.findAll());
        return "AuthorView/displayAuthor";
    }
}
