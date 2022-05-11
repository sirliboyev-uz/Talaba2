package com.example.talaba2.Repository;
import com.example.talaba2.Model.Universitet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversitetRepository extends JpaRepository<Universitet, Integer>{

}
