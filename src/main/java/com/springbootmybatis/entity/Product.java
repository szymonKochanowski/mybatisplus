package com.springbootmybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("products")
public class Product extends Model<Product> {

    @TableId(type = IdType.AUTO, value = "product_id")
    private Integer productId;

    @TableField(value = "producer_id")
    private Integer producerId;

    private String model;

    private String category;

    private Integer price;
}
