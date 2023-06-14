package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import fr.simplon.pixelshielrestapi.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    public DashboardController(

            UserDetailsManager pUserDetailsManager)
    {
        userDetailsManager = pUserDetailsManager;
    }

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyRepository surveyRepository;


    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        Collection<UserProfile> userProfiles = userProfileRepository.findTop10ByRoleUser();
        model.addAttribute("userProfiles", userProfiles);
        Collection<UserProfile> managerProfiles = userProfileRepository.findTop10ByRoleManager();
        model.addAttribute("managerProfiles", managerProfiles);
        Collection<Survey> unpublishedSurveys = surveyRepository.findByPublishedFalse().stream()
                .filter(survey -> !survey.isClosed())
                .collect(Collectors.toList());
        model.addAttribute("surveys", unpublishedSurveys);
        return ("dashboard");
    }

    @PostMapping("/dashboard/moderation/{id}")
    public String publishSurvey(@PathVariable Long id, @RequestParam Boolean published){
        // Récupérer le sondage par id
        Optional<Survey> optionalSurvey = surveyService.getSurveyById(id);

        if(optionalSurvey.isPresent()) {
            // Si le sondage existe, le marquer comme publié ou le supprimer, selon la valeur de "published"
            Survey surveyToPublish = optionalSurvey.get();
            if(published){
                surveyToPublish.setPublished(true);
                // Enregistrer le sondage mis à jour
                surveyService.updateSurvey(surveyToPublish);
            } else {
                surveyService.deleteSurvey(id);
            }
        } else {
            // handle the case when the survey with the provided id does not exist
            return "redirect:/dashboard";
        }

        return "redirect:/dashboard";
    }
    @GetMapping("/employe/details/{username}")
    public String getEmployeDetails(@PathVariable String username, Model model) {

        UserProfile userProfile = userProfileRepository.findByUsername(username);
        model.addAttribute("userProfile", userProfile);

        return "employe_details";
    }
    @GetMapping("/edit/employe/{username}")
    public String getEmployeEdit(@PathVariable String username, Model model) {

        UserProfile userProfile = userProfileRepository.findByUsername(username);
        model.addAttribute("userProfile", userProfile);

        return "edit_employe";
    }
    @GetMapping("/client/details/{username}")
    public String getCustomerDetails(@PathVariable String username, Model model) {

        UserProfile userProfile = userProfileRepository.findByUsername(username);
        model.addAttribute("userProfile", userProfile);

        return "customer_details";
    }
    @GetMapping("/edit/client/{username}")
    public String getCustomerEdit(@PathVariable String username, Model model) {

        UserProfile userProfile = userProfileRepository.findByUsername(username);
        model.addAttribute("userProfile", userProfile);

        return "customerEdit";
    }

    @PostMapping("/edit/employe/{username}")
    public String editEmploye(@PathVariable String username,
                              @ModelAttribute UserProfile updatedProfile,
                              @RequestParam(value = "enabled", required = false) Boolean enabled) {
        // Récupérer les détails de l'employé à éditer en fonction de son username
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        // Mettre à jour les informations de l'employé avec les données soumises dans le formulaire
        userProfile.setFirstName(updatedProfile.getFirstName());
        userProfile.setLastName(updatedProfile.getLastName());
        userProfile.setAddress(updatedProfile.getAddress());
        userProfile.setPhone(updatedProfile.getPhone());

        // Enregistrer les modifications dans la base de données
        userProfileRepository.save(userProfile);

        // Mettre à jour l'état d'activation du compte utilisateur avec UserDetailsManager
        UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
        if (userDetails != null) {
            User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUserDetails(userDetails);
            User.UserBuilder updatedUserBuilder = builder.username(username);
            if (enabled != null && !enabled) {
                updatedUserBuilder.disabled(true);
            } else {
                updatedUserBuilder.disabled(false);
            }
            User updatedUser = (User) updatedUserBuilder.build();
            userDetailsManager.updateUser(updatedUser);
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return "redirect:/employe/details/{username}";
    }
    @PostMapping("/edit/client/{username}")
    public String editCustomer(@PathVariable String username,
                              @ModelAttribute UserProfile updatedProfile) {
        // Récupérer les détails du client à éditer en fonction de son username
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        // Mettre à jour les informations du client avec les données soumises dans le formulaire
        userProfile.setFirstName(updatedProfile.getFirstName());
        userProfile.setLastName(updatedProfile.getLastName());
        userProfile.setAddress(updatedProfile.getAddress());
        userProfile.setPhone(updatedProfile.getPhone());

        // Enregistrer les modifications dans la base de données
        userProfileRepository.save(userProfile);

        return "redirect:/client/details/{username}";
    }

}