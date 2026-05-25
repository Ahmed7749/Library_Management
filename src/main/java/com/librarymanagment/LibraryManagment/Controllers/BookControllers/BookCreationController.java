package com.librarymanagment.LibraryManagment.Controllers.BookControllers;

import com.librarymanagment.LibraryManagment.Entities.Book;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import com.librarymanagment.LibraryManagment.Services.BookService;
import com.librarymanagment.LibraryManagment.Services.CategoryService;
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


    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    public BookCreationController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping("create")
    public String bookPage(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "BookView/bookCreation";
    }


    @PostMapping("create")
    public String bookCreation(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("categories", categoryService.getAllCategories());
            return "BookView/bookCreation";
        }
        bookService.saveBook(book);
        return "redirect:/books/create";
    }




}
