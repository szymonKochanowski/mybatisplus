package com.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.mapper.ProducerMapper;
import com.springbootmybatis.service.IProducerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProducerServiceImpl extends ServiceImpl<ProducerMapper, Producer> implements IProducerService {

    @Resource
    private ProducerMapper producerMapper;

    @Override
    public Producer addNewProducer(Producer producer) {
       producer.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
       producerMapper.insert(producer);
       return producer;
    }

    @Override
    public Producer getProducerById(Integer producerId) {
        return producerMapper.selectById(producerId);
    }

    @Override
    public List<Producer> getAllProducers() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        return producerMapper.selectList(queryWrapper);
    }

    @Override
    public Producer editProducerById(Integer producerId, Producer producer) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("producer_id", producerId);
        producerMapper.update(producer, updateWrapper);
        return producer;
    }

    @Override
    public String deleteProducerById(Integer producerId) {
        producerMapper.deleteById(producerId);
        return "Producer with id '" + producerId + "' deleted succesfully!";
    }

    @Override
    public List<Producer> getProducerByKeywordInName(String keywordInName) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keywordInName);
        return producerMapper.selectList(queryWrapper);
    }

    @Override
    public List<Producer> getProducerByKeywordInCountry(String keywordInCountry) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.like("country", keywordInCountry);
        return producerMapper.selectList(queryWrapper);
    }

    @Override
    public List<Producer> getProducersListByCreateOnBetween(Timestamp startCreatedOn, Timestamp endCreateOn) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.between("created_on", startCreatedOn, endCreateOn);
        return producerMapper.selectList(queryWrapper);
    }

    @Override
    public List<Producer> getProducersListByProductsCategory(String productCategory) {
        return producerMapper.getProducersListByProductsCategory(productCategory);
    }


}
