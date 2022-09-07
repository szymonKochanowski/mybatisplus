package com.springbootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootmybatis.entity.Producer;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface IProducerService extends IService<Producer> {

    Producer addNewProducer(Producer producer);

    Producer getProducerById(Integer producerId) throws NotFoundException;

    List<Producer> getAllProducers();

    Producer editProducerById(Integer producerId, Producer producer) throws NotFoundException;

    String deleteProducerById(Integer producerId) throws NotFoundException;

    List<Producer> getProducerByKeywordInName(String keywordInName);

    List<Producer>  getProducerByKeywordInCountry(String keywordInCountry);

    List<Producer> getProducersListByCreateOnBetween(Timestamp startCreatedOn, Timestamp endCreateOn);

    List<Producer> getProducersListByProductsCategory(String productCategory);

}
