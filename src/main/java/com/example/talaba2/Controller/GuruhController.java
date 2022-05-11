package com.example.talaba2.Controller;

import com.example.talaba2.DTO.GuruhDTO;
import com.example.talaba2.Model.Fakultet;
import com.example.talaba2.Model.Guruh;
import com.example.talaba2.Repository.FakultetRepository;
import com.example.talaba2.Repository.GuruhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Guruh")
public class GuruhController {
    @Autowired
    GuruhRepository guruhRepository;
    @Autowired
    FakultetRepository fakultetRepository;

    @PostMapping
    public String malumot_joylash(@RequestBody GuruhDTO guruhDTO) {
        Optional<Fakultet> fakultetOptional = fakultetRepository.findById(guruhDTO.fakultetId);
        if(!fakultetOptional.isPresent()){
            return "bunday fakultet mavjud emas";
        }
        Guruh guruh=new Guruh();
        guruh.setNomi(guruhDTO.nomi);
        guruh.setFakultet(fakultetOptional.get());
        if (guruhRepository.existsByNomiAndFakultetId(guruhDTO.nomi,guruhDTO.fakultetId)){
            return "Bunday guruh mavjud";
        }
        guruhRepository.save(guruh);
        return "Malumot joylandi!!!";
    }
    @DeleteMapping(value = "/{id}")
    public String guruhOchirish(@PathVariable Integer id){
        Optional<Guruh> optionalGuruh = guruhRepository.findById(id);
        if (optionalGuruh.isPresent()){
            guruhRepository.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot topilmadi";
    }
    @PutMapping(value = "/{id}")
    public String guruhYangilash(@PathVariable Integer id , @RequestBody GuruhDTO guruhDTO){
        Optional<Guruh> guruhOptional = guruhRepository.findById(id);
        if (!guruhOptional.isPresent()){
            return "Bunday guruh yo'q";
        }
        Guruh guruh = guruhOptional.get();
        guruh.setNomi(guruhDTO.nomi);
        guruhRepository.save(guruh);
        return "Tahrirlandi";
    }
}