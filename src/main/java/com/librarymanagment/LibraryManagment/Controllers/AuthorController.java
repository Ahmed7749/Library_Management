package com.librarymanagment.LibraryManagment.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flipkart.zjsonpatch.JsonPatch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import com.librarymanagment.LibraryManagment.exception.JsonPatchProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {


    private final AuthorService authorService;
    private final ObjectMapper objectMapper;
    public AuthorController(AuthorService authorService, ObjectMapper objectMapper) {
        this.authorService = authorService;
        this.objectMapper = objectMapper;

    }

    @GetMapping
    public List<Author> getAuthors(){
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable long id){
        return authorService.findById(id);
    }


    @PostMapping
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author){
        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> putAuthor(@PathVariable long id, @Valid @RequestBody Author author){
        author.setId(id);
        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id){
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Author> patchAuthor(@PathVariable long id, @RequestBody String patchBody) {

        Author targetAuthor = authorService.findById(id);


        Author patchedAuthor = applyPatchToAuthor(patchBody, targetAuthor);


        return ResponseEntity.ok(authorService.saveAuthor(patchedAuthor));
    }


    private Author applyPatchToAuthor(String patchBody, Author targetAuthor) {
        try {
            JsonNode patchNode = objectMapper.readTree(patchBody);
            JsonNode targetNode = objectMapper.convertValue(targetAuthor, JsonNode.class);

            JsonNode patchedNode = JsonPatch.apply(patchNode, targetNode);

            return objectMapper.treeToValue(patchedNode, Author.class);
        } catch (JsonProcessingException e) {
            throw new JsonPatchProcessingException("Invalid patch format: " + e.getMessage());
        }
    }

}