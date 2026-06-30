package com.librarymanagment.LibraryManagment.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flipkart.zjsonpatch.JsonPatch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagment.LibraryManagment.Entities.Author;
import com.librarymanagment.LibraryManagment.Services.AuthorService;
import com.librarymanagment.LibraryManagment.dto.AuthorDTO;
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
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody AuthorDTO dto){
        Author savedAuthor = authorService.saveAuthor(dto);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> putAuthor(@PathVariable long id, @Valid @RequestBody AuthorDTO requestDTO){
        Author existing = authorService.findById(id);
        existing.setAuthorName(requestDTO.authorName());
        existing.setNationality(requestDTO.nationality());
        return new ResponseEntity<>(authorService.saveAuthor(existing), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable long id){
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Author> patchAuthor(@PathVariable long id, @RequestBody String patchBody) {

        Author targetAuthor = authorService.findById(id);


        AuthorDTO patchedAuthor = applyPatchToAuthor(patchBody, authorService.castTOAuthorDTO(targetAuthor));


        return ResponseEntity.ok(authorService.saveAuthor(patchedAuthor));
    }


    private AuthorDTO applyPatchToAuthor(String patchBody, AuthorDTO targetAuthor) {
        try {
            JsonNode patchNode = objectMapper.readTree(patchBody);
            JsonNode targetNode = objectMapper.convertValue(targetAuthor, JsonNode.class);

            JsonNode patchedNode = JsonPatch.apply(patchNode, targetNode);

            return objectMapper.treeToValue(patchedNode, AuthorDTO.class);
        } catch (JsonProcessingException e) {
            throw new JsonPatchProcessingException("Invalid patch format: " + e.getMessage());
        }
    }

}