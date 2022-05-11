package com.example.talaba2.Repository;

import com.example.talaba2.Model.Guruh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruhRepository extends JpaRepository<Guruh, Integer> {
    boolean existsByNomiAndFakultetId(String nomi, Integer id);

}
