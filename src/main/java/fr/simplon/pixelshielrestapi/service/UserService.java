package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.User;
import fr.simplon.pixelshielrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private  final UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public Collection<User>getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}
