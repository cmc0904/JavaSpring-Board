package com.cmc.board.controller;

import com.cmc.board.domain.Comment;
import com.cmc.board.domain.User;
import com.cmc.board.form.CommentForm;
import com.cmc.board.form.UserForm;
import com.cmc.board.service.CommentService;
import com.cmc.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/board/postingDetail/comment/new")
    public String register(CommentForm form, HttpSession session) {
        Comment comment = new Comment();
        System.out.println("Easdsa " + form.getComment());
        comment.setAuthor(form.getAuthor());
        comment.setComment(form.getComment());
        User loginedInfo = (User) session.getAttribute("poster");
        comment.setAuthor(loginedInfo.getNickName());
        comment.setPostingId((Integer) session.getAttribute("posting_id"));

        commentService.addComment(comment);
        return "redirect:/boardP/postingDetail?id="+session.getAttribute("posting_id");
    }

}
