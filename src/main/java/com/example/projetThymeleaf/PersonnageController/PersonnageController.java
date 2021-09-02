package com.example.projetThymeleaf.PersonnageController;

import com.example.projetThymeleaf.Personnage;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
public class PersonnageController {

    @Autowired
    RestTemplate restTemplate;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/ListePerso")
    public String liste(Model model) {
        List<Personnage> listePerso = restTemplate.getForObject("http://127.0.0.1:8080/Perso", List.class);
        model.addAttribute("ListeDesPerso", listePerso);
        return "index";

    }
    @GetMapping("/PersoDetail/{id}")
    public String persoDetail(Model model, @PathVariable int id) {
        Personnage persoDetail = restTemplate.getForObject("http://127.0.0.1:8080/Perso/" + id , Personnage.class);
        model.addAttribute("PersoDetail", persoDetail);
        return "persoDetail";

    }


    @RequestMapping(value = {"/PersoDelete/{id}"}, method = RequestMethod.POST)
    public String deletePersonnage(@PathVariable int id) {
        restTemplate.delete("http://localhost:8080/Perso/"+id);
        return "redirect:/ListePerso";
    }

    @RequestMapping(value = {"/AjoutPerso"}, method = RequestMethod.GET)
    public String ShowForm(Model model){
        Personnage personnage = new Personnage();
        model.addAttribute("personnage",personnage);
        return "ajoutPerso";
    }

    @RequestMapping(value = {"/AjoutPerso"}, method = RequestMethod.POST)
    public String Ajoutform(Model model, @ModelAttribute Personnage personnage){
        restTemplate.postForObject("http://localhost:8080/Perso", personnage, String.class);
        return "redirect:/ListePerso";
    }
    @RequestMapping(value = "/UpdatePerso", method = RequestMethod.POST)
    public String updatePerso(@ModelAttribute Personnage personnage) {
        restTemplate.put("http://localhost:8080/Perso", personnage);
        return "redirect:/ListePerso";
    }
    @GetMapping("/UpdatePerso/{id}")
    public String updatePerso(Model model, @PathVariable int id) {
        Personnage persoDetail = restTemplate.getForObject("http://127.0.0.1:8080/Perso/" + id , Personnage.class);
        model.addAttribute("updatePerso", persoDetail);
        return "updatePerso";

    }

//    @RequestMapping(value = {"/AjoutPerso"}, method = RequestMethod.POST)
//    public String savePersonnage(Model model,
//                                 @ModelAttribute("personnageForm") Personnage personnageForm) {
//
//        personnageForm.setId(at.getAndIncrement());
//        if(personnageForm.getNom()==null||personnageForm.getNom().isEmpty()){
//
//            model.addAttribute("errorMessage", "il faut le nom du bg");
//            return "AjoutPerso";
//        }


}
