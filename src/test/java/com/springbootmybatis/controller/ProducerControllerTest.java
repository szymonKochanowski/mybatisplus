package com.springbootmybatis.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.entity.Product;
import com.springbootmybatis.service.IProducerService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IProducerService producerService;

    @Test
    void addNewProducer() throws Exception {
        //Given
        Producer expectedProducer = preparedProducer();
        when(producerService.addNewProducer(expectedProducer)).thenReturn(expectedProducer);
        //When
        MvcResult mvcResult = mockMvc.perform(post("/producer/add")
                        .content(objectMapper.writeValueAsString(expectedProducer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201))
                .andReturn();
        //Then
        Producer actualProducer = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Producer.class);
        assertEquals(expectedProducer, actualProducer);
    }

    @Test
    void getProducerById() throws Exception {
        //Given
        Producer expectedProducer = preparedProducer();
        Integer producerId = expectedProducer.getProducerId();
        when(producerService.getProducerById(producerId)).thenReturn(expectedProducer);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/producer/{producerId}", producerId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        Producer actualProducer = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Producer.class);
        assertEquals(expectedProducer, actualProducer);
    }

    @Test
    void getProducerByIdWithNotFoundException() throws Exception {
        //Given
        Integer incorrectProducerId = preparedProducer().getProducerId();
        when(producerService.getProducerById(incorrectProducerId)).thenThrow(NotFoundException.class);
        //When
        mockMvc.perform(get("/producer/{producerId}", incorrectProducerId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getAllProducers() throws Exception {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        when(producerService.getAllProducers()).thenReturn(expectedProducerList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/producer/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Producer> actualProducerList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Producer>>() {
        });
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void editProducerById() throws Exception {
        //Given
        Producer expectedEditProducer = preparedProducer();
        expectedEditProducer.setName("edited name");
        expectedEditProducer.setCountry("edited country");
        Integer producerId = expectedEditProducer.getProducerId();
        when(producerService.editProducerById(producerId, expectedEditProducer)).thenReturn(expectedEditProducer);
        //When
        MvcResult mvcResult = mockMvc.perform(put("/producer/edit/{producerId}", producerId)
                        .content(objectMapper.writeValueAsString(expectedEditProducer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        Producer actualProducer = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Producer.class);
        assertEquals(expectedEditProducer, actualProducer);
    }

    @Test
    void editProducerByIdWithNotFoundException() throws Exception {
        //Given
        Producer expectedEditProducer = preparedProducer();
        expectedEditProducer.setName("edited name");
        expectedEditProducer.setCountry("edited country");
        Integer incorrectProducerId = 9999999;
        when(producerService.editProducerById(incorrectProducerId, expectedEditProducer)).thenThrow(NotFoundException.class);
        //When
        mockMvc.perform(put("/producer/edit/{producerId}", incorrectProducerId)
                        .content(objectMapper.writeValueAsString(expectedEditProducer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deleteProducerById() throws Exception {
        //Given
        Integer producerId = preparedProducer().getProducerId();
        String expectedResponse = "Producer with id '1' deleted successfully!";
        when(producerService.deleteProducerById(any())).thenReturn(expectedResponse);
        //When
        mockMvc.perform(delete("/producer/delete/{producerId}", producerId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteProducerByIdWithNotFoundException() throws Exception {
        //Given
        Integer incorrectProducerId = 99999;
        when(producerService.deleteProducerById(any())).thenThrow(NotFoundException.class);
        //When
        mockMvc.perform(delete("/producer/delete/{producerId}", incorrectProducerId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getProducersListByKeywordInName() throws Exception {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        String keywordInName = preparedProducer().getName().substring(3, 8);
        when(producerService.getProducerByKeywordInName(keywordInName)).thenReturn(expectedProducerList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/producer/name")
                        .param("keywordInName", keywordInName))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Producer> actualProducerList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Producer>>() {
        });
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void getProducersListByKeywordInCountry() throws Exception {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        String keywordInCountry = preparedProducer().getCountry().substring(3, 8);
        when(producerService.getProducerByKeywordInCountry(keywordInCountry)).thenReturn(expectedProducerList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/producer/country")
                        .param("keywordInCountry", keywordInCountry))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Producer> actualProducerList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Producer>>() {
        });
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void getProducersListByCreateOnBetween() throws Exception {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        Timestamp startCreatedOn = Timestamp.valueOf("2012-09-01 11:40:47");
        Timestamp endCreateOn = Timestamp.valueOf("2012-12-01 11:40:47");
        when(producerService.getProducersListByCreateOnBetween(startCreatedOn, endCreateOn)).thenReturn(expectedProducerList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/producer/create")
                        .param("startCreatedOn", String.valueOf(startCreatedOn))
                        .param("endCreateOn", String.valueOf(endCreateOn)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Producer> actualProducerList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Producer>>() {
        });
        assertEquals(expectedProducerList, actualProducerList);
    }

    @Test
    void getProducersListByProductsCategory() throws Exception {
        //Given
        List<Producer> expectedProducerList = preparedProducersList();
        String productCategory = preparedProduct().getCategory();
        when(producerService.getProducersListByProductsCategory(productCategory)).thenReturn(expectedProducerList);
        //When
        MvcResult mvcResult = mockMvc.perform(get("/producer/product/model")
                        .param("productCategory", productCategory))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        List<Producer> actualProducerList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Producer>>() {
        });
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