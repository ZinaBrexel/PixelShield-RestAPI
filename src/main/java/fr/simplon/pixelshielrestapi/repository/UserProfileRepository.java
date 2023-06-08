package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
}
