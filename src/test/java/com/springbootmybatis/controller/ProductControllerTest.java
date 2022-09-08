package com.springbootmybatis.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.entity.Product;
import com.springbootmybatis.service.IProductService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IProductService productService;

    @Test
    void addProduct() throws Exception {
        //Given
        Product expectedNewProduct = preperedProduct();
        expectedNewProduct.setName("new product test");
        expectedNewProduct.setCategory("new product category");
        when(productService.addProduct(expectedNewProduct)).thenReturn(expectedNewProduct);
        //When
        MvcResult mvcResult = mockMvc.perform(post("/product/add")
                        .content(objectMapper.writeValueAsString(expectedNewProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        //Then
        Product actualProduct = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);
        assertEquals(expectedNewProduct, actualProduct);
    }

    @Test
    void getProductById() throws Exception {
        //Given
        Product expectedProduct = preperedProduct();
        Integer productId = expectedProduct.getProductId();
        when(productService.getProductById(productId)).thenReturn(expectedProduct);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/product/{productId}", productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        Product actualProduct = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getProductByIdWithNotFoundException() throws Exception {
        //Given
        Integer incorrectProductId = 99;
        when(productService.getProductById(incorrectProductId)).thenThrow(NotFoundException.class);
        //When
        mockMvc.perform(get("/product/{productId}", incorrectProductId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getAllProducts() throws Exception {
        //Given
        List<Product> expectedProductList = preparedProductsList();
        when(productService.getAllProducts()).thenReturn(expectedProductList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/product/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Product> actualProductsList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>() {
        });
        assertEquals(expectedProductList, actualProductsList);
    }

    @Test
    void updateProduct() throws Exception {
        //Given
        Product expectedProduct = preperedProduct();
        expectedProduct.setName("new test product name");
        expectedProduct.setCategory("new test product category");
        expectedProduct.setPrice(222.13);
        Integer productId = expectedProduct.getProductId();
        when(productService.updateProduct(productId, expectedProduct)).thenReturn(expectedProduct);
        //When
        MvcResult mvcResult = mockMvc.perform(put("/product/update/{productId}", productId)
                        .content(objectMapper.writeValueAsString(expectedProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        Product actualProduct = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void updateProductWithNotFoundException() throws Exception {
        //Given
        Product expectedProduct = preperedProduct();
        expectedProduct.setName("new test product name");
        expectedProduct.setCategory("new test product category");
        expectedProduct.setPrice(222.13);
        Integer incorrectProductId = 89999999;
        when(productService.updateProduct(incorrectProductId, expectedProduct)).thenThrow(NotFoundException.class);
        //When
        mockMvc.perform(put("/product/update/{productId}", incorrectProductId)
                        .content(objectMapper.writeValueAsString(expectedProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deleteProductById() throws Exception {
        //Given
        Product expectedProduct = preperedProduct();
        Integer productId = expectedProduct.getProductId();
        doNothing().when(productService).deleteProductById(productId);
        //When
        mockMvc.perform(delete("/product/delete/{productId}", productId))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteProductByIdWithNotFoundException() throws Exception {
        //Given
        Integer incorrectProductId = 999999999;
        doThrow(NotFoundException.class).when(productService).deleteProductById(incorrectProductId);
        //When
        mockMvc.perform(delete("/product/delete/{productId}", incorrectProductId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getProductsListByKeywordInProducerName() throws Exception {
        //Given
        List<Product> expectedProductList = preparedProductsList();
        String producerName = preparedProducer().getName().substring(5, 9);
        when(productService.getProductsListByKeywordInProducerName(producerName)).thenReturn(expectedProductList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/product/producer/name")
                        .param("producerName", producerName))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Product> actualProductsList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>() {
        });
        assertEquals(expectedProductList, actualProductsList);
    }

    @Test
    void getProductsListByPriceBetween() throws Exception {
        //Given
        List<Product> expectedProductList = preparedProductsList();
        Double startPrice = 8.0;
        Double endPrice = 16.0;
        when(productService.getProductsListByPriceBetween(startPrice, endPrice)).thenReturn(expectedProductList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/product/price")
                        .param("startPrice", String.valueOf(startPrice))
                        .param("endPrice", String.valueOf(endPrice)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Product> actualProductsList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>() {
        });
        assertEquals(expectedProductList, actualProductsList);
    }

    @Test
    void getProductByKeywordInProductName() throws Exception {
        //Given
        List<Product> expectedProductList = preparedProductsList();
        String keywordInName = preperedProduct().getName().substring(5, 9);
        when(productService.getProductsListByKeywordInProductName(keywordInName)).thenReturn(expectedProductList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/product/name")
                        .param("keywordInName", keywordInName))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Product> actualProductsList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Product>>() {
        });
        assertEquals(expectedProductList, actualProductsList);
    }

    private Product preperedProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setProducerId(preparedProducer().getProducerId());
        product.setName("test product");
        product.setCategory("test category");
        product.setPrice(12.5);
        product.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        product.setCreatedOn(Timestamp.valueOf(LocalDateTime.of(2022, 12, 8, 12, 30, 00)));
        return product;
    }

    private List<Product> preparedProductsList() {
        Product product = preperedProduct();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

    private Producer preparedProducer() {
        Producer producer = new Producer();
        producer.setProducerId(1);
        producer.setName("test producer name");
        producer.setCreatedOn(Timestamp.valueOf("2012-10-01 11:40:47"));
        producer.setCountry("test producer country");
        return producer;
    }

}