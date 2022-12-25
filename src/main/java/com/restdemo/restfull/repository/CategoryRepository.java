package com.restdemo.restfull.repository;

import com.restdemo.restfull.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Product, Long> {
}

