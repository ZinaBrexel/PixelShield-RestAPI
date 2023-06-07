package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Authority;
import fr.simplon.pixelshielrestapi.entity.User;
import fr.simplon.pixelshielrestapi.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    public void addAuthority(Authority authority) { authorityRepository.save(authority);}

    public Collection<Authority> getAllAuthorities(){
        return authorityRepository.findAll();
    }

    public Optional<Authority> getAuthorityById(Long id) {
        return authorityRepository.findById(id);
    }

    public void deleteAuthority(Long id){
        authorityRepository.deleteById(id);
    }
}
