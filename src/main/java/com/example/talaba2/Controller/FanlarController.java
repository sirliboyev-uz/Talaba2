package com.example.talaba2.Controller;

import com.example.talaba2.Model.Fanlar;
import com.example.talaba2.Model.Talaba;
import com.example.talaba2.Repository.FanlarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Fanlar")
public class FanlarController {
    @Autowired
    FanlarRepository fanlarRepository;

    //    @RequestMapping(value = "/Fanlar",method = RequestMethod.POST)
    @PostMapping
    public String malumot_joylash(@RequestBody Fanlar fanlar) {
//        List<Fanlar> fanlarList = fanlarRepository.findAll();
//
//        for (Fanlar i : fanlarList) {
//            if (!i.equals(fanlar.getNomi())) {
//                return "Bunday ma'lumot mavjud!!!";
//            }
//            System.out.println(i);
//        }
//        Fanlar fanlar1 = new Fanlar();
//        fanlar1.setNomi(fanlar.getNomi());
//        fanlarRepository.save(fanlar1);
//        return "Malumot joylandi!!!";

        boolean xolat=fanlarRepository.existsByNomi(fanlar.getNomi());
        if(xolat){
            return "bunday malumot bor";
        }
        Fanlar fanlar1=new Fanlar();
        fanlar1.setNomi(fanlar.getNomi());
        fanlarRepository.save(fanlar1);
        return "Malumot joylandi!!!";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String ochirish(@PathVariable Integer id){
        Optional<Fanlar> optionalFanlar=fanlarRepository.findById(id);
        if (optionalFanlar.isPresent()) {
            fanlarRepository.deleteById(id);
            return "Ma'lumot ochirildi";
        }
        return "Ma'lumot topilmadi";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String tahrirlash(@PathVariable Integer id, @RequestBody Fanlar fanlar){
        Optional<Fanlar> optionalFanlar=fanlarRepository.findById(id);
        if (optionalFanlar.isPresent()){
            Fanlar fanlar1 = optionalFanlar.get();
            fanlar1.setNomi(fanlar.getNomi());
            fanlarRepository.save(fanlar1);
            return "Muvaffaqiyatli yangilandi!";
        }
        return "Ma'lumot topilmadi";
    }
    @GetMapping
    public List<Fanlar> oqish(){
        List<Fanlar> fanlarList = fanlarRepository.findAll();
        return fanlarList;
    }
}
