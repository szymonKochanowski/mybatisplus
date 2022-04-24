package com.springbootmybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.ui.Model;

@Data
@TableName("produkty")
public class Produkt {

    @TableId(type = IdType.AUTO)
    private Integer id_produktu;
    private Integer producent;
    private String model;
    private String kategoria;
    private Integer cena;
}
