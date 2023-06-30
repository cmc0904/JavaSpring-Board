package com.cmc.board.repository;


import com.cmc.board.domain.Posting;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PostingDBRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostingDBRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Posting post(Posting p) {
        System.out.println(p.getTitle());
        String sql = "INSERT INTO hello_posting (title, author, mainText) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, p.getTitle(), p.getAuthor(), p.getMainText());

        return p;
    }

    public List<Posting> findAllPosting() {
        List<Posting> result = jdbcTemplate.query("SELECT posting_id, title, author, dt FROM hello_posting;", allPostingRowMapper());
        return result;
    }

    public Posting findPostingById(int id) {
        List<Posting> result = jdbcTemplate.query("SELECT * FROM hello_posting WHERE posting_id = ?", postingRowMapper(), id);
        return result.get(0);
    }

    private RowMapper<Posting> postingRowMapper() {
        return (rs, rowNum) -> {
            Posting posting = new Posting();
            posting.setMainText(rs.getString("mainText"));
            posting.setTitle(rs.getString("title"));
            posting.setAuthor(rs.getString("author"));
            posting.setId(rs.getInt("posting_id"));
            posting.setDt(rs.getString("dt"));
            return posting;
        };
    }

    private RowMapper<Posting> allPostingRowMapper() {
        return (rs, rowNum) -> {
            Posting posting = new Posting();
            posting.setTitle(rs.getString("title"));
            posting.setAuthor(rs.getString("author"));
            posting.setId(rs.getInt("posting_id"));
            posting.setDt(rs.getString("dt"));
            return posting;
        };
    }
}
