package com.example.java_rent.Repositories;

import com.example.java_rent.Models.Apartment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApartmentRepository extends CrudRepository<Apartment,Long> {
    List<Apartment> findByApartmentRentsIsEmpty();
}
