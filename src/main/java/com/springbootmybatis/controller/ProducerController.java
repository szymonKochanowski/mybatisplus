package com.springbootmybatis.controller;

import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.service.IProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    IProducerService iProducerService;

    @PostMapping("/add")
    public ResponseEntity<Producer> addNewProducer(@RequestBody Producer producer) {
        log.info("Start to add new producer");
        try {
            return new ResponseEntity(iProducerService.addNewProducer(producer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
