package com.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootmybatis.entity.Product;
import com.springbootmybatis.mapper.ProductMapper;
import com.springbootmybatis.service.IProductService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Integer productId) throws NotFoundException {
        Product product = productMapper.selectById(productId);
        if (product != null) {
            return product;
        } else {
            log.error("Error in method getProductById! Product with id '" + productId + "' not found!");
            throw new NotFoundException("Product with id '" + productId + "' not found!");
        }
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
    public void deleteProductById(Integer productId) throws NotFoundException {
        Product product = productMapper.selectById(productId);
        if (product != null) {
            productMapper.deleteById(productId);
        } else {
            log.error("Error in method getProductById! Product with id '" + productId + "' not found!");
            throw new NotFoundException("Product with id '" + productId + "' not found!");
        }

    }

    @Override
    public Product updateProduct(Integer productId, Product product) throws NotFoundException {
        Product productFromDb = productMapper.selectById(productId);
        if (productFromDb != null) {
            productFromDb.setProducerId(product.getProducerId());
            productFromDb.setName(product.getName());
            productFromDb.setCategory(product.getCategory());
            productFromDb.setPrice(product.getPrice());
            productFromDb.setCreatedOn(product.getCreatedOn());
            productFromDb.setExpirationDate(product.getExpirationDate());
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("product_id", productId);
            productMapper.update(productFromDb, queryWrapper);
            return productFromDb;
        } else {
            log.error("Error in method updateProduct! Product with id '" + productId + "' not found!");
            throw new NotFoundException("Product with id '" + productId + "' not found!");
        }
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
