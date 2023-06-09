package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor

public class ApiController {

    private final SurveyRepository surveyRepository;

    @GetMapping("/")
    public String home() {
        return ("index");
    }
    @GetMapping("/login")
    public String login() {

        return ("/login");
    }

    @GetMapping("/sinistres")
    public String accidents() {
        return ("accidents");
    }
    @GetMapping("/sinistre")
    public String accidentProfile() {
        return ("accidentProfile");
    }
    @GetMapping("/annuaire")
    public String phoneBook() {
        return ("phoneBook");
    }
    @GetMapping("/about")
    public String about() {
        return ("about");
    }
    @GetMapping("/nosvaleurs")
    public String values() {
        return ("values");
    }
    @GetMapping("/nostarifs")
    public String prices() {
        return ("prices");
    }
    @GetMapping("/faq")
    public String faq() {
        return ("faq");
    }
    @GetMapping("/mentionslegales")
    public String legalNotice() {
        return ("legalNotice");
    }
    @GetMapping("/contact")
    public String contact() {
        return ("contact");
    }
}

