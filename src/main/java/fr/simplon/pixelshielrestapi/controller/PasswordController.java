package fr.simplon.pixelshielrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class PasswordController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public PasswordController(
            PasswordEncoder pPasswordEncoder,
            UserDetailsManager pUserDetailsManager)
    {
        passwordEncoder = pPasswordEncoder;
        userDetailsManager = pUserDetailsManager;
    }

    @GetMapping("/password")
    public String password() {
        return ("updatePassword");
    }

    @PostMapping("/password")
    public String updatePassword(@RequestParam("password") String password,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Principal principal) {
        String username = principal.getName();
        if (!newPassword.equals(confirmPassword)) {
            // Gérer l'erreur lorsque le nouveau mot de passe et la confirmation du mot de passe ne correspondent pas
            return "redirect:/password?error";
        }

        UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            // Gérer l'erreur lorsque le mot de passe actuel est incorrect
            return "redirect:/password?error";
        }

        userDetailsManager.changePassword(password, passwordEncoder.encode(newPassword));

        // Rediriger vers une page de confirmation
        return "redirect:/password?success";
    }
}
