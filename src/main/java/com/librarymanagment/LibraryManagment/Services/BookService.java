package com.librarymanagment.LibraryManagment.Services;

import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Entities.Book;
import com.librarymanagment.LibraryManagment.Entities.Category;
import com.librarymanagment.LibraryManagment.Repostries.BookRepository;
import com.librarymanagment.LibraryManagment.dto.BookAuthorDTO;
import com.librarymanagment.LibraryManagment.dto.BookRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    public BookService(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }


    @Transactional
    public List<BookAuthorDTO> getBooksForAuthor(long id){
        return bookRepository.getBooksByAuthorId(id);
    }

    @Transactional
    public Book getBookById(long id){
        return bookRepository.getBookById(id).orElseThrow( () -> new EntityNotFoundException("Book has not been found "));
    }


    @Transactional
    public Book createBook(BookRequestDTO dto){
        Author author = authorService.findById(dto.authorId());
        Category category = categoryService.getCategoryById(dto.categoryId());

        Book newBook = new Book();
        newBook.setTitle(dto.title());
        newBook.setIsbn(dto.isbn());
        newBook.setPageCount(dto.pageCount());
        newBook.setStatus(dto.status());
        newBook.setAuthor(author);
        newBook.setCategory(category);

        return bookRepository.save(newBook);
    }
}
