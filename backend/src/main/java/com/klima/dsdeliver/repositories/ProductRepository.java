package com.klima.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klima.dsdeliver.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByOrderByNameAsc();
}
