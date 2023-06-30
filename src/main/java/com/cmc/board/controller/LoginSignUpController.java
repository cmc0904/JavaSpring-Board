package com.cmc.board.controller;

import com.cmc.board.domain.User;
import com.cmc.board.form.UserForm;
import com.cmc.board.service.PostingService;
import com.cmc.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import java.net.http.HttpHeaders;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class LoginSignUpController {


    private final UserService userService;

    @Autowired
    public LoginSignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String page() {
        return "login/index";
    }

    @ResponseBody
    @PostMapping("/board/login")
    public String login(UserForm form, HttpSession session) {

        Optional<User> user = userService.findOne(form.getId());

        String message = "<script>location.href='/boardP';</script>";

        if(user.isEmpty()) { // 존재하지 않은 아이디
            message = "<script>alert('존재하지 않는 아이디 입니다.');location.href='/';</script>";
            return message;
        }

        if (!(user.get().getPassword().equals(form.getPassword()))) { // 비밀번호 틀림
            message = "<script>alert('비밀번호가 틀렸습니다.');location.href='/';</script>";
            return message;
        }

        session.setAttribute("poster", user.get());
        return message;
    }


    @GetMapping("/board/register")
    public String registerPage() {
        return "login/register";
    }

    @PostMapping("/board/register/new")
    public String register(UserForm form) {
        User user = new User();

        user.setNickName(form.getNickName());
        user.setId(form.getId());
        user.setPassword(form.getPassword());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/boardP/logout")
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
