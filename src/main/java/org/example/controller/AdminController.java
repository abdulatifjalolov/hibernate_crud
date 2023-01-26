package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.dao.CarDAO;
import org.example.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserDAO userDAO;
    private final CarDAO carDAO;

    @GetMapping("/user/list")
    public String getCategoryList(
            HttpServletRequest request
    ) {
        request.setAttribute("userList", userDAO.getList());
        return "index";
    }
}