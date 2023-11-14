package com.example.java_rent.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apartmentRents")
public class ApartmentRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "apartmentId")
    private Apartment apartment;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    private Date rentDate;
    private Date returnDate;
}
