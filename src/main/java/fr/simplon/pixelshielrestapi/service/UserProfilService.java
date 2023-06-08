package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfilService {

    private final UserProfileRepository userProfileRepository;

    public void addUserProfil(UserProfile userProfile){
        userProfileRepository.save(userProfile);
    }

    public Collection<UserProfile> getAllUsersProfiles(){
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> getUserProfileById(String id){
        return userProfileRepository.findById(id);
    }

    public void deleteUserProfil(String id){
        userProfileRepository.deleteById(id);
    }
}
