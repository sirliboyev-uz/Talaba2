package com.example.talaba2.Model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Talaba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ism;

    @Column(nullable = false)
    private String familya;

    @Column(nullable = false, unique = true)
    private String telefon;

    @OneToOne
    Manzil manzil;

//    @ManyToOne
//    Guruh guruh;

    @ManyToMany
    List<Fanlar> fanlarList;
}