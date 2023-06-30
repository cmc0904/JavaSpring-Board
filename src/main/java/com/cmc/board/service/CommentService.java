package com.cmc.board.service;

import com.cmc.board.domain.Comment;
import com.cmc.board.domain.Posting;
import com.cmc.board.repository.CommentDBRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentService {
    private final CommentDBRepository commentDBRepository;

    public CommentService(CommentDBRepository commentDBRepository) {
        this.commentDBRepository = commentDBRepository;
    }

    public Comment addComment(Comment comment) {
        commentDBRepository.addComment(comment);

        return comment;
    }

    public List<Comment> getAllPostingById(int postingId) {
        List<Comment> result = commentDBRepository.findCommentByPostingID(postingId);
        return result;
    }
}
