package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.dao.CarDAO;
import org.example.dao.UserDAO;
import org.example.entity.CarEntity;
import org.example.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarDAO carDAO;
    private final UserDAO userDAO;

    @PostMapping("/delete/{id}")
    public String getCarList(HttpServletRequest request,
                             @PathVariable("id") int id) {
        request.setAttribute("carList", carDAO.getList());
        return "admin/car";
    }
    @GetMapping("/start")
    public String start(Model model, HttpServletRequest httpServletRequest){
//
        model.addAttribute("carDAO",carDAO);
        return "web/index";
    }
@PostMapping("/add")
public String addCar(
        HttpServletRequest httpServletRequest,
        Model model,
        @ModelAttribute CarEntity carEntity
        ){
    Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
    UserEntity userEntity = userDAO.getById(1);
    carEntity.setUserEntity(userEntity);
    carDAO.add(carEntity);
    model.addAttribute("carDAO", carDAO);
    return "web/index";
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
