package com.springbootmybatis.service.impl;

import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.entity.Product;
import com.springbootmybatis.mapper.ProductMapper;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductMapper productMapper;

    @Test
    void getProductById() throws NotFoundException {
        //Given
        Product expectedProduct = preparedProduct();
        Integer productId = expectedProduct.getProductId();
        when(productMapper.selectById(anyInt())).thenReturn(expectedProduct);
        //When
        Product actualProduct = productService.getProductById(productId);
        //Then
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getProductByIdReturnNotFoundException() throws NotFoundException {
        //Given
        Integer incorrectProductId = 22222222;
        when(productMapper.selectById(anyInt())).thenReturn(null);
        //When
        //Then
        assertThrows(NotFoundException.class, () -> productService.getProductById(incorrectProductId),
                "Product with id '" + incorrectProductId + "' not found!");
    }

    @Test
    void getAllProducts() {
        //Given
        List<Product> expectedProductsList = preparedProductsList();
        when(productMapper.selectList(any())).thenReturn(expectedProductsList);
        //When
        List<Product> actualProductsList = productService.getAllProducts();
        //Then
        assertEquals(expectedProductsList, actualProductsList);
    }

    @Test
    void addProduct() {
        //Given
        Product expectedProduct = preparedProduct();
        Integer productId = expectedProduct.getProductId();
        when(productMapper.insert(expectedProduct)).thenReturn(productId);
        //When
        Product actualProduct = productService.addProduct(expectedProduct);
        //Then
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getProductsListByKeywordInProducerName() {
        //Given
        List<Product> expectedProductsList = preparedProductsList();
        String producerName = preparedProducer().getName().substring(5, 8);
        when(productMapper.getProductsListByKeywordInProducerName(anyString())).thenReturn(expectedProductsList);
        //When
        List<Product> actualProductsList = productService.getProductsListByKeywordInProducerName(producerName);
        //Then
        assertEquals(expectedProductsList, actualProductsList);
    }

    @Test
    void deleteProductById() throws NotFoundException {
        //Given
        Product expectedProduct = preparedProduct();
        Integer productId = expectedProduct.getProductId();
        when(productMapper.selectById(anyInt())).thenReturn(expectedProduct);
        when(productMapper.deleteById(productId)).thenReturn(productId);
        //When
        productService.deleteProductById(productId);
        //Then
        verify(productMapper, times(1)).deleteById(productId);
    }

    @Test
    void deleteProductByIdReturnNotFoundException() throws NotFoundException {
        //Given
        Integer incorrectProductId = 99999999;
        when(productMapper.selectById(anyInt())).thenReturn(null);
        //When
        //Then
        assertThrows(NotFoundException.class, () -> productService.deleteProductById(incorrectProductId),
                "Product with id '" + incorrectProductId + "' not found!");
    }

    @Test
    void updateProduct() throws NotFoundException {
        //Given
        Product expectedUpdatedProduct = preparedProduct();
        expectedUpdatedProduct.setName("updated product name");
        Integer productId = expectedUpdatedProduct.getProductId();
        when(productMapper.selectById(anyInt())).thenReturn(expectedUpdatedProduct);
        when(productMapper.update(any(Product.class), any())).thenReturn(productId);
        //When
        Product actualUpdatedProduct = productService.updateProduct(productId, expectedUpdatedProduct);
        //Then
        assertEquals(expectedUpdatedProduct, actualUpdatedProduct);
    }

    @Test
    void updateProductReturnNotFoundException() throws NotFoundException {
        //Given
        Product expectedUpdatedProduct = preparedProduct();
        expectedUpdatedProduct.setName("updated product name");
        Integer productId = expectedUpdatedProduct.getProductId();
        when(productMapper.selectById(anyInt())).thenReturn(null);
        //When
        //Then
        assertThrows(NotFoundException.class, () -> productService.updateProduct(productId, expectedUpdatedProduct),
                "Product with id '" + productId + "' not found!");
    }

    @Test
    void getProductsListByPriceBetween() {
        //Given
        List<Product> expectedProductsList = preparedProductsList();
        Double startPrice = 9.12;
        Double endPrice = 15.33;
        when(productMapper.selectList(any())).thenReturn(expectedProductsList);
        //When
        List<Product> actualProductsList = productService.getProductsListByPriceBetween(startPrice, endPrice);
        //Then
        assertEquals(expectedProductsList, actualProductsList);
    }

    @Test
    void getProductsListByKeywordInProductName() {
        //Given
        List<Product> expectedProductsList = preparedProductsList();
        String keywordInName = preparedProduct().getName().substring(5, 8);
        when(productMapper.selectList(any())).thenReturn(expectedProductsList);
        //When
        List<Product> actualProductsList = productService.getProductsListByKeywordInProductName(keywordInName);
        //Then
        assertEquals(expectedProductsList, actualProductsList);
    }

    private Producer preparedProducer() {
        Producer producer = new Producer();
        producer.setProducerId(1);
        producer.setName("test producer name");
        producer.setCreatedOn(Timestamp.valueOf("2012-10-01 11:40:47"));
        producer.setCountry("test producer country");
        return producer;
    }

    private Product preparedProduct() {
        Product product = new Product();
        Producer producer = preparedProducer();
        product.setProductId(producer.getProducerId());
        product.setProducerId(1);
        product.setName("test product");
        product.setCategory("test category");
        product.setPrice(12.5);
        product.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        product.setCreatedOn(Timestamp.valueOf(LocalDateTime.of(2022, 12, 8, 12, 30, 00)));
        return product;
    }

    private List<Product> preparedProductsList() {
        Product product = preparedProduct();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

    }