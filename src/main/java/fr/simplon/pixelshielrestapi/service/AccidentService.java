package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Accident;
import fr.simplon.pixelshielrestapi.repository.AccidentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    public void addAccident(Accident accident) {
       accidentRepository.save(accident);
    }
    public Collection<Accident> getAccidentsByUser(String username) {
        return accidentRepository.findByUsername(username);
    }
}
