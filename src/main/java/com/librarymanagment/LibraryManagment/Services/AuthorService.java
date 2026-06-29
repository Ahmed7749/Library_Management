package com.librarymanagment.LibraryManagment.Services;

import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Repostries.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {


    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @Transactional
    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }



    public List<Author> findAll(){
        return authorRepository.findAll();
    }


    public Author findById(long id){
        return authorRepository.findAuthorById(id).orElseThrow(() -> new EntityNotFoundException("Author with ID " + id + " has not been found "));
    }


    @Transactional
    public void delete(Long id){
        int rowsAffected = authorRepository.deleteAuthorById(id);
        if(rowsAffected == 0) {
            throw new EntityNotFoundException("Author not found");
        }
    }
}
