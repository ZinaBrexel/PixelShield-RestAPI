package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Page<Survey> findAll(Pageable pageable);
    Collection<Survey>findByPublishedFalse();
}
