package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Users;
import fr.simplon.pixelshielrestapi.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private  final UsersRepository usersRepository;

    public void addUser(Users user){usersRepository}
}
