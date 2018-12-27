package com.tnicy.demo.Controller;

import com.tnicy.demo.Entity.User;
import com.tnicy.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;


    @PostMapping("/register")
    public String register(User user,HttpSession session){
        if(userRepository.existsByTelephone(user.getTelephone())){
            return "redirect:/register";
        }
        else {
            userRepository.save(user);
            User GotUser = userRepository.findByTelephone(user.getTelephone());
            session.setAttribute("user",GotUser);
            session.setAttribute("Admin",GotUser.getAdmin());
            session.setAttribute("uid", GotUser.getUid());
        }



        return "redirect:/book";
    }


    @PostMapping("/login")
    public String login(User user, Model model,HttpSession session){
        User GotUser=userRepository.findByUsername(user.getUsername());

        if(GotUser!=null&&GotUser.getPassword().equals(user.getPassword())){
            session.setAttribute("uid",GotUser.getUid());
            session.setAttribute("Admin",GotUser.getAdmin());
            session.setAttribute("user",GotUser);
            return "redirect:/";
        }
        else {
            model.addAttribute("alert","<script> $(\"#no_user\").modal(); </script>");
            return "login";
        }
    }


    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(HttpSession session){
        if (session.getAttribute("uid") != null) {
            if (session.getAttribute("Admin").equals("1")) {
                return "redirect:/introduction";
            }
            else {
                return "redirect:/introduction";
            }
        }

        return "login";
    }


    @GetMapping("/quit")
    public String quitAccount(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
