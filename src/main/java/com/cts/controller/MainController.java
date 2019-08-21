package com.cts.controller;

import com.cts.config.CustomAuthenticationProvider;
import com.cts.model.Login;
import com.cts.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    @Autowired
    DataSource dataSource;
    @Autowired
    private HttpSessionSecurityContextRepository httpSessionSecurityContextRepository;
    @Autowired
    private CustomAuthenticationProvider authenticationManager;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        LOGGER.debug("Default Page");
        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        LOGGER.debug("Default Page End");
        return model;

    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) throws SQLException {
        LOGGER.debug("In Login method");
        ModelAndView model = new ModelAndView("login");
        model.addObject("login", new Login());
        if (error != null) {
            LOGGER.error("Invalid login");
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            LOGGER.error("Invalid login");
            model.addObject("msg", "You've been logged out successfully.");
        }
        return model;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout, HttpSession session) throws SQLException {

        ModelAndView model = new ModelAndView("/homePage");
       //model.addObject("login", new Login());
        HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);
        httpSessionSecurityContextRepository.loadContext(holder);
         if(session.getAttribute("CAPTCHA").equals(request.getParameter("captcha"))){
            try {

                Authentication auth = new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"), new ArrayList<>());
                auth = authenticationManager.authenticate(auth);
                SecurityContextHolder.getContext().setAuthentication(auth);
                if(auth!=null){
                    User user = getUser((String) auth.getPrincipal());
                    session.setAttribute("USER",user);
                }
                if (auth == null || !auth.isAuthenticated())
                    throw new CredentialException("exception");
            } catch (Exception ex) {

                model.addObject("error", "Invalid username and password!");
                model.setViewName("redirect:/login?error");
  //              return model2;
            }
        }else{

             model.addObject("msg", "Invalid Captcha");
             model.setViewName("redirect:/login?error");
            //return model2;
        }

       return model;
    }

    private User getUser(String name) throws SQLException {
        User user = new User();
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement("select * from USER where USERNAME= ?");
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                user.setName(results.getString("NAME"));
                user.setEmail(results.getString("EMAIL"));
                user.setUsername(results.getString("USERNAME"));
                user.setPassword(results.getString("PASSWORD"));
            }
        }catch (Exception e)
        {
            LOGGER.error(e);
        }
        return user;
    }

    @RequestMapping(value = { "/homePage"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("homePage");
        return model;
    }

    @RequestMapping(value = {"/userPage"}, method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout, HttpSession session) {
        ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute("USER");
        model.addObject("user",user);
        model.setViewName("userPage");
        return model;
    }



    @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminPage");
        return model;
    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

}