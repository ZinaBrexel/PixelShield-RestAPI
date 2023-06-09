package fr.simplon.pixelshielrestapi.service;

import fr.simplon.pixelshielrestapi.entity.Vote;
import fr.simplon.pixelshielrestapi.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    public Collection<Vote> getAllVotes() {
        return voteRepository.findAll();
    }
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

}
