package com.fakeghost.bbs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller
@RequestMapping("/LoginServlet")
public class LoginCtrl {

    @Autowired
    @Qualifier("userMapper")
      UserMapper userMapper;

    @RequestMapping(value="/", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        /*
        String kaptchaReceived = request.getParameter("verification");
        String kaptchaExpected = (String)request.getSession().getAttribute("kcode");
        if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)){
            request.setAttribute("failMsg","kaptcha code fault, Why not try again.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        */

        User user = userMapper.user(username, password);
        if(null !=user && user.nickname.equals(username)){
            String remember = request.getParameter("remember");
            if(null != remember && remember.equals("true")){
                Cookie cookie = new Cookie("username",username);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            }
            request.getSession().setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/main.jsp");
            return;
        }
        request.setAttribute("failMsg","wrong Username or password.");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    @RequestMapping(value="/", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("username", "No login Access");
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}

