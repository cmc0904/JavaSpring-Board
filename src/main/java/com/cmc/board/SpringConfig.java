package com.cmc.board;

import com.cmc.board.domain.Posting;
import com.cmc.board.repository.CommentDBRepository;
import com.cmc.board.repository.PostingDBRepository;
import com.cmc.board.repository.UserDataBaseRepository;
import com.cmc.board.repository.UserRepository;
import com.cmc.board.service.CommentService;
import com.cmc.board.service.PostingService;
import com.cmc.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PostingService postingService() {
        return new PostingService(postingDBRepository());
    }

    @Bean
    public PostingDBRepository postingDBRepository() {
        return new PostingDBRepository(dataSource);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserDataBaseRepository(dataSource);
    }

    @Bean
    public CommentService commentService() {
        return new CommentService(commentDBRepository());
    }

    @Bean
    public CommentDBRepository commentDBRepository() {
        return new CommentDBRepository(dataSource);
    }
}
