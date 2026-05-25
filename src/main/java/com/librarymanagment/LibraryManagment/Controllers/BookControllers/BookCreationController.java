package com.librarymanagment.LibraryManagment.Controllers.BookControllers;

import com.librarymanagment.LibraryManagment.Entities.Book;
import com.librarymanagment.LibraryManagment.Repostries.AuthorRepository;
import com.librarymanagment.LibraryManagment.Repostries.BookRepository;
import com.librarymanagment.LibraryManagment.Repostries.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class BookCreationController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookCreationController(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("create")
    public String bookPage(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "BookView/bookCreation";
    }


    @PostMapping("create")
    public String bookCreation(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("authors", authorRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "BookView/bookCreation";
        }
        bookRepository.save(book);
        return "redirect:/books/create";
    }




}
