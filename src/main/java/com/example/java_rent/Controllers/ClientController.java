package com.example.java_rent.Controllers;

import com.example.java_rent.Models.Client;
import com.example.java_rent.Repositories.ClientRepository;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public String getClient(Model model){
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client";
    }
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestParam String firstname, @RequestParam String surname, @RequestParam String email, Model model){
      Client client = new Client();
      client.setFirstname(firstname);
      client.setSurname(surname);
      client.setEmail(email);
      clientRepository.save(client);
        return "redirect:/";
    }

    @PostMapping("/client/searchByFirstname")
    public String searchByFirstname(@RequestParam String firstname,Model model){
        List<Client> clients = clientRepository.findByFirstname(firstname);
        model.addAttribute("clients",clients);
        return "client";
    }
    @GetMapping("/client/{id}/delete")
    public String clientDelete(@PathVariable(value = "id")Long id,Model model){
        Client client = clientRepository.findById(id).orElse(null);
        assert client !=null;
        clientRepository.delete(client);
        return "redirect:/client";
    }

    @GetMapping("/client/{id}/edit")
    public String clientEdit(@PathVariable(value = "id")Long id,Model model){
        Client client = clientRepository.findById(id).orElse(null);
        assert client !=null;
        model.addAttribute("client",client);
        return "edit";
    }

    @PostMapping("/client/{id}/edit")
    public String clientEdit(@PathVariable(value = "id")Long id, @RequestParam String firstname,
                             @RequestParam String surname, @RequestParam String email, Model model){
        Client client = clientRepository.findById(id).orElse(null);
        assert client!=null;
        if(!firstname.equals(client.getFirstname())){
            client.setFirstname(firstname);
        }
        if(!surname.equals(client.getSurname()))
            client.setSurname(surname);
        if(!email.equals(client.getEmail()))
            client.setEmail(email);
        clientRepository.save(client);
        return "redirect:/client";
    }




}
