package com.forero.products.rest;
import java.util.List;

import com.forero.products.dao.ProductsDAO;
import com.forero.products.entitys.*;
import java.util.ArrayList;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.forero.products.entitys.Product;

@RestController
@RequestMapping("/products")
public class ProductREST {
	
	@Autowired
	private ProductsDAO productDAO;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct() {
		List<Product> products =productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	//cambio el @RequestMapping(value = "{productoId}") por:
	@GetMapping(value = "/{productoId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productoId")Long productId) {
		Optional<Product> optionalProduct =productDAO.findById(productId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}
	
	@DeleteMapping(value = "{productoId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productoId")Long productId) {
		productDAO.deleteById(productId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Optional<Product> optionalProduct =productDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	//@GetMapping//localhost:8080
	//@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return "hello word";
	}
}
