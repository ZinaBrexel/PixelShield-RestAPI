package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.UserProfile;
import fr.simplon.pixelshielrestapi.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfilService {

    private final UserProfileRepository userProfileRepository;

    public void addUserProfil(UserProfile userProfile){
        userProfileRepository.save(userProfile);
    }
    public UserProfile findByUsername(String username) {
        return userProfileRepository.findById(username).orElse(null);
    }

    public UserProfile updateUserProfil(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}
