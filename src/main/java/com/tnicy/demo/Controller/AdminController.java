package com.tnicy.demo.Controller;

import com.tnicy.demo.Entity.Period;
import com.tnicy.demo.Entity.Site;
import com.tnicy.demo.Entity.User;
import com.tnicy.demo.Repository.PeriodRepository;
import com.tnicy.demo.Repository.SiteRepository;
import com.tnicy.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    UserRepository userRepository;

    //bookControl
    @GetMapping("/bookControl")
    public String getBookControl(HttpSession session, Model model) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/login";
        }


        Integer isAdmin = (Integer) session.getAttribute("Admin");

        if (!isAdmin.equals(1)) {
            return "redirect:/book";
        }

        ArrayList<Site> sites = siteRepository.findAll();
        ArrayList<Period> periods = periodRepository.findAll();
        model.addAttribute("sites", sites);
        model.addAttribute("periods", periods);
        model.addAttribute("user", session.getAttribute("user"));
        return "bookControl";
    }


    //新增时段
    @GetMapping("/newPeriod/{sid}/{day}/{starttime}/{endtime}/{cost}")
    public String newPeriod(@PathVariable("sid") Integer sid, @PathVariable("day") Integer day, @PathVariable("starttime") Integer starttime, @PathVariable("endtime") Integer endtime, @PathVariable("cost") Integer cost,HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        Period period = new Period();
        period.setSid(sid);
        period.setDate(day);
        period.setStartTime(starttime);
        period.setEndTime(endtime);
        period.setCost(cost);
        period.setIsOccupied(0);

        periodRepository.save(period);
        return "redirect:/bookControl";
    }


    //删除时段
    @GetMapping("/delPeriod/{pid}")
    public String delPeriod(@PathVariable("pid") String pidString,HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        int pid = Integer.parseInt(pidString);
        periodRepository.deleteByPid(pid);
        return "redirect:/bookControl";
    }


    @GetMapping("/newSite/{sName}/{sDesc}")
    public String newSite(@PathVariable("sName") String sName, @PathVariable("sDesc") String sDesc,HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        Site site = new Site();
        sName = sName.substring(1);
        site.setsName(sName);
        sDesc = sName.substring(1);
        site.setsDesc(sDesc);
        siteRepository.save(site);
        return "redirect:/bookControl";
    }

    //删除Site
    @GetMapping("/delSite/{sid}")
    public String delSite(@PathVariable("sid") String sidString,HttpSession session) {

        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        int sid = Integer.parseInt(sidString);
        siteRepository.deleteBySid(sid);
        periodRepository.deleteAllBySid(sid);
        return "redirect:/bookControl";
    }

    @GetMapping("bookedControl")
    public String bookedControl(Model model,HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        ArrayList<Period> periods = periodRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("periods", periods);
        model.addAttribute("user", session.getAttribute("user"));
        return "bookedControl";
    }

    @GetMapping("removeperiod/{pid}/{uid}")
    public String removeperiod(@PathVariable("pid") int pid, @PathVariable("uid") int uid,HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "redirect:/";
        }
        Period period=periodRepository.findByPid(pid);
        period.setIsOccupied(0);
        User user = userRepository.findByUid(uid);
        String[] bookeds = user.getBooked().split("_");
        ArrayList<String> newbooked = new ArrayList<String>();
        for (int i = 0; i < bookeds.length; i++) {
            if (Integer.parseInt(bookeds[i]) != pid)
                newbooked.add(bookeds[i]);
        }
        String s = "";//表示新的预定字符串
        if (newbooked.size() != 0) {
            for (int i = 0; i < newbooked.size() - 1; i++) {
                s = s + newbooked.get(i) + "_";
            }
            s = s + newbooked.get(newbooked.size() - 1);
        }
        user.setBooked(s);
        userRepository.save(user);
        return "redirect:/bookedControl";
    }
}
