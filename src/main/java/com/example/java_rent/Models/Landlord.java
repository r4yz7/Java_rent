package com.example.java_rent.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "landlords")
public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String email;
    @OneToMany(mappedBy = "landlord")
    List<Apartment> apartments;
}
