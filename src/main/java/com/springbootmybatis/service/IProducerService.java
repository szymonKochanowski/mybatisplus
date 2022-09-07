package com.springbootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootmybatis.entity.Producer;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface IProducerService extends IService<Producer> {

    Producer addNewProducer(Producer producer);

    Producer getProducerById(Integer producerId);

    List<Producer> getAllProducers();

    Producer editProducerById(Integer producerId, Producer producer);

    String deleteProducerById(Integer producerId);

    List<Producer> getProducerByKeywordInName(String keywordInName);

    List<Producer>  getProducerByKeywordInCountry(String keywordInCountry);

    List<Producer> getProducersListByCreateOnBetween(Timestamp startCreatedOn, Timestamp endCreateOn);

    List<Producer> getProducersListByProductsCategory(String productCategory);

}
