package com.springbootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootmybatis.entity.Product;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IProductService extends IService<Product> {

    Product getProductById(Integer productId) throws NotFoundException;

    List<Product> getAllProducts();

    Product addProduct(Product product);

    List<Product> getProductsListByKeywordInProducerName(String producerName);

    void deleteProduct(Integer productId) throws NotFoundException;

    Product updateProduct(Integer productId, Product product) throws NotFoundException;

    List<Product> getProductsListByPriceBetween(Double startPrice, Double endPrice);

    List<Product> getProductsListByKeywordInProductName(String keywordInModel);

}
