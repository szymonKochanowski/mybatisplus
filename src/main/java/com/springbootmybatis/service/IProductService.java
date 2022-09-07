package com.springbootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootmybatis.entity.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IProductService extends IService<Product> {

    Product getProductById(Integer productId);

    List<Product> getAllProducts();

    Product addProduct(Product product);

    List<Product> getProductsListByKeywordInProducerName(String producerName);

    void deleteProduct(Integer productId);

    Product updateProduct(Integer productId, Product product);

    List<Product> getProductsListByPriceBetween(Integer startPrice, Integer endPrice);

    List<Product> getProductsListByKeywordInProductModel(String keywordInModel);

}
