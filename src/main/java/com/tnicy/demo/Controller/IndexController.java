package com.tnicy.demo.Controller;

import com.tnicy.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/")
    public String getIndex(HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/login";
        } else {
            return "redirect:/introduction";
        }
    }

}
