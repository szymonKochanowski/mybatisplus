package com.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootmybatis.entity.Producer;
import com.springbootmybatis.mapper.ProducerMapper;
import com.springbootmybatis.service.IProducerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ProducerService extends ServiceImpl<ProducerMapper, Producer> implements IProducerService {

    @Resource
    private ProducerMapper producerMapper;

    @Override
    public Producer addNewProducer(Producer producer) {
       producer.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
       producerMapper.insert(producer);
       return producer;
    }

}
