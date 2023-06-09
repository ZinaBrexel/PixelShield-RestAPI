package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    boolean existsByUsernameAndSurveyId(String username, Long surveyId);
}
