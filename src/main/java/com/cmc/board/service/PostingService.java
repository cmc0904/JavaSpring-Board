package com.cmc.board.service;

import com.cmc.board.domain.Posting;
import com.cmc.board.repository.PostingDBRepository;

import java.util.List;
import java.util.Optional;

public class PostingService {

    private final PostingDBRepository postingDBRepository;

//    @Autowired
    public PostingService(PostingDBRepository postingDBRepository) {
        this.postingDBRepository = postingDBRepository;
    }

    public Posting upload(Posting posting) {
        postingDBRepository.post(posting);

        return posting;
    }

    public List<Posting> getAllPosting() {
        List<Posting> result = postingDBRepository.findAllPosting();
        return result;
    }

    public Posting findPostingById(int id) {
        Posting result = postingDBRepository.findPostingById(id);
        return result;
    }

}
