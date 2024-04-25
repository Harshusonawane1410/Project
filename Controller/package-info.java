package Controller;

package com.example.SpringbootBackend.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    // Get All the Categories
    @GetMapping("/Categories")
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    // Create a new Category
    @PostMapping("/Categories")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryRepository.save(category);
    }
    

    // Get a Single category by ID
    @GetMapping("/Categories/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
    }

    // Update category by ID (PUT)
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable(value = "id") Long catId,
                                            @Valid @RequestBody Category categoryDetails) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        category.setTitle(categoryDetails.getTitle());
        category.setContent(categoryDetails.getContent());

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    // Delete a Category by ID
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(value = "id") Long catId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);

        return ResponseEntity.ok().build();
    }
}