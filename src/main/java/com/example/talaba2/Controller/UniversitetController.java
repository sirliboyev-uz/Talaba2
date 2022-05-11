package com.example.talaba2.Controller;

import com.example.talaba2.DTO.UniversitetDTO;
import com.example.talaba2.Model.Manzil;
import com.example.talaba2.Model.Universitet;
import com.example.talaba2.Repository.ManzilRepository;
import com.example.talaba2.Repository.UniversitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversitetController {
    @Autowired
    UniversitetRepository universitetRepository;

    @Autowired
    ManzilRepository manzilRepository;

    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<Universitet> showDB(){
        List<Universitet> universitetList = universitetRepository.findAll();
        return universitetList;
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String inputDB(@RequestBody UniversitetDTO universitetDTO){
        Manzil manzil = new Manzil();
        manzil.setViloyat(universitetDTO.getViloyat());
        manzil.setTuman(universitetDTO.getTuman());
        manzil.setKocha(universitetDTO.getKocha());

        Manzil manzilSaqlash = manzilRepository.save(manzil);

        Universitet universitet = new Universitet();
        universitet.setNomi(universitetDTO.getNomi());
        universitet.setManzil(manzilSaqlash);

        universitetRepository.save(universitet);
        return "Muvaffaqiyatli joylandi";
    }

    @RequestMapping (value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteDB(@PathVariable Integer id){
        Optional<Universitet> optionalTalaba = universitetRepository.findById(id);
        if (optionalTalaba.isPresent()){
            universitetRepository.deleteById(id);
            manzilRepository.deleteById(id);
            return "Ma'lumot o'chirildi";
        }
        return "Topilmadi";
    }

    @RequestMapping (value = "/university/{id}", method = RequestMethod.PUT)
    public String updateDB(@PathVariable Integer id, @RequestBody UniversitetDTO universityDTO){
        Optional<Universitet> optionalUniversity = universitetRepository.findById(id);
        Optional<Manzil> optionalAddress = manzilRepository.findById(id);

        boolean holat=false;

        if (optionalUniversity.isPresent()){
            Universitet university1 = optionalUniversity.get();
            Manzil manzil = optionalAddress.get();
            manzil.setKocha(universityDTO.getKocha());
            manzil.setViloyat(universityDTO.getViloyat());
            manzil.setTuman(universityDTO.getTuman());
            university1.setManzil(manzil);
            university1.setNomi(universityDTO.getNomi());
            universitetRepository.save(university1);
            manzilRepository.save(manzil);
            holat=true;
        }
        return holat ? "Muvaffaqiyatli yangilandi":"Ma'lumot topilmadi";
    }
}