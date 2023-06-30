package com.cmc.board.repository;


import com.cmc.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User u);
    Optional<User> findById(String id);
    Optional<User> findByName(String name);
    List<User> findAll();
}
