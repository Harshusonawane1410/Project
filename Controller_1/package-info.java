package Controller_1;

package com.example.SpringbootBackend.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.SpringbootBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    // Get All Products
    @GetMapping("/Products")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Create a new Product
    @PostMapping("/Products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }
    

    // Get a Single product by ID
    @GetMapping("/Products/{id}")
    public Product getProdcutById(@PathVariable(value = "id") Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", prodId));
    }

    // Update a product by ID (PUT)
    @PutMapping("/products/{id}")
    public Prodcut updateProduct(@PathVariable(value = "id") Long prodId,
                                            @Valid @RequestBody Note productDetails) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", prodId));

        product.setTitle(productDetails.getTitle());
        product.setContent(productDetails.getContent());

        Product updatedProduct = productRepository.save(note);
        return updatedProduct;
    }

    // Delete a product by ID
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long prodId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", prodId));

        productRepository.delete(product);

        return ResponseEntity.ok().build();
    }
}