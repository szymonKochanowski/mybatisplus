package com.springbootmybatis.service.impl;

import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.entity.Product;
import com.springbootmybatis.mapper.ProducerMapper;
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
class ProducerServiceImplTest {

    @InjectMocks
    private ProducerServiceImpl producerService;
    @Mock
    private ProducerMapper producerMapper;

    @Test
    void addNewProducer() {
        //Given
        Producer expectedProducer = preparedProducer();
        when(producerMapper.insert(expectedProducer)).thenReturn(expectedProducer.getProducerId());
        //When
        Producer actualProducer = producerService.addNewProducer(expectedProducer);
        //Then
        assertEquals(expectedProducer, actualProducer);
    }

    @Test
    void getProducerById() throws NotFoundException {
        //Given
        Producer expectedProducer = preparedProducer();
        Integer producerId = expectedProducer.getProducerId();
        when(producerMapper.selectById(producerId)).thenReturn(expectedProducer);
        //When
        Producer actualProducer = producerService.getProducerById(producerId);
        //Then
        assertEquals(expectedProducer, actualProducer);
    }

    @Test
    void getProducerByIdReturnNotFoundException() throws NotFoundException {
        //Given
        Integer incorrectProducerId = 9999;
        when(producerMapper.selectById(incorrectProducerId)).thenReturn(null);
        //When
        //Then
        assertThrows(NotFoundException.class, () -> producerService.getProducerById(incorrectProducerId),
                "Producer with id '" + incorrectProducerId + "' not found!");
    }

    @Test
    void getAllProducers() {
        //Given
        List<Producer> expectedProducersList = preparedProducersList();
        when(producerMapper.selectList(any())).thenReturn(expectedProducersList);
        //When
        List<Producer> actualProducersList = producerService.getAllProducers();
        //Then
        assertEquals(expectedProducersList, actualProducersList);
    }

    @Test
    void editProducerById() throws NotFoundException {
        //Given
        Producer expectedProducer = preparedProducer();
        expectedProducer.setName("edited producer name");
        expectedProducer.setCountry("edited producer country");
        Integer producerId = expectedProducer.getProducerId();
        when(producerMapper.selectById(anyInt())).thenReturn(expectedProducer);
        when(producerMapper.update(any(), any())).thenReturn(expectedProducer.getProducerId());
        //When
        Producer actualProducer = producerService.editProducerById(producerId, expectedProducer);
        //Then
        assertEquals(expectedProducer, actualProducer);
    }

    @Test
    void editProducerByIdReturnNotFoundException() throws NotFoundException {
        //Given
        Producer expectedProducer = preparedProducer();
        expectedProducer.setName("edited producer name");
        expectedProducer.setCountry("edited producer country");
        Integer incorrectProducerId = 99999999;
        when(producerMapper.selectById(anyInt())).thenReturn(null);
        //When
        //Then
        assertThrows(NotFoundException.class, () -> producerService.editProducerById(incorrectProducerId, expectedProducer),
                "Producer with id '" + incorrectProducerId + " not found!");
    }

    @Test
    void deleteProducerById() throws NotFoundException {
        //Given
        Producer producer = preparedProducer();
        Integer producerId = producer.getProducerId();
        String expectedResponse = "Producer with id '" + producerId + "' deleted successfully!";
        when(producerMapper.selectById(anyInt())).thenReturn(producer);
        when(producerMapper.deleteById(any())).thenReturn(producer.getProducerId());
        //When
        String actualResponse = producerService.deleteProducerById(producerId);
        //Then
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void deleteProducerByIdReturnNotFoundException() throws NotFoundException {
        //Given
        Integer incorrectProducerId = 99999;
        when(producerMapper.selectById(anyInt())).thenReturn(null);
        //When
        //Then
        assertThrows(NotFoundException.class, () -> producerService.deleteProducerById(incorrectProducerId),
                "Producer with id '" + incorrectProducerId + " not found!");
    }

    @Test
    void getProducerByKeywordInName() {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        String keywordInName = preparedProducer().getName().substring(3, 5);
        when(producerMapper.selectList(any())).thenReturn(expectedProducerList);
        //When
        List<Producer> actualProducerList = producerService.getProducerByKeywordInName(keywordInName);
        //Then
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void getProducerByKeywordInCountry() {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        String keywordInCountry = preparedProducer().getCountry().substring(3, 5);
        when(producerMapper.selectList(any())).thenReturn(expectedProducerList);
        //When
        List<Producer> actualProducerList = producerService.getProducerByKeywordInCountry(keywordInCountry);
        //Then
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void getProducersListByCreateOnBetween() {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        Timestamp startCreatedOn = Timestamp.valueOf("2012-09-01 11:40:47");
        Timestamp endCreateOn = Timestamp.valueOf("2012-12-01 11:40:47");
        when(producerMapper.selectList(any())).thenReturn(expectedProducerList);
        //When
        List<Producer> actualProducerList = producerService.getProducersListByCreateOnBetween(startCreatedOn, endCreateOn);
        //Then
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void getProducersListByProductsCategory() {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        String productCategory = preparedProduct().getCategory().substring(5, 8);
        when(producerMapper.getProducersListByProductsCategory(productCategory)).thenReturn(expectedProducerList);
        //When
        List<Producer> actualProducerList = producerService.getProducersListByProductsCategory(productCategory);
        //Then
        assertEquals(expectedProducerList, actualProducerList);
    }

    private Producer preparedProducer() {
        Producer producer = new Producer();
        producer.setProducerId(1);
        producer.setName("test producer name");
        producer.setCreatedOn(Timestamp.valueOf("2012-10-01 11:40:47"));
        producer.setCountry("test producer country");
        return producer;
    }

    private List<Producer> preparedProducersList() {
        Producer product = preparedProducer();
        List<Producer> producerList = new ArrayList<>();
        producerList.add(product);
        return producerList;
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

}