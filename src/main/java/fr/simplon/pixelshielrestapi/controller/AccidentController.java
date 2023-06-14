package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Accident;
import fr.simplon.pixelshielrestapi.service.AccidentService;
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
public class AccidentController {

    @Autowired
    public AccidentService accidentService;
    @GetMapping("/mes_sinistres")
    public String accident(Principal principal, Model model) {
        String currentUsername = principal.getName();
        Collection<Accident> accidents = accidentService.getAccidentsByUser(currentUsername);
        model.addAttribute("accidents", accidents);
        return "accident";
    }
    @PostMapping("/mes_sinistres")
    public String newAccident(@ModelAttribute Accident accident) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        accident.setUsername(currentUserName);
        accident.setFile(accident.getFile());
        accident.setType(accident.getType());
        accident.setDescription(accident.getDescription());
        accident.setPending(true);
        accident.setAccepted(false);
        accidentService.addAccident(accident);

        return "redirect:/mes_sinistres";
    }

}
