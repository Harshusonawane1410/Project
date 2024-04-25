package Repository;

public interface Repository

package com.example.springbootbackend.repository;

import com.example.springbootbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
