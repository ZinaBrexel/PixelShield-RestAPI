package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.entity.Vote;
import fr.simplon.pixelshielrestapi.repository.VoteRepository;
import fr.simplon.pixelshielrestapi.service.SurveyService;
import fr.simplon.pixelshielrestapi.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class VoteController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private VoteService voteService; // Injectez le service de vote

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping("/sondages/vote/{id}")
    public String voteSurvey(@PathVariable Long id, @RequestParam Boolean value, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        // Récupérer le sondage par id
        Optional<Survey> optionalSurvey = surveyService.getSurveyById(id);

        if(optionalSurvey.isPresent()) {
            Survey survey = optionalSurvey.get();
                Vote vote = new Vote();
                vote.setUsername(currentUserName);
                vote.setValue(value);
                survey.addVote(vote);

                // Enregistrez le vote dans la base de données
                voteService.save(vote); // Assurez-vous d'avoir une méthode save dans votre service de vote

        } else {
            // handle the case when the survey with the provided id does not exist
            return "redirect:/sondages";
        }

        return "redirect:/sondages";
    }

}

