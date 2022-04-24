package com.springbootmybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduktInfo {

    @TableId(type = IdType.AUTO)
    private Integer id_produktu;
    private Integer producent;
    private String model;
    private String kategoria;
    private Integer cena;
}
