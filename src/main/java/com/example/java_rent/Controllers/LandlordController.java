package com.example.java_rent.Controllers;

import com.example.java_rent.Models.Landlord;
import com.example.java_rent.Repositories.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LandlordController {
    @Autowired
    private LandlordRepository landlordRepository;
    @GetMapping("/landlord")
    public String getLandlord(Model model){
        Iterable<Landlord> landlords = landlordRepository.findAll();
        model.addAttribute("landlords",landlords);
        return "landlord";
    }

    @GetMapping("/landlord/{id}/delete")
    public String deleteLandlord(@PathVariable(value = "id")Long id, Model model){
        Landlord landlord = landlordRepository.findById(id).orElse(null);
        assert landlord!=null;
        landlordRepository.delete(landlord);
        return "redirect:/landlord";
    }

    @GetMapping("/landlord/add")
    public String landlordAdd(Model model){

        return "addLandlord";
    }

    @PostMapping("/landlord/add")
    public String landlordAdd(@RequestParam String companyName, @RequestParam String email, Model model){
        Landlord landlord = new Landlord();
        landlord.setEmail(email);
        landlord.setCompanyName(companyName);
        landlordRepository.save(landlord);
        return "redirect:/landlord";
    }

    @GetMapping("/landlord/{id}/edit")
    public String landlordEdit(@PathVariable(value = "id")Long id,Model model){
        Landlord landlord = landlordRepository.findById(id).orElse(null);
        assert landlord!=null;
        model.addAttribute("landlord",landlord);
        return "landlordEdit";
    }

    @PostMapping("/landlord/{id}/edit")
    public String landlordEdit(@PathVariable(value = "id")Long id, @RequestParam String companyName,
                               @RequestParam String email, Model model){
        Landlord landlord = landlordRepository.findById(id).orElse(null);
        assert landlord!=null;
        if(!companyName.equals(landlord.getCompanyName()))
            landlord.setCompanyName(companyName);
        if(!email.equals(landlord.getEmail()))
            landlord.setEmail(email);
        landlordRepository.save(landlord);
        return "redirect:/landlord";
    }

}
