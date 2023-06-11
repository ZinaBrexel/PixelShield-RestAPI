package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import fr.simplon.pixelshielrestapi.repository.VoteRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Optional;
@Transactional
@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;

    private final VoteRepository voteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void addSurvey(Survey survey){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        survey.setUsername(currentUserName);
        surveyRepository.save(survey);
    }
    public Page<Survey> getAllSurveys(Pageable pageable){
        return surveyRepository.findAll(pageable);
    }
    public Page<Survey> getAllSurveysPublishedTrue(Pageable pageable){
        return surveyRepository.findByPublishedTrue(pageable);
    }
    public Optional<Survey> getSurveyById(Long id){
        return surveyRepository.findById(id);
    }


    public Survey updateSurvey(Survey survey){
        return entityManager.merge(survey);

    }
    public void deleteSurvey(Long id){
        surveyRepository.deleteById(id);
    }

    public boolean hasUserVotedForSurvey(String username, Long surveyId) {
        return voteRepository.existsByUsernameAndSurveyId(username, surveyId);
    }

}
