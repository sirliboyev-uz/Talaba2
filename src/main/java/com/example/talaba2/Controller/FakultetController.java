package com.example.talaba2.Controller;

import com.example.talaba2.DTO.FakultetDTO;
import com.example.talaba2.Model.Fakultet;
import com.example.talaba2.Model.Manzil;
import com.example.talaba2.Model.Universitet;
import com.example.talaba2.Repository.FakultetRepository;
import com.example.talaba2.Repository.UniversitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/Fakultet")

public class FakultetController {
    @Autowired
    UniversitetRepository universitetRepository;
    @Autowired
    FakultetRepository fakultetRepository;
    @PostMapping
    public String fakultetQoshish(@RequestBody FakultetDTO fakultetDTO){
        Optional<Universitet> optionalUniversitet = universitetRepository.findById(fakultetDTO.getUniversitetId());
        if (!optionalUniversitet.isPresent()){
            return "Bunday universitet mavjud emas!";
        }
        Fakultet fakultet = new Fakultet();
        fakultet.setNomi(fakultetDTO.getNomi());
        fakultet.setUniversitet(optionalUniversitet.get());
//        List<Fakultet> fakultetList = fakultetRepository.findAll();
//        for (Fakultet i : fakultetList) {
//            if (i.getNomi().equals(fakultetDTO.getNomi()) && i.getUniversitet().equals(optionalUniversitet.get())) {
//                return "Bunday ma'lumot mavjud";
//            }
//        }
        if (fakultetRepository.existsByNomiAndUniversitetId(fakultetDTO.getNomi(),fakultetDTO.getUniversitetId())){
            return "Bunday ma'lumot mavjud";
        }
        fakultetRepository.save(fakultet);
        return "Muvaffaqiyatli joylandi!";
    }
    @GetMapping(value = "/oqish/{id}")
    public List<Fakultet> idBoyichaChiqish(@PathVariable Integer id){
        List<Fakultet> fakultetList = fakultetRepository.findAllByUniversitetId(id);
        return fakultetList;
    }

    @RequestMapping(value = "/Fakultet/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id){
        Optional<Fakultet> optionalFakultet = fakultetRepository.findById(id);
        if (optionalFakultet.isPresent()){
            fakultetRepository.deleteById(id);
            universitetRepository.deleteById(id);
            return "Malumot ochirildi";
        }
        return "Malumot topilmadi";
    }
    @RequestMapping(value = "/Fakultet/{id}",method = RequestMethod.PUT)
    public String update_fakultet(@PathVariable Integer id , @RequestBody FakultetDTO fakultetDto){
        Optional<Fakultet> optionalFakultet = fakultetRepository.findById(id);
        if (optionalFakultet.isPresent()){
            List<Fakultet> fakultets = fakultetRepository.findAll();
            for (Fakultet i: fakultets) {
                if (i.getNomi().equals(fakultetDto.getNomi())){
                    return "Universitetda bunday fakultet mavjud";
                }
                Fakultet fakultet = optionalFakultet.get();
                fakultet.setNomi(fakultetDto.getNomi());
                fakultetRepository.save(fakultet);
                return "Tahrirlandi";

            }
        }
        return "Bunday malumot topilmadi";
    }
}
