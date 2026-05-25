package com.librarymanagment.LibraryManagment.Controllers.BookControllers;


import com.librarymanagment.LibraryManagment.Repostries.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("books")
public class bookDisplayController {

    private final BookRepository bookRepository;

    public bookDisplayController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("display")
    public String generateList(Model model){
        model.addAttribute("booksList", bookRepository.findAll());
        return "BookView/displayBook";
    }
}
