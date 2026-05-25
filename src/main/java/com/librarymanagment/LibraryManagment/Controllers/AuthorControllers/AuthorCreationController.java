package com.librarymanagment.LibraryManagment.Controllers.AuthorControllers;

import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Repostries.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorCreationController {

    private final AuthorRepository authorRepository;

    public AuthorCreationController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/create")
    public String showCreationForm(Model model){
        model.addAttribute("author", new Author());

        return "AuthorView/createAuthor";
    }

    @PostMapping("/create")
    public String processCreationForm(@ModelAttribute("author") Author createdAuthor){
        authorRepository.save(createdAuthor);

        return "redirect:/authors/create";
    }
}