package com.librarymanagment.LibraryManagment.Controllers.AuthorControllers;

import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("authors")
public class AuthorController {


    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("create")
    public String showCreationForm(Model model){
        model.addAttribute("author", new Author());

        return "AuthorView/createAuthor";
    }

    @GetMapping("display")
    public String displayPage(Model model){
        model.addAttribute("authorList", authorService.getAllAuthors());
        return "AuthorView/displayAuthor";
    }

    @GetMapping("home")
    public String homePage(){
        return "AuthorView/authorHomePage";
    }


    @PostMapping("create")
    public String processCreationForm(@ModelAttribute("author") @Valid Author createdAuthor){
        authorService.saveAuthor(createdAuthor);

        return "redirect:/authors/create";
    }



}