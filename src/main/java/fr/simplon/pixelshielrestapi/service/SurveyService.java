package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public void addSurvey(Survey survey){
        surveyRepository.save(survey);
    }

    public Collection<Survey> getAllSurveys(){
        return surveyRepository.findAll();
    }

    public Optional<Survey> getSurveyById(Long id){
        return surveyRepository.findById(id);
    }

    public void deleteSurvey(Long id){
            surveyRepository.deleteById(id);
    }
}
