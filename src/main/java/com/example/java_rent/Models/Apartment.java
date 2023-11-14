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
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int rooms;
    private double area;

    @ManyToOne
    @JoinColumn(name = "landlordId")
    private Landlord landlord;

    @OneToMany(mappedBy = "apartment")
    private List<ApartmentRent> apartmentRents;

    @OneToMany(mappedBy = "apartment")
    private List<ApartmentImage> apartmentImages;
}
