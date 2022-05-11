package com.example.talaba2.Repository;

import com.example.talaba2.Model.Fanlar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanlarRepository extends JpaRepository<Fanlar, Integer> {
    boolean existsByNomi(String nomi);
}
