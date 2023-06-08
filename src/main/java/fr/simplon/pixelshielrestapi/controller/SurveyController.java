package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.service.SurveyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class SurveyController {
    @Autowired
    public  SurveyService surveyService;

    @GetMapping("/sondages")
    public String survey(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Survey> surveys = surveyService.getAllSurveys(PageRequest.of(page, 10));
        model.addAttribute("surveys", surveys);

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
