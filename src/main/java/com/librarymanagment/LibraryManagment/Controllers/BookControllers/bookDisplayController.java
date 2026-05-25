package com.librarymanagment.LibraryManagment.Controllers.BookControllers;


import com.librarymanagment.LibraryManagment.Repostries.BookRepository;
import com.librarymanagment.LibraryManagment.Services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class bookDisplayController {


    private final BookService bookService;

    public bookDisplayController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("display")
    public String generateList(Model model){
        model.addAttribute("booksList", bookService.getAllBooks());
        return "BookView/displayBook";
    }
}
