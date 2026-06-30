package com.librarymanagment.LibraryManagment.Controllers;

import com.librarymanagment.LibraryManagment.Entities.Book;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import com.librarymanagment.LibraryManagment.Services.BookService;
import com.librarymanagment.LibraryManagment.Services.CategoryService;
import com.librarymanagment.LibraryManagment.dto.BookAuthorDTO;
import com.librarymanagment.LibraryManagment.dto.BookRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    public BookController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }


    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }


    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }

    @GetMapping("/authors/{authorId}")
    public List<BookAuthorDTO> getBooksForAuthor(@PathVariable long authorId){
        return bookService.getBooksForAuthor(authorId);
    }


    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequestDTO book){
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(bookService.saveBook(createdBook), HttpStatus.CREATED);
    }
}
