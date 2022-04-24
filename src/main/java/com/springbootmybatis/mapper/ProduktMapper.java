package com.springbootmybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springbootmybatis.entity.Produkt;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProduktMapper extends BaseMapper<Produkt>{

    @Select("select * from sklep.produkty where id_produktu = #{id_produktu}")
    Produkt getProduktById(Integer id_produktu);


    @Select("select * from sklep.produkty")
    List<Produkt> getAllProdukty();



//    @Results(id = "produktResultMap", value = {
//            @Result(property = "id_produktu", column = "id_produktu"),
//            @Result(property = "producent", column = "producent"),
//            @Result(property = "model", column = "model"),
//            @Result(property = "kategoria", column = "kategoria"),
//           @Result(property = "cena", column = "cena")
//    })
    @Insert("insert into sklep.produkty (producent, model, kategoria, cena) values (#{producent}, #{model}, #{kategoria}, #{cena})")
    @Options(useGeneratedKeys = true, keyProperty = "id_produktu", keyColumn = "id_produktu")
    void addProdukt(Produkt produkt);

    @Select("select sklep.produkty.id_produktu, producenci.nazwa, produkty.model, produkty.kategoria, produkty.cena \n" +
            "from sklep.produkty\n" +
            "inner join producenci \n" +
            "on produkty.producent = producenci.id_producenta")
    List<Produkt> getAllProduktyWithProducentName();

    @Delete("delete from sklep.produkty where id_produktu = #{id_produktu}")
    void deleteProdukt(Integer id_produktu);

    @Update("update sklep.produkty set producent = #{producent}, model = #{model}, kategoria = #{kategoria}, cena = #{cena} where id_produktu = #{id_produktu}")
    void updateProdukt(Produkt produkt);
}
