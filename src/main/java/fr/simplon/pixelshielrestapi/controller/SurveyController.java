package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.repository.VoteRepository;
import fr.simplon.pixelshielrestapi.service.SurveyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class SurveyController {
    @Autowired
    public  SurveyService surveyService;

    @Autowired
    public VoteRepository voteRepository;

    @GetMapping("/sondages")
    public String survey(Model model, @RequestParam(defaultValue = "0") int page, Principal principal) {
        Page<Survey> surveys = surveyService.getAllSurveysPublishedTrue(PageRequest.of(page, 10));

        Map<Long, Boolean> hasVoted = new HashMap<>();
        Map<Long, Boolean> isOpen = new HashMap<>();
        for (Survey survey : surveys) {
            hasVoted.put(survey.getId(), surveyService.hasUserVotedForSurvey(principal.getName(), survey.getId()));
            isOpen.put(survey.getId(), survey.isOpen());
        }

        model.addAttribute("surveys", surveys);
        model.addAttribute("hasVoted", hasVoted);
        model.addAttribute("isOpen", isOpen);

        return ("survey");
    }

    @PostMapping("/createSurvey")
    public String newSurvey(
            @Valid @ModelAttribute Survey survey,
            BindingResult validation

    ){
        if (validation.hasErrors()) {
            // handle validation errors here
            return "redirect:/sondages";

        }

        surveyService.addSurvey(survey);

        return "redirect:/sondages";
    }

}
