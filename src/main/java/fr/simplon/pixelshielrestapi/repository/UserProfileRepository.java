package fr.simplon.pixelshielrestapi.repository;

import fr.simplon.pixelshielrestapi.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    @Query(value = "SELECT * FROM user_profiles u WHERE u.username IN " +
            "(SELECT a.username FROM authorities a WHERE a.authority = 'ROLE_USER') " +
            "ORDER BY u.created_at DESC LIMIT 10", nativeQuery = true)
    Collection<UserProfile> findTop10ByRoleUser();

    @Query(value = "SELECT * FROM user_profiles u WHERE u.username IN " +
            "(SELECT a.username FROM authorities a WHERE a.authority = 'ROLE_MANAGER') " +
            "ORDER BY u.created_at DESC LIMIT 10", nativeQuery = true)
    Collection<UserProfile> findTop10ByRoleManager();
    UserProfile findByUsername(String username);

}