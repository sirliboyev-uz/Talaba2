package com.example.talaba2.Repository;

import com.example.talaba2.Model.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FakultetRepository extends JpaRepository<Fakultet,Integer> {
    boolean existsByNomiAndUniversitetId(String nomi, Integer id);
    List<Fakultet> findAllByUniversitetId(Integer universitetId);
}
