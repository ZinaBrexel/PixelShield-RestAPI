package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
