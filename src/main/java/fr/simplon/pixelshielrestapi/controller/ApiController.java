package fr.simplon.pixelshielrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor

public class ApiController {

    @GetMapping("/")
    public String home() {
        return ("index");
    }
    @GetMapping("/login")
    public String login() {

        return ("/login");
    }
    @PostMapping()
    @GetMapping("/sondages")
    public String survey() {
        return ("survey");
    }
    @GetMapping("/souscription")
    public String subscribe() {
        return ("subscribe");
    }
    @GetMapping("/password")
    public String password() {
        return ("updatePassword");
    }
    @GetMapping("/profil")
    public String profile() {
        return ("profile");
    }
    @GetMapping("/profil_edit")
    public String profileEdit() {
        return ("profileEdit");
    }
    @GetMapping("/materiel")
    public String equipment() {
        return ("equipment");
    }
    @GetMapping("/mes_sinistres")
    public String accident() {
        return ("accident");
    }
    @GetMapping("/sinistres")
    public String accidents() {
        return ("accidents");
    }
    @GetMapping("/sinistre")
    public String accidentProfile() {
        return ("accidentProfile");
    }
    @GetMapping("/dashboard")
    public String dashboardAdmin() {
        return ("dashboard");
    }
    @GetMapping("/clients")
    public String customersAdmin() {
        return ("customers");
    }
    @GetMapping("/employes")
    public String employeesAdmin() {
        return ("employees");
    }
    @GetMapping("/employe_details")
    public String employeDetailsAdmin() {
        return ("employe_details");
    }
    @GetMapping("/employe_edit")
    public String employeEditAdmin() {
        return ("edit_employe");
    }
    @GetMapping("/client_details")
    public String customerDetailsAdmin() {
        return ("customer_details");
    }
    @GetMapping("/client_edit")
    public String clientEditAdmin() {
        return ("customerEdit");
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
