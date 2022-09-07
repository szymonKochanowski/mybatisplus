package com.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootmybatis.entity.Product;
import com.springbootmybatis.mapper.ProductMapper;
import com.springbootmybatis.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public Product getProductById(Integer productId) {
        return productMapper.selectById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public Product addProduct(Product product) {
       productMapper.insert(product);
       return product;
    }

    @Override
    public List<Product> getProductsListByKeywordInProducerName(String producerName) {
        return productMapper.getProductsListByKeywordInProducerName(producerName);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productMapper.deleteById(productId);
    }

    @Override
    public Product updateProduct(Integer productId, Product product) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        productMapper.update(product, queryWrapper);
        return product;
    }

    @Override
    public List<Product> getProductsListByPriceBetween(Double startPrice, Double endPrice) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("price", startPrice, endPrice);
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public List<Product> getProductsListByKeywordInProductName(String keywordInName) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keywordInName);
        return productMapper.selectList(queryWrapper);
    }

}
