package com.librarymanagment.LibraryManagment.Repostries;

import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {



    List<Book> findBooksByAuthor_AuthorName(String authorAuthorName);


    Book findBooksByAddedDate(Date addedDate);
}
