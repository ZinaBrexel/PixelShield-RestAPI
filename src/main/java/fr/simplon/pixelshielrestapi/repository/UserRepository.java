package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
}
