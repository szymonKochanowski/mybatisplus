package com.springbootmybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.springbootmybatis.entity.Produkt;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public interface IProduktService extends IService<Produkt> {

    Produkt getProduktById(Integer id_produktu);

    List<Produkt> getAllProdukty();

    void addProdukt(Produkt produkt);

    List<Produkt> getAllProduktyWithProducentName();

    void deleteProdukt(Integer id_produktu);

    void updateProdukt(Integer id_produktu, Produkt produkt);
}
