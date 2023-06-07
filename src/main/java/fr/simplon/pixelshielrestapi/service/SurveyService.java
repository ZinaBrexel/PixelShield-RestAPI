package fr.simplon.pixelshielrestapi.Dao;

import fr.simplon.pixelshielrestapi.entity.Survey;

import java.util.Collection;

public class SurveyDao {
    void save(Survey survey);

    void update (Survey survey);

    void delete (Long id);
    Collection<Survey> findAll();

    Survey findById(Long id);
}
