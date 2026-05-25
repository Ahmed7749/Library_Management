package com.librarymanagment.LibraryManagment.Repostries;

import com.librarymanagment.LibraryManagment.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {



    Category findCategoryByName(String name);
}
