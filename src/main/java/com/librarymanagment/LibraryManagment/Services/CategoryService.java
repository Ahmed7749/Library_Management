package com.librarymanagment.LibraryManagment.Services;

import com.librarymanagment.LibraryManagment.Entities.Category;
import com.librarymanagment.LibraryManagment.Repostries.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


    @Transactional
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }


    public Category getCategoryById(long id){
        return categoryRepository.getCategoryById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    @Transactional
    public void deleteCategory(long id){
        int rowsAffected = categoryRepository.deleteCategoryById(id);
        if(rowsAffected == 0){
            throw new EntityNotFoundException("Category not found - deletion failed");
        }
    }
}
