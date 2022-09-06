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
public class IProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

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
    public List<Product> getProductsListByPriceBetween(Integer startPrice, Integer endPrice) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("price", startPrice, endPrice);
        return productMapper.selectList(queryWrapper);
    }

    @Override
    public List<Product> getProductsListByKeywordInProductModel(String keywordInModel) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("model", keywordInModel);
        return productMapper.selectList(queryWrapper);
    }


}
