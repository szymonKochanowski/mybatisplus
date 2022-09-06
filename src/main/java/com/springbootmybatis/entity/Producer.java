package com.springbootmybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("producents")
public class Producer extends Model<Producer> {

    @TableId(type = IdType.AUTO, value = "producer_id")
    private Integer producerId;

    private String name;

    @TableField(fill = FieldFill.INSERT, value = "created_on")
    private Timestamp createdOn;

    private String country;

}
