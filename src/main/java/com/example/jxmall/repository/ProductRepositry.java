package com.example.jxmall.repository;

import com.example.jxmall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepositry extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

}