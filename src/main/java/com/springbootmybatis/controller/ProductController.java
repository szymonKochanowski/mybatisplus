package com.springbootmybatis.controller;

import com.springbootmybatis.entity.Product;
import com.springbootmybatis.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;


    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/producer/name")
    public ResponseEntity<List<Product>> getProductsListByKeywordInProducerName(@RequestParam String producerName) {
        return ResponseEntity.ok(productService.getProductsListByKeywordInProducerName(producerName));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsListByPriceBetween(@RequestParam(required = false) Integer startPrice, @RequestParam(required = false) Integer endPrice) {
        Integer optionalStartPrice = startPrice != null ? startPrice : 0;
        Integer optionalEndPrice = endPrice != null ? endPrice : 1000000;
        return ResponseEntity.ok(productService.getProductsListByPriceBetween(optionalStartPrice, optionalEndPrice));
    }

    @GetMapping("/model")
    public ResponseEntity<List<Product>> getProductByKeywordInProductModel(@RequestParam String keywordInModel) {
        return ResponseEntity.ok(productService.getProductsListByKeywordInProductModel(keywordInModel));
    }

}
