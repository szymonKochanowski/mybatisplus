package com.springbootmybatis.controller;

import com.springbootmybatis.entity.Produkt;
import com.springbootmybatis.service.IProduktService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/produkt")
public class ProduktController {

    @Autowired
    IProduktService produktService;

    @GetMapping("/{id_produktu}")
    public ResponseEntity<Produkt> getProduktById(@PathVariable Integer id_produktu) {
        return ResponseEntity.ok(produktService.getProduktById(id_produktu));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produkt>> getAllProdukty() {
        return ResponseEntity.ok(produktService.getAllProdukty());
    }

    //todo this method is not returning correctly producent name - need to fix
    @GetMapping("/all/producent/name")
    public ResponseEntity<List<Produkt>> getAllProduktyWithProducentName() {
        return ResponseEntity.ok(produktService.getAllProduktyWithProducentName());
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProdukt(@RequestBody Produkt produkt) {
        produktService.addProdukt(produkt);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id_produktu}")
    public ResponseEntity<Void> updateProdukt(@PathVariable Integer id_produktu, @RequestBody Produkt produkt) {
        produktService.updateProdukt(id_produktu, produkt);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id_produktu}")
    public ResponseEntity<Void> deleteProdukt(@PathVariable Integer id_produktu) {
        produktService.deleteProdukt(id_produktu);
        return ResponseEntity.ok().build();
    }
}
