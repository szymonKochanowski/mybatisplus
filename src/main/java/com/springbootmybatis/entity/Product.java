package com.springbootmybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@TableName("products")
public class Product extends Model<Product> {

    @TableId(type = IdType.AUTO, value = "product_id")
    private Integer productId;

    @TableField(value = "producer_id")
    private Integer producerId;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    private Double price;

    @TableField(fill = FieldFill.INSERT, value = "created_on")
    private Timestamp createdOn;

    @TableField(fill = FieldFill.INSERT, value = "expiration_date")
    private Timestamp expirationDate;
}
