package com.cmc.board.repository;

import com.cmc.board.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.List;

public class CommentDBRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;


    public CommentDBRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Comment addComment(Comment c) {
        String sql = "INSERT INTO hello_comment (posting_id, comment, author) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, c.getPostingId(), c.getComment(), c.getAuthor());

        return c;
    }

    public List<Comment> findCommentByPostingID(int postingId) {
        String sql = "SELECT posting_id, comment, author, dt FROM hello_comment WHERE posting_id = ?";
        List<Comment> result = jdbcTemplate.query(sql, commentRowMapper(), postingId);
        return result;
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setPostingId(rs.getInt("posting_id"));
            comment.setComment(rs.getString("comment"));
            comment.setDt(rs.getString("dt"));
            comment.setAuthor(rs.getString("author"));

            return comment;
        };
    }
}
