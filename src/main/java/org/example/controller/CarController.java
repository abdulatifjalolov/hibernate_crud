package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.dao.CarDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CarController {
    private final CarDAO carDAO;

    @PostMapping("/cars")
    public String getCarList(HttpServletRequest request) {
        request.setAttribute("carList", carDAO.getList());
        return "admin/car";
    }

    @GetMapping("/user/cars")
    public String getButtonsValue(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        request.setAttribute("carList", carDAO.getList()
                .stream()
                .filter(car -> car.getId() == userId)
                .toList());
        return "admin/car";
    }
}
