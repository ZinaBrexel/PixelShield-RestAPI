package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import fr.simplon.pixelshielrestapi.service.UserProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class DashboardController {
    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private UserProfilService userProfilService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        Collection<UserProfile> userProfiles = userProfileRepository.findTop10ByRoleUser();
        model.addAttribute("userProfiles", userProfiles);
        Collection<UserProfile> managerProfiles =userProfileRepository.findTop10ByRoleManager();
        model.addAttribute("managerProfiles", managerProfiles);
        return ("dashboard");
    }

}
