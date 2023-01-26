package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.dao.CarDAO;
import org.example.dao.UserDAO;
import org.example.entity.CarEntity;
import org.example.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserDAO userDAO;
    private final CarDAO carDAO;

    @GetMapping("/user/list")
    public String getCategoryList(
            Model model,
            HttpServletRequest request
    ) {
        if (request.getParameter("userId")!=null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            List<UserEntity> user = userDAO.getList()
                    .stream()
                    .filter(currentUser -> currentUser.getId() == userId)
                    .filter(currentUser -> currentUser.getRole().name().equals("USER"))
                    .toList();
            model.addAttribute("userList",user);
        }else {
            model.addAttribute("userList", userDAO.getList());
        }
            return "home";
    }

    @GetMapping("/user/cars")
    public String getUserCars(
            Model model,
            HttpServletRequest request
    ) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<CarEntity> cars = carDAO
                .getList()
                .stream()
                .parallel()
                .filter(car -> car.getId() == userId)
                .toList();
        model.addAttribute("carList", cars);
        return "cars";
    }

    @GetMapping("/cars")
    public String getCars(
            Model model
    ) {
        model.addAttribute("carList", carDAO.getList());
        return "cars";
    }
}