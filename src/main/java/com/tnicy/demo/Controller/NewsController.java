package com.tnicy.demo.Controller;

import com.tnicy.demo.Entity.News;
import com.tnicy.demo.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class NewsController {
@Autowired
    NewsRepository newsRepository;

    @GetMapping("/news")
    public String newsControl(Model model, HttpSession session){
        ArrayList<News> news=(ArrayList<News>)newsRepository.findAll();
        model.addAttribute("news",news);
        model.addAttribute("user", session.getAttribute("user"));
        return "news";
    }

    @GetMapping("/returnnews/{nid}")
    public String shownews(Model model, @PathVariable("nid") int nid,HttpSession session){
        News news=newsRepository.findByNid(nid);
        model.addAttribute("title",news.getTitle());
        model.addAttribute("content",news.getContent());
        model.addAttribute("user", session.getAttribute("user"));
        return "shownews";
    }

    @GetMapping("/newsControl")
    public String newscontrol(Model model,HttpSession session){
        ArrayList<News> news=(ArrayList<News>)newsRepository.findAll();
        model.addAttribute("news",news);
        model.addAttribute("user", session.getAttribute("user"));
        return "newsControl";
    }

    @GetMapping("/returnnews2/{nid}")
    public String shownews2(Model model, @PathVariable("nid") int nid,HttpSession session){
        News news=newsRepository.findByNid(nid);
        model.addAttribute("title",news.getTitle());
        model.addAttribute("content",news.getContent());
        model.addAttribute("user", session.getAttribute("user"));
        return "shownews2";
    }

    @PostMapping("/writenews")
    public String writenews(News news){
        newsRepository.save(news);
        return "redirect:/newsControl";
    }

    @GetMapping("/removenews/{nid}")
    public String removenews(@PathVariable("nid") int nid){
        newsRepository.deleteByNid(nid);
        return "redirect:/newsControl";
    }
}
