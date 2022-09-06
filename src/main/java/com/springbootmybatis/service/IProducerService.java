package com.springbootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootmybatis.entity.Producer;
import org.springframework.stereotype.Service;

@Service
public interface IProducerService extends IService<Producer> {

    Producer addNewProducer(Producer producer);

}
