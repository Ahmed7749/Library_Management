package com.librarymanagment.LibraryManagment.Services;

import com.librarymanagment.LibraryManagment.Entities.Book;
import com.librarymanagment.LibraryManagment.Repostries.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }
}
