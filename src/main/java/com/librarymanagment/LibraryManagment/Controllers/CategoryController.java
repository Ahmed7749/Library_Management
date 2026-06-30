package com.librarymanagment.LibraryManagment.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonPatch;
import com.librarymanagment.LibraryManagment.Entities.Category;
import com.librarymanagment.LibraryManagment.Services.CategoryService;
import com.librarymanagment.LibraryManagment.exception.JsonPatchProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {


    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;
    public CategoryController(CategoryService categoryService, ObjectMapper objectMapper) {
        this.categoryService = categoryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Category> getCategoryList(){
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.FOUND);
    }


    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @Valid @RequestBody Category category){
        category.setId(id);
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Category> patchCategory(@PathVariable long id, @RequestBody String patch){
        Category category = categoryService.getCategoryById(id);

        return new ResponseEntity<>(applyPatching(category, patch), HttpStatus.CREATED);
    }

    private Category applyPatching(Category target, String patch){
        try{
            JsonNode patchedNode = objectMapper.readTree(patch);
            JsonNode targetNode = objectMapper.convertValue(target, JsonNode.class);

            JsonNode patchValue = JsonPatch.apply(patchedNode,targetNode);

            return objectMapper.treeToValue(patchValue, Category.class);
        } catch (JsonProcessingException e){
            throw new JsonPatchProcessingException("Failed to do patch method on category");
        }
    }
}
