package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.dao.CarDAO;
import org.example.dao.UserDAO;
import org.example.entity.CarEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {
    private final UserDAO userDAO;
    private final CarDAO carDAO;
    @GetMapping("")
    public String start(Model model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        try {
            httpServletResponse.sendRedirect("car/start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
