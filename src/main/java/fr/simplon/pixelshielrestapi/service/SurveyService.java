package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;

    public void addSurvey(Survey survey){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        survey.setUsername(currentUserName);
        surveyRepository.save(survey);
    }
    public Page<Survey> getAllSurveys(Pageable pageable){
        return surveyRepository.findAll(pageable);
    }

    public Optional<Survey> getSurveyById(Long id){
        return surveyRepository.findById(id);
    }
    public void updateSurvey(Survey survey){
        surveyRepository.save(survey);
    }
    public void deleteSurvey(Long id){
            surveyRepository.deleteById(id);
    }

}
