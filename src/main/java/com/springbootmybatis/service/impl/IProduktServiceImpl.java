package com.springbootmybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springbootmybatis.entity.Produkt;
import com.springbootmybatis.mapper.ProduktMapper;
import com.springbootmybatis.service.IProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProduktServiceImpl extends ServiceImpl<ProduktMapper, Produkt> implements IProduktService {

    @Autowired
    ProduktMapper produktMapper;

    @Override
    public Produkt getProduktById(Integer id_produktu) {
        return produktMapper.getProduktById(id_produktu);
    }

    @Override
    public List<Produkt> getAllProdukty() {
        return produktMapper.getAllProdukty();
    }

    @Override
    public void addProdukt(Produkt produkt) {
       produktMapper.addProdukt(produkt);
    }

    @Override
    public List<Produkt> getAllProduktyWithProducentName() {
        return produktMapper.getAllProduktyWithProducentName();
    }

    @Override
    public void deleteProdukt(Integer id_produktu) {
        produktMapper.deleteProdukt(id_produktu);
    }

    @Override
    public void updateProdukt(Integer id_produktu, Produkt produkt) {
        produkt.setId_produktu(id_produktu);
        produktMapper.updateProdukt(produkt);
    }
}
