package com.example.projetThymeleaf.PersonnageController;

import com.example.projetThymeleaf.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class PersonnageController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ListePerso")
    public String liste(Model model) {
        List<Personnage> listePerso = restTemplate.getForObject("http://127.0.0.1:8080/Perso", List.class);
        model.addAttribute("ListeDesPerso", listePerso);
        return "index";

    }


}
