package com.cts.controller;

import com.cts.model.Login;
import com.cts.model.User;
import com.cts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {
    @Autowired
    public UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }
    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
                                @ModelAttribute("user") User user, Model model, HttpSession session) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", user);
        if (user.getName() == null || user.getName().equals("")) {
            user.setCaptcha("");
            user.setPassword("");
            model.addAttribute("message", "Name is required");
            return mav;
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            user.setCaptcha("");
            user.setPassword("");
            model.addAttribute("message", "Email is required");
            return mav;
        }
        if (user.getUsername() == null || user.getUsername().equals("")) {
            user.setCaptcha("");
            user.setPassword("");
            model.addAttribute("message", "UserName is required");
            return mav;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            user.setCaptcha("");
            user.setPassword("");
            model.addAttribute("message", "Password is required");
            return mav;
        }

        String captcha = (String) session.getAttribute("CAPTCHA");
        System.out.print(captcha);
        if (captcha == null || (captcha != null && !captcha.equals(user.getCaptcha()))) {
            user.setCaptcha("");
            model.addAttribute("message", "Captcha does not match");
            return mav;
        }
        userService.register(user);
        ModelAndView success = new ModelAndView("login");
        success.addObject("login", new Login());
        return success;

    }

    @RequestMapping(value = {"/updateAccount"}, method = RequestMethod.POST)
    public ModelAndView updateAccount(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "logout", required = false) String logout, HttpSession session, @ModelAttribute("user") User user) {
        ModelAndView model = new ModelAndView();
        User newresponse = userService.updateUser(user);

        model.setViewName("homePage");
        if(newresponse!=null) {
            model.addObject("msg", "Successfully updated the User account");
        }else
        {
            model.addObject("error", "Unable to update the User Retry");
        }
        return model;
    }

}

