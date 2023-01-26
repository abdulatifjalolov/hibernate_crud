package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.dao.UserDAO;
import org.example.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {

    private final UserDAO userDAO;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register/signUp")
    public String register(
            @ModelAttribute UserEntity user
    ) {
        boolean isSuccess = true;
        try{
            userDAO.add(user);
        }catch (Exception e){
            isSuccess = false;
        }

        return isSuccess ? "login" : "register";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest req){
        req.getSession().setAttribute("userId",null);
        req.getSession().setMaxInactiveInterval(0);
        return "index";
    }
}
