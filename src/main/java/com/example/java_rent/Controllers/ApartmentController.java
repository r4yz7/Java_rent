package com.example.java_rent.Controllers;

import com.example.java_rent.Models.Apartment;
import com.example.java_rent.Models.ApartmentRent;
import com.example.java_rent.Models.Client;
import com.example.java_rent.Models.Landlord;
import com.example.java_rent.Repositories.ApartmentRepository;
import com.example.java_rent.Repositories.ClientRepository;
import com.example.java_rent.Repositories.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @GetMapping("/")
    public String getApartment(Model model){
        Iterable<Apartment> apartments = apartmentRepository.findAll();
        model.addAttribute("apartments", apartments);
        return "apartment";
    }
    @GetMapping("/getAvailableApartments")
    public String getAvailableApartments(Model model){
        Iterable<Apartment> apartments = apartmentRepository.findByApartmentRentsIsEmpty();
        model.addAttribute("apartments",apartments);
        return "apartment";
    }

    @GetMapping("/apartments/addApartment")
    public String apartmentAdd(Model model){
        Iterable<Landlord> landlords = landlordRepository.findAll();
        model.addAttribute("landlords",landlords);
        return "addApartment";
    }
    @PostMapping("/apartments/addApartment")
    public String apartmentAdd(@RequestParam String address, @RequestParam double area,
                               @RequestParam int rooms, @RequestParam Long landlordId,Model model){
        Landlord landlord = landlordRepository.findById(landlordId).orElse(null);
        if(landlord!=null){
            Apartment apartment = new Apartment();
            apartment.setArea(area);
            apartment.setAddress(address);
            apartment.setRooms(rooms);
            apartment.setLandlord(landlord);
            apartmentRepository.save(apartment);
        }
        return "redirect:/";
    }
}
