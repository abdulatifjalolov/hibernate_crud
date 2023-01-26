package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.dao.UserDAO;
import org.example.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final UserDAO userDAO;
    private final SessionFactory sessionFactory;

    @PostMapping("/login")
    public String login(
            HttpServletRequest request,
            Model model
    ) {
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from UserEntity u where u.phone_number=:phone_number and u.password=:password");
        query.setParameter("phone_number", phoneNumber);
        query.setParameter("password", password);
        UserEntity currentUser = (UserEntity) query.uniqueResult();
        tx.commit();
        session.close();
        if (currentUser!=null){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("userId",currentUser.getId());
            if (currentUser.getRole().name().equals("USER")){
                return "user";
            } else if (currentUser.getRole().name().equals("ADMIN")) {
                model.addAttribute("text", "WELCOME TO ADMIN PAGE");
                model.addAttribute("userList",userDAO.getList()
                        .stream()
                        .filter(user -> user.getRole().name().equals("USER"))
                        .toList());
                return "home";
            }
        }
        return "index";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register/signUp")
    public String register(
            @ModelAttribute UserEntity user,
            HttpServletResponse response
    ) {
        boolean isSuccess = true;
        try{
            userDAO.add(user);
        }catch (Exception e){
            isSuccess = false;
        }
        return isSuccess ? "index" : "register";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest req){
        req.getSession().setAttribute("userId",null);
        req.getSession().setMaxInactiveInterval(0);
        return "index";
    }
}
