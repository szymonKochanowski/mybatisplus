package com.springbootmybatis.controller;

import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.service.IProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    IProducerService producerService;

    @PostMapping("/add")
    public ResponseEntity<Producer> addNewProducer(@RequestBody Producer producer) {
        log.info("Start to add new producer");
        try {
            return new ResponseEntity<Producer>(producerService.addNewProducer(producer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{producerId}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Integer producerId) {
        return ResponseEntity.ok(producerService.getProducerById(producerId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producer>> getAllProducers() {
        return ResponseEntity.ok(producerService.getAllProducers());
    }

    @PutMapping("/edit/{producerId}")
    public ResponseEntity<Producer> editProducerById(@PathVariable Integer producerId, @RequestBody Producer producer) {
        return ResponseEntity.ok(producerService.editProducerById(producerId, producer));
    }

    @DeleteMapping("/delete/{producerId}")
    public ResponseEntity<String> deleteProducerById(@PathVariable Integer producerId) {
        return new ResponseEntity(producerService.deleteProducerById(producerId), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Producer>> getProducersListByKeywordInName(@RequestParam String keywordInName) {
        return ResponseEntity.ok(producerService.getProducerByKeywordInName(keywordInName));
    }

    @GetMapping("/country")
    public ResponseEntity<List<Producer>> getProducersListByKeywordInCountry(@RequestParam String keywordInCountry) {
        return ResponseEntity.ok(producerService.getProducerByKeywordInCountry(keywordInCountry));
    }

    @GetMapping("/create")
    public ResponseEntity<List<Producer>> getProducersListByCreateOnBetween(@RequestParam Timestamp startCreatedOn, @RequestParam Timestamp endCreateOn) {
        return ResponseEntity.ok(producerService.getProducersListByCreateOnBetween(startCreatedOn, endCreateOn));
    }

    @GetMapping("/product/model")
    public ResponseEntity<List<Producer>> getProducersListByProductsCategory(@RequestParam String productCategory) {
        return ResponseEntity.ok(producerService.getProducersListByProductsCategory(productCategory));
    }

}
