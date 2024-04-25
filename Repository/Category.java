package Repository;

public interface Repository

package com.example.springbootbackend.repository;

import com.example.springbootbackend.entity.Categoty;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
