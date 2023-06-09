package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import fr.simplon.pixelshielrestapi.service.SurveyService;
import fr.simplon.pixelshielrestapi.service.UserProfilService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserProfileRepository userProfileRepository;

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


}
