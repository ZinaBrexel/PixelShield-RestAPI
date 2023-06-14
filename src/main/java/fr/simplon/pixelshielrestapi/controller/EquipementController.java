package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Accident;
import fr.simplon.pixelshielrestapi.entity.Equipement;
import fr.simplon.pixelshielrestapi.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class EquipementController {

    @Autowired
    public EquipementService equipementService;


    @GetMapping("/mon_materiel")
    public String newEquipment(Principal principal, Model model) {
        String currentUsername = principal.getName();
        Collection<Equipement> equipements = equipementService.getAccidentsByUser(currentUsername);
        model.addAttribute("equipements", equipements);
        return ("equipment");
    }

    @PostMapping("/mon_materiel")
    public String newEquipement(@ModelAttribute Equipement equipement) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        equipement.setUsername(currentUserName);
        equipement.setFile(equipement.getFile());
        equipement.setEquipment(equipement.getEquipment());
        equipementService.addEquipement(equipement);

        return "redirect:/mon_materiel";
    }
}
