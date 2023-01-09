package com.forero.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forero.products.entitys.Product;

public interface ProductsDAO extends JpaRepository<Product, Long> {

}
