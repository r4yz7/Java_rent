package com.example.java_rent.Repositories;

import com.example.java_rent.Models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,Long> {
    List<Client> findByFirstname(String firstname);

}
