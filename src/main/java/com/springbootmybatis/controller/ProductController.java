package com.springbootmybatis.controller;

import com.springbootmybatis.entity.Product;
import com.springbootmybatis.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        log.info("Start to add new product.");
        return new ResponseEntity(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        log.info("Start to get  product by id: " + productId + ".");
        try {
            return ResponseEntity.ok(productService.getProductById(productId));
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("Start to get all products.");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @Valid @RequestBody Product product) {
        log.info("Start to update product by id: " + productId + ".");
        try {
            return ResponseEntity.ok(productService.updateProduct(productId, product));
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer productId) {
        log.info("Start to delete product by id: " + productId + ".");
        try {
            productService.deleteProductById(productId);
            log.info("Product with id: " + productId + " deleted successfully.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/producer/name")
    public ResponseEntity<List<Product>> getProductsListByKeywordInProducerName(@RequestParam String producerName) {
        log.info("Start to get product by keyword in producer name: " + producerName + ".");
        return ResponseEntity.ok(productService.getProductsListByKeywordInProducerName(producerName));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsListByPriceBetween(@RequestParam(required = false) Double startPrice, @RequestParam(required = false) Double endPrice) {
        log.info("Start to get products list by price between '" + startPrice + "' and '" + endPrice + "'.");
        Double optionalStartPrice = startPrice != null ? startPrice : 0.0;
        Double optionalEndPrice = endPrice != null ? endPrice : 1000000.0;
        return ResponseEntity.ok(productService.getProductsListByPriceBetween(optionalStartPrice, optionalEndPrice));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Product>> getProductByKeywordInProductName(@RequestParam String keywordInName) {
        log.info("Start to get products list by keyword '" + keywordInName + "' in product name.");
        return ResponseEntity.ok(productService.getProductsListByKeywordInProductName(keywordInName));
    }

}
