package com.springbootmybatis.controller;

import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.service.IProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private IProducerService producerService;

    @PostMapping("/add")
    public ResponseEntity<Producer> addNewProducer(@Valid @RequestBody Producer producer) {
        log.info("Start to add new producer.");
        return new ResponseEntity(producerService.addNewProducer(producer), HttpStatus.CREATED);
    }

    @GetMapping("/{producerId}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Integer producerId) {
        log.info("Start to get producer by id: " + producerId + ".");
        try {
            return ResponseEntity.ok(producerService.getProducerById(producerId));
        } catch (NotFoundException e) {
            log.error("Error in GET method getProducerById!", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producer>> getAllProducers() {
        log.info("Start to get all producer.");
        return ResponseEntity.ok(producerService.getAllProducers());
    }

    @PutMapping("/edit/{producerId}")
    public ResponseEntity<Producer> editProducerById(@PathVariable Integer producerId, @Valid @RequestBody Producer producer) {
        log.info("Start to edit producer by id: " + producerId + ".");
        try {
            return ResponseEntity.ok(producerService.editProducerById(producerId, producer));
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{producerId}")
    public ResponseEntity<String> deleteProducerById(@PathVariable Integer producerId) {
        log.info("Start to delete producer by id: " + producerId + ".");
        try {
            return new ResponseEntity(producerService.deleteProducerById(producerId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name")
    public ResponseEntity<List<Producer>> getProducersListByKeywordInName(@RequestParam String keywordInName) {
        log.info("Start to search producer by keyword '" + keywordInName + "' in producer name.");
        return ResponseEntity.ok(producerService.getProducerByKeywordInName(keywordInName));
    }

    @GetMapping("/country")
    public ResponseEntity<List<Producer>> getProducersListByKeywordInCountry(@RequestParam String keywordInCountry) {
        log.info("Start to search producer by keyword '" + keywordInCountry + "' in producer country.");
        return ResponseEntity.ok(producerService.getProducerByKeywordInCountry(keywordInCountry));
    }

    @GetMapping("/create")
    public ResponseEntity<List<Producer>> getProducersListByCreateOnBetween(@RequestParam Timestamp startCreatedOn, @RequestParam Timestamp endCreateOn) {
        log.info("Start to search producer list by date create on between '" + startCreatedOn + "' and '" + endCreateOn + "'.");
        return ResponseEntity.ok(producerService.getProducersListByCreateOnBetween(startCreatedOn, endCreateOn));
    }

    @GetMapping("/product/model")
    public ResponseEntity<List<Producer>> getProducersListByProductsCategory(@RequestParam String productCategory) {
        log.info("Start to search producer list by product category '" + productCategory + "'.");
        return ResponseEntity.ok(producerService.getProducersListByProductsCategory(productCategory));
    }

}
