package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
