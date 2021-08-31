package com.lafin.housekeeper.repository;


import com.lafin.housekeeper.entity.Member;
import com.lafin.housekeeper.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
