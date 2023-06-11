package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.dto.UserForm;
import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import fr.simplon.pixelshielrestapi.service.SurveyService;
import fr.simplon.pixelshielrestapi.service.UserProfilService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private UserProfilService userProfilService;

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
    @GetMapping("/employe_details/{username}")
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

    @PostMapping("/edit/employe/{username}")
    @Transactional
    public String editEmploye(@PathVariable String username, @Valid @ModelAttribute(name = "user") UserForm user,
                              BindingResult validation, Model model) {
        if (validation.hasErrors()) {
            // Gérer les erreurs de validation
            return "redirect:/edit_employe/{username}";
        }

        // Vérifier si l'utilisateur existe déjà
        if (!username.equals(user.getLogin()) && userDetailsManager.userExists(user.getLogin())) {
            validation.addError(new ObjectError("user", "Cet utilisateur existe déjà"));
            return "redirect:/edit_employe/{username}";
        }

        // Mettre à jour les informations de l'utilisateur
        UserDetails userDetails = userDetailsManager.loadUserByUsername(username);
        User updatedUser = (User) User.withUserDetails(userDetails)
                .username(user.getLogin())
                .build();
        userDetailsManager.updateUser(updatedUser);

        // Mettre à jour les informations du profil utilisateur
        UserProfile userProfile = userProfilService.findByUsername(username);
        userProfile.setUsername(user.getLogin());
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setAddress(user.getAddress());
        userProfile.setPhone(user.getPhone());
        userProfilService.updateUserProfil(userProfile);

        return "redirect:/employe_details/{username}";
    }

}
