package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
