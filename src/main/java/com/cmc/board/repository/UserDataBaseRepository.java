package com.cmc.board.repository;




import com.cmc.board.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


//@Repository
public class UserDataBaseRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;


    public UserDataBaseRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User u) {
        String sql = "INSERT INTO hello_users (username, password, nickName) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, u.getId(), u.getPassword(), u.getNickName());

        return u;
    }

    @Override
    public Optional<User> findById(String id) {
        List<User> result = jdbcTemplate.query("SELECT * FROM hello_users WHERE username = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByName(String nickName) {
        List<User> result = jdbcTemplate.query("SELECT * FROM hello_users WHERE nickname = ?", userRowMapper(), nickName);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        List<User> result = jdbcTemplate.query("SELECT * FROM hello_users", userRowMapper());
        return result;
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserCode(rs.getInt("user_id"));
            user.setId(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setNickName(rs.getString("nickname"));

            return user;
        };
    }
}
