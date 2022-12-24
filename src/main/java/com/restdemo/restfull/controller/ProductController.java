package com.restdemo.restfull.controller;

import com.restdemo.restfull.model.Product;
import com.restdemo.restfull.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts(
                    @RequestParam(name = "name",
                    required = false,
                    defaultValue = ""
            ) String name
    ) {
        try {
            List<Product> products;
            if (StringUtils.hasText(name)) {
                products = productRepository.findByNameContaining(name);
            } else {
                products = productRepository.findAll();
            }

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> findById(
            @PathVariable("id") Long id
    ) {

        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(
            @RequestBody Product customer) {
        try {
            Product newProduct = new Product();
            newProduct.setName(customer.getName());
            newProduct.setDescription(customer.getDescription());
            return new ResponseEntity<>(productRepository.save(newProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/get/{id}")
    public ResponseEntity<Product> update(
            @PathVariable("id") Long id,
            @RequestBody Product product
    ) {

        Optional<Product> productData = productRepository.findById(id);

        if (productData.isPresent()) {
            Product updatedProduct = productData.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            return new ResponseEntity<>(productRepository.save(updatedProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("id") Long id
    ) {
        System.out.println("id console: " + id);
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            productRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
