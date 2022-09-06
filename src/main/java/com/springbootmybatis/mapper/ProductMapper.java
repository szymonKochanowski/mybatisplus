package com.springbootmybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootmybatis.entity.Product;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product>{

    List<Product> getProductsListByKeywordInProducerName(String producerName);

}
