package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {


    Collection<Equipement> findByUsername(String username);

}
