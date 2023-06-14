package fr.simplon.pixelshielrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipementController {
    @GetMapping("/mon_materiel")
    public String equipment() {
        return ("equipment");
    }
}
