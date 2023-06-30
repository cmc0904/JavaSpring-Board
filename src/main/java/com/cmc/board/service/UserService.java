package com.cmc.board.service;

import com.cmc.board.domain.User;
import com.cmc.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user) {
//        validateDuplicateMember(user);
        userRepository.save(user);
        return user;
    }

    private void validateDuplicateMember(User user) {
        userRepository.findByName(user.getId()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String userId) {
        return userRepository.findById(userId);
    }

}




