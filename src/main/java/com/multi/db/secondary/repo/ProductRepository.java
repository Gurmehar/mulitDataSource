package com.multi.db.secondary.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.multi.db.secondary.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
