package com.springbootmybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@TableName("producents")
public class Producer extends Model<Producer> {

    @TableId(type = IdType.AUTO, value = "producer_id")
    private Integer producerId;

    @NotNull
    @NotBlank
    private String name;

    @TableField(fill = FieldFill.INSERT, value = "created_on")
    private Timestamp createdOn;

    @NotNull
    @NotBlank
    private String country;

}
