package com.tnicy.demo.Controller;

import com.tnicy.demo.Entity.Site;
import com.tnicy.demo.Repository.SiteRepository;
import com.tnicy.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class IntroController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SiteRepository siteRepository;

    @GetMapping("/introduction")
    public String getIntro(HttpSession session, Model model) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }else {
            ArrayList<Site> sites = siteRepository.findAll();//拿出所有场馆
            model.addAttribute("sites", sites);
            model.addAttribute("user", session.getAttribute("user"));
            if ((int)session.getAttribute("Admin")==1) {//如果是管理员

                return "introductionControl";
            }else {//普通用户
                return "introduction";
            }
        }
    }


    @GetMapping("/editSite/{sid}/{name}/{desc}")
    public String editSite(@PathVariable("sid") int sid,@PathVariable("name") String name, @PathVariable("desc") String desc, HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }else {
            if ((int)session.getAttribute("Admin")==1) {//如果是管理员
                Site newSite = siteRepository.findBySid(sid);
                name = name.substring(1);
                newSite.setsName(name);

                desc = desc.substring(1);
                newSite.setsDesc(desc);
                siteRepository.save(newSite);
                return "redirect:/introduction";
            }else {
                return "redirect:/";
            }
        }
    }
}
