package com.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.mapper.ProducerMapper;
import com.springbootmybatis.service.IProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
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
    public Producer getProducerById(Integer producerId) throws NotFoundException {
        Producer producer = producerMapper.selectById(producerId);
        if (producer != null) {
            return producer;
        } else {
            log.error("Error in method getProducerById! Producer with id '" + producerId + "' not found!");
            throw new NotFoundException("Producer with id '" + producerId + "' not found!");
        }
    }

    @Override
    public List<Producer> getAllProducers() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        return producerMapper.selectList(queryWrapper);
    }

    @Override
    public Producer editProducerById(Integer producerId, Producer producer) throws NotFoundException {
        Producer producerFromDb = producerMapper.selectById(producerId);
        if (producerFromDb != null) {
            producerFromDb.setName(producer.getName());
            producerFromDb.setCountry(producer.getCountry());
            UpdateWrapper<Producer> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("producer_id", producerId);
            producerMapper.update(producerFromDb, updateWrapper);
            return producerFromDb;
        } else {
            log.error("Error in method editProducerById! Producer with id '" + producerId + " not found!");
            throw new NotFoundException("Producer with id '" + producerId + " not found!");
        }
    }

    @Override
    public String deleteProducerById(Integer producerId) throws NotFoundException {
        Producer producer = producerMapper.selectById(producerId);
        if (producer != null) {
            producerMapper.deleteById(producerId);
            return "Producer with id '" + producerId + "' deleted succesfully!";
        } else {
            log.error("Error in method deleteProducerById! Producer with id '" + producerId + " not found!");
            throw new NotFoundException("Producer with id '" + producerId + " not found!");
        }
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
