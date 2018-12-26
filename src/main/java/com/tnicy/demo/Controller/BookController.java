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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class BookController {
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/book")
    public String book(Model model, HttpSession session) {

        ArrayList<Site> sites = siteRepository.findAll();
        model.addAttribute("sites", sites);
        ArrayList<Period> periods = periodRepository.findAll();

        for (int i = 0; i < periods.size(); i++)
            if (periods.get(i).getIsOccupied() == 1) {
                periods.remove(i);
                i--;
            }
        model.addAttribute("periods", periods);
        model.addAttribute("user", session.getAttribute("user"));
        return "book";
    }

    @RequestMapping("/submitbook/{pids}")
    public String submitBook(@PathVariable("pids") String pids, Model model, HttpSession session) {
        String[] words = pids.split("_");

        User user = (User) session.getAttribute("user");
        String booked = user.getBooked();
        if (!booked.equals(""))
            booked = booked + "_";
        for (int i = 0; i < words.length; i++) {
            Period period = periodRepository.findByPid(Integer.parseInt(words[i]));
            period.setIsOccupied(1);
            if (i < words.length - 1)
                booked = booked + words[i] + "_";
        }
        booked = booked + words[words.length - 1];
        user.setBooked(booked);
        userRepository.save(user);
        return "redirect:/book";
    }

    @RequestMapping("/off/{pid}")
    public String bookOff(@PathVariable("pid") String pid, HttpSession session) {

        User user = (User) session.getAttribute("user");
        String[] words = user.getBooked().split("_");
        String newbooked = "";
        for (int i = 0; i < words.length - 1; i++) {
            if (!words[i].equals(pid)) {
                newbooked = newbooked + words[i] + "_";
            }
        }
        if (!words[words.length - 1].equals(pid))
            newbooked = newbooked + words[words.length - 1];
        user.setBooked(newbooked);
        userRepository.save(user);
        //更改isoccupied属性
        Period period = periodRepository.findByPid(Integer.parseInt(pid));
        period.setIsOccupied(0);
        periodRepository.save(period);
        return "redirect:/booked";
    }

    @RequestMapping("/booked")
    public String booked(HttpSession session, Model model) {

        model.addAttribute("user", session.getAttribute("user"));
        User user = (User) session.getAttribute("user");
        String[] words;
        if (!user.getBooked().equals("")) {
            words = user.getBooked().split("_");
            ArrayList<Period> periods = new ArrayList<>();
            ArrayList<Site> sites = new ArrayList<>();
            if (!words[0].equals(""))
                for (int i = 0; i < words.length; i++) {
                    Period period = periodRepository.findByPid(Integer.parseInt(words[i]));
                    Site site = siteRepository.findBySid(period.getSid());
                    sites.add(site);
                    periods.add(period);
                }
            model.addAttribute("sites", sites);
            model.addAttribute("booked", periods);

        }
        return "booked";
    }











}
