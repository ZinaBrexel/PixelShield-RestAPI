package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Equipement;
import fr.simplon.pixelshielrestapi.repository.EquipementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EquipementService {
    private final EquipementRepository equipementRepository;

    public void addEquipement(Equipement equipement) {
        equipementRepository.save(equipement);
    }
    public Collection<Equipement> getAccidentsByUser(String username) {
        return equipementRepository.findByUsername(username);
    }
}
