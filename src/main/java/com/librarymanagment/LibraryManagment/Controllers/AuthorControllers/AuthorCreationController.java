package com.librarymanagment.LibraryManagment.Controllers.AuthorControllers;

import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Repostries.AuthorRepository;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorCreationController {


    private final AuthorService authorService;

    public AuthorCreationController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/create")
    public String showCreationForm(Model model){
        model.addAttribute("author", new Author());

        return "AuthorView/createAuthor";
    }

    @PostMapping("/create")
    public String processCreationForm(@ModelAttribute("author") Author createdAuthor){
        authorService.saveAuthor(createdAuthor);

        return "redirect:/authors/create";
    }
}