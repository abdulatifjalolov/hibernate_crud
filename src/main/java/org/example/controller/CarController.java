package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.dao.CarDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CarController {
    private final CarDAO carDAO;

    @GetMapping("/cars")
    public String getCarList(Model model) {
        model.addAttribute("carList", carDAO.getList());
        return "admin/car";
    }

    @GetMapping("/user/cars")
    public String getButtonsValue(Model model, HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        model.addAttribute("carList", carDAO.getList()
                .stream()
                .filter(car -> car.getId() == userId)
                .toList());
        return "admin/car";
    }
}
