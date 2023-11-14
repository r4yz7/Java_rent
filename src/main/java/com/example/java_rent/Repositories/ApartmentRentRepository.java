package com.example.java_rent.Repositories;

import com.example.java_rent.Models.ApartmentRent;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRentRepository extends CrudRepository<ApartmentRent,Long> {
}
