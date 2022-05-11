package com.example.talaba2.Controller;

import com.example.talaba2.DTO.TalabaDTO;
import com.example.talaba2.Model.Fanlar;
import com.example.talaba2.Model.Manzil;
import com.example.talaba2.Model.Talaba;
import com.example.talaba2.Repository.FanlarRepository;
import com.example.talaba2.Repository.ManzilRepository;
import com.example.talaba2.Repository.TalabaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/talaba")
public class TalabaController {
    @Autowired
    TalabaRepository talabaRepository;
    @Autowired
    FanlarRepository fanlarRepository;
    @Autowired
    ManzilRepository manzilRepository;
    @PostMapping
    public String talabaJoylash(@RequestBody TalabaDTO talabaDTO){
        Manzil manzil = new Manzil();
        manzil.setViloyat(talabaDTO.getViloyat());
        manzil.setTuman(talabaDTO.getTuman());
        manzil.setKocha(talabaDTO.getKocha());
        Manzil manzil1 = manzilRepository.save(manzil);
        Talaba talaba = new Talaba();
        talaba.setFamilya(talabaDTO.getFamilya());
        talaba.setIsm(talabaDTO.getIsm());
        talaba.setTelefon(talabaDTO.getTelefon());
        talaba.setManzil(manzil1);
        List<Fanlar> fanlars = new ArrayList<>();
        for (Integer i : talabaDTO.getFanlarList()) {
            fanlars.add(fanlarRepository.getById(i));
        }
        talaba.setFanlarList(fanlars);
        talabaRepository.save(talaba);
        return "Joylandi";
    }
}
