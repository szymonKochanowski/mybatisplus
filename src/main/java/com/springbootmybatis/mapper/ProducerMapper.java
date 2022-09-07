package com.springbootmybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootmybatis.entity.Producer;

import java.util.List;

public interface ProducerMapper extends BaseMapper<Producer> {

    List<Producer> getProducersListByProductsCategory(String productModel);

}
