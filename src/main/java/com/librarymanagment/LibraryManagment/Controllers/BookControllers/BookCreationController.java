package com.librarymanagment.LibraryManagment.Controllers.BookControllers;

import com.librarymanagment.LibraryManagment.Entities.Book;
import com.librarymanagment.LibraryManagment.Repostries.BookRepository;
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

    public BookCreationController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("create")
    public String bookPage(Model model){
        model.addAttribute("book", new Book());
        return "BookView/bookCreation";
    }

    @PostMapping("create")
    public String bookCreation(@ModelAttribute("book") @Valid Book book, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/books/create";
        }
        bookRepository.save(book);
        return "redirect:/books/create";
    }
}
