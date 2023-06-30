package com.cmc.board.controller;

import com.cmc.board.domain.Comment;
import com.cmc.board.domain.Posting;
import com.cmc.board.domain.User;
import com.cmc.board.form.PostingForm;
import com.cmc.board.service.CommentService;
import com.cmc.board.service.PostingService;
import com.cmc.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostingController {

    private final PostingService postingService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public PostingController(PostingService postingService, UserService userService, CommentService commentService) {
        this.postingService = postingService;
        this.userService = userService;
        this.commentService = commentService;
    }


    @GetMapping("/boardP")
    public String mainPage(Model model, HttpSession session) {
        List<Posting> result = postingService.getAllPosting();

        model.addAttribute("postings", result);

        User loginedInfo = (User) session.getAttribute("poster");
        if (loginedInfo == null) {
            return "redirect:/";
        }
        return "board/index";
    }

    @PostMapping("/boardP/new")
    public String post(PostingForm form, HttpSession session) {

        Posting posting = new Posting();

        User loginedInfo = (User) session.getAttribute("poster");
        System.out.println(loginedInfo.getNickName());
        System.out.println("ass " + form.getTitle());
        posting.setTitle(form.getTitle());
        posting.setAuthor(loginedInfo.getNickName());
        posting.setMainText(form.getMainText());
        postingService.upload(posting);
        return "redirect:/boardP";
    }

    @GetMapping("/boardP/postingDetail")
    public String postingDetail(Model model, @RequestParam(value="id") String id, HttpSession session) {
        session.setAttribute("posting_id", Integer.valueOf(id));
        Posting posting = postingService.findPostingById(Integer.valueOf(id));
        List<Comment> allPosting = commentService.getAllPostingById(Integer.valueOf(id));
        model.addAttribute("post", posting);
        model.addAttribute("comment", allPosting);


        return "board/detail";
    }

    @GetMapping("/boardP/postingDetail/re")
    public String redirectToBoard() {
        return "redirect:/boardP";
    }

}
