package com.librarymanagment.LibraryManagment.Services;

import com.librarymanagment.LibraryManagment.Entities.Category;
import com.librarymanagment.LibraryManagment.Repostries.CategoryRepository;
import org.springframework.stereotype.Service;

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

    public void saveCategory(Category category){
        categoryRepository.save(category);
    }
}
